package com.stackroute.cvgenerationservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.cvgenerationservice.domain.userCv;
import com.stackroute.cvgenerationservice.exception.CvAlreadyExistsException;
import com.stackroute.cvgenerationservice.exception.CvNotFoundException;
import com.stackroute.cvgenerationservice.service.cvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1")
public class controller {
    private cvService service;
    private ResponseEntity responseEntity;


    @Autowired

    public controller(cvService service) {
        this.service = service;
    }
    @PostMapping("/userCv")
    public ResponseEntity<?> saveUserCv(userCv user,@RequestParam("cv") String cv, @RequestParam("file") MultipartFile file) throws CvAlreadyExistsException {
        try{
            userCv UserCv = new ObjectMapper().readValue(cv,userCv.class);
            ResponseEntity responseEntity = new ResponseEntity(service.saveCv(UserCv, file), HttpStatus.CREATED);
        }catch (CvAlreadyExistsException e) {
            throw new CvAlreadyExistsException();
        }
        catch (Exception e){
            responseEntity = new ResponseEntity<>("Error  !!!Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
    @DeleteMapping("/userCv/{cvId}")
    public ResponseEntity deleteUser(@PathVariable int cvId) throws CvNotFoundException {
        try {
            service.deleteCv(cvId);
            responseEntity = new ResponseEntity("Successfully deleted !!!", HttpStatus.OK);
        } catch (CvNotFoundException e) {

            throw new CvNotFoundException();

        } catch (Exception e) {
            responseEntity = new ResponseEntity("Error !!! Try after sometime.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @PutMapping("/userCv/{cvId}")
    public ResponseEntity<userCv> updateUser(@PathVariable int cvId,@RequestBody userCv Cv,@RequestParam("cv") String cv,@RequestParam("file") MultipartFile file) throws CvNotFoundException , IOException {
        try {
            Cv.setCvId(cvId);
            this.service.updateCv(Cv,file);
            userCv UserCv = new ObjectMapper().readValue(cv,userCv.class);
            ResponseEntity responseEntity = new ResponseEntity(service.updateCv(UserCv, file), HttpStatus.CREATED);
        }catch (CvNotFoundException e){
            throw new CvNotFoundException();
        }
        catch (Exception e){
            responseEntity = new ResponseEntity<>("Error  !!!Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
//    userCv UserCv = new ObjectMapper().readValue(cv,userCv.class);
//    ResponseEntity responseEntity = new ResponseEntity(service.saveCv(UserCv, file), HttpStatus.CREATED);

    @GetMapping("/userByCvId/{cvId}")
    public ResponseEntity<?> userById(@PathVariable int cvId) throws CvNotFoundException {
        try {
            userCv cv = service.findCvByCvId(cvId);
            responseEntity = new ResponseEntity<userCv>(cv, HttpStatus.OK);
        }catch (CvNotFoundException e){
            throw new CvNotFoundException();
        }
        catch (Exception e){
            responseEntity = new ResponseEntity<String>("NotFound",HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

}
