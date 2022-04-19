package com.stackroute.recruitmentservice.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.recruitmentservice.exception.CompanyNotFound;
import com.stackroute.recruitmentservice.exception.JobsNotFound;
import com.stackroute.recruitmentservice.model.JobPosting;
import com.stackroute.recruitmentservice.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin("*")
@RestController
@RequestMapping("/recruitment")
public class JobController {
    private ResponseEntity responseEntity;
    @Autowired
    private JobService jobService;

    @PostMapping("/posting")
    public ResponseEntity<?> saveJobPost(@RequestParam("file") MultipartFile multipartFile,@RequestParam("jobs") String job) throws IOException {
       // MultipartFile multipartFile = null;
        System.out.println("in controller");
//        System.out.println(multipartFile.getBytes());
        JobPosting jobPosting = new ObjectMapper().readValue(job,JobPosting.class);
//        System.out.println(jobPosting);
        return new ResponseEntity<>(jobService.jobPosting(multipartFile,jobPosting), HttpStatus.CREATED);
    }

    @PostMapping("/postjob")
    public ResponseEntity<?> savejob(@RequestParam("jobs") String jobPosting) throws JsonProcessingException {
        JobPosting job = new ObjectMapper().readValue(jobPosting,JobPosting.class);
        return new ResponseEntity<>(jobService.addJob(job),HttpStatus.OK);
    }

    @DeleteMapping("/deleteJobPost/{companyId}")
    public ResponseEntity<?> deleteJobPost(@PathVariable String companyId) {
//        jobService.deleteJobPost(companyId);


        ResponseEntity<Boolean> responseEntity;
        try {
            responseEntity =   new ResponseEntity<>(jobService.deleteJobPost(companyId), HttpStatus.OK);
        } catch (Exception e){
            responseEntity = new ResponseEntity("Error try again after some time",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/showAllJobs")
    public ResponseEntity<?> showAllJobs() {

        try {
            responseEntity = new ResponseEntity<>(jobService.showAllJobs(), HttpStatus.OK);
        } catch (Exception e){
            responseEntity = new ResponseEntity("Error try again after some time",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    @GetMapping("/getCompany/{companyName}")
    public ResponseEntity<?> getCompanyDetails(@PathVariable String companyName) {

        try {
            responseEntity =  new ResponseEntity<>(jobService.getCompany(companyName),HttpStatus.OK);
        } catch (Exception e){
            responseEntity = new ResponseEntity("Error try again after some time",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
