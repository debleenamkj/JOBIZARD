package com.stackroute.cvgenerationservice.controller;


import ch.qos.logback.core.net.SyslogOutputStream;
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
@CrossOrigin
@RestController
@RequestMapping("api/v1")
public class controller
{
    private cvService service;
    private ResponseEntity responseEntity;
    @Autowired
    public controller(cvService service) {
        this.service = service;
    }
    @PostMapping("/userCv")
    public ResponseEntity<?> saveUserCv(userCv user,@RequestParam("cv") String cv, @RequestParam("file") MultipartFile file) throws CvAlreadyExistsException {
        try{
            System.out.println(user);
            userCv UserCv = new ObjectMapper().readValue(cv,userCv.class);
            service.saveCv(UserCv, file);
            System.out.println(UserCv);
            ResponseEntity responseEntity = new ResponseEntity("Sucessfully created",HttpStatus.OK);
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
    public ResponseEntity<?> updateUser(@PathVariable int cvId,@RequestParam("cv") String cv,@RequestParam("file") MultipartFile file) throws CvNotFoundException , IOException {
        try {
            userCv UserCv = new ObjectMapper().readValue(cv,userCv.class);
            ResponseEntity responseEntity = new ResponseEntity(service.updateCv( cvId,UserCv,file), HttpStatus.CREATED);
        }catch (CvNotFoundException e){
            throw new CvNotFoundException();
        }
        catch (Exception e){
            responseEntity = new ResponseEntity<>("Error  !!!Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
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
    @GetMapping("/cvByEmail/{email}")
    public ResponseEntity<?> cvByEmailid(@PathVariable String email) throws CvNotFoundException {

        userCv cv=service.findByEmail(email);

        if(cv!=null)
            return new ResponseEntity<userCv>(cv,HttpStatus.OK);

        return new ResponseEntity<String>("NotFound",HttpStatus.CONFLICT);
    }

}
