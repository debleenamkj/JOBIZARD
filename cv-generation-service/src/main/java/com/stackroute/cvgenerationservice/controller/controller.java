package com.stackroute.cvgenerationservice.controller;

import com.stackroute.cvgenerationservice.domain.userCv;
import com.stackroute.cvgenerationservice.service.cvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class controller {
    private cvService service;

    @Autowired

    public controller(cvService service) {
        this.service = service;
    }
    @PostMapping("/userCv")
    public ResponseEntity<userCv> saveUserCv(@RequestBody userCv cv) throws Exception {
        userCv savedUserCv=service.saveCv(cv);
        return new ResponseEntity<>(savedUserCv, HttpStatus.CREATED);
    }
    @DeleteMapping("/userCv/{cvId}")
    public HttpStatus deleteUser(@PathVariable String cvId) throws Exception {
        this.service.deleteCv(cvId);
        return HttpStatus.OK;
    }
    @PutMapping("/userCv/{cvId}")
    public ResponseEntity<userCv> updateUser(@PathVariable String cvId,@RequestBody userCv cv) throws Exception {
        cv.setCvId(cvId);
        return ResponseEntity.ok().body(this.service.updateCv(cv));
    }
    @GetMapping("/userByCvId/{cvId}")
    public ResponseEntity<?> userById(@PathVariable String cvId) throws Exception {

        userCv cv=service.findCvByCvId(cvId);

        if(cv!=null)
            return new ResponseEntity<userCv>(cv,HttpStatus.OK);

        return new ResponseEntity<String>("NotFound",HttpStatus.CONFLICT);
    }
}
