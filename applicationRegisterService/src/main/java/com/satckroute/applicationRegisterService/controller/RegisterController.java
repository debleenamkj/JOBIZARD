package com.satckroute.applicationRegisterService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.satckroute.applicationRegisterService.domain.JobSeeker;
import com.satckroute.applicationRegisterService.domain.OrganizationDetails;
import com.satckroute.applicationRegisterService.domain.Recruiter;
import com.satckroute.applicationRegisterService.exception.*;
import com.satckroute.applicationRegisterService.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("api/v1")
public class RegisterController {
    private RegisterService registerService;
    private ResponseEntity responseEntity;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }


//---------------------------------------------------------------------------------------------------------------------

    @PostMapping("/jobSeeker")
    public ResponseEntity<?> addJobSeekerImage(@RequestParam("jobSeeker") String jobSeeker, @RequestParam("file") MultipartFile file) throws JobSeekerImageAlreadyExistException , IOException
    {

            JobSeeker jobSeeker1 = new ObjectMapper().readValue(jobSeeker,JobSeeker.class);
            ResponseEntity responseEntity = new ResponseEntity(registerService.saveJobSeekerImage(jobSeeker1,file),HttpStatus.CREATED);

//        catch (JobSeekerImageAlreadyExistException jobSeekerImageAlreadyExistException)
//        {
//            throw new JobSeekerImageAlreadyExistException();
//        }
//        catch (Exception exception)
//        {
//            responseEntity = new ResponseEntity<>("Try after sometime.", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
        return responseEntity;
    }


//---------------------------------------------------------------------------------------------------------------------


    @PostMapping("/recruiter")
    public ResponseEntity<?> addRecruiterImage(@RequestParam("recruiter") String recruiter, @RequestParam("file") MultipartFile file) throws RecruiterImageAlreadyExistException , IOException
    {
        Recruiter recruiter1 = new ObjectMapper().readValue(recruiter,Recruiter.class);
        ResponseEntity responseEntity = new ResponseEntity(registerService.saveRecruiterImage(recruiter1,file),HttpStatus.CREATED);

        return responseEntity;
    }
//---------------------------------------------------------------------------------------------------------------------

    @PostMapping("/organizationDetails")
    public ResponseEntity<?> addOrganizationDetailsImage(@RequestParam("organizationDetails") String organizationDetails, @RequestParam("file") MultipartFile file) throws OrganizationDetailsAlreadyExistException , IOException
    {
        OrganizationDetails organizationDetails1 = new ObjectMapper().readValue(organizationDetails,OrganizationDetails.class);
        ResponseEntity responseEntity = new ResponseEntity(registerService.saveOrganizationDetails(organizationDetails1,file),HttpStatus.CREATED);

        return responseEntity;
    }


//---------------------------------------------------------------------------------------------------------------------

    @PostMapping("/registerJobSeeker")
    public ResponseEntity<?> registerNewJobSeeker(@RequestBody JobSeeker jobSeeker) throws JobSeekerAlreadyExistException {
        try {
            return new ResponseEntity<>(registerService.registerNewJobSeeker(jobSeeker), HttpStatus.CREATED);
        } catch (JobSeekerAlreadyExistException jobSeekerAlreadyExistException) {
            throw new JobSeekerAlreadyExistException();
        } catch (Exception exception) {
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @PostMapping("/registerRecruiter")
    public ResponseEntity<?> registerNewRecruiter(@RequestBody Recruiter recruiter) throws RecruiterAlreadyExistException {
        try {
            return new ResponseEntity<>(registerService.registerNewRecruiter(recruiter), HttpStatus.CREATED);
        } catch (RecruiterAlreadyExistException recruiterAlreadyExistException) {
            throw new RecruiterAlreadyExistException();
        } catch (Exception exception) {
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @GetMapping("/getALlJobSeeker")
    public ResponseEntity<?> getAllJobSeekerDetails() {
        try {
            return new ResponseEntity<>(registerService.getAllJobSeeker(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @GetMapping("/getAllRecruiter")
    public ResponseEntity<?> getAllRecruiter() {
        try {
            return new ResponseEntity<>(registerService.getAllRecruiter(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @GetMapping("/jobSeeker/getUserByFirstName/{firstName}")
    public ResponseEntity<?> getJobSeekerByFirstName(@PathVariable String firstName) throws JobSeekerNotFoundException {
        try {
            return new ResponseEntity<>(registerService.getAllJobSeekerByFirstName(firstName), HttpStatus.OK);
        } catch (JobSeekerNotFoundException jobSeekerNotFoundException) {
            throw new JobSeekerNotFoundException();
        } catch (Exception exception) {
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @GetMapping("/jobSeeker/getUserByFirstNameAndLastName/{firstName}/{lastName}")
    public ResponseEntity<?> getJobSeekerByFirstNameAndLastName(@PathVariable String firstName, String lastName) throws JobSeekerNotFoundException {
        try {
            return new ResponseEntity<>(registerService.getAllJobSeekerByFirstNameAndLastName(firstName, lastName), HttpStatus.OK);
        } catch (JobSeekerNotFoundException jobSeekerNotFoundException) {
            throw new JobSeekerNotFoundException();
        } catch (Exception exception) {
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//---------------------------------------------------------------------------------------------------------------------

    @GetMapping("/jobSeeker/getUserByFullName/{firstName}/{middleName}/{lastName}")
    public ResponseEntity<?> getJobSeekerByFirstNameAndMiddleNameAndLastName(@PathVariable String firstName, String middleName, String lastName) throws JobSeekerNotFoundException {
        try {
            return new ResponseEntity<>(registerService.getAllJobSeekerByFirstNameAndMiddleNameAndLastName(firstName, middleName, lastName), HttpStatus.OK);
        } catch (JobSeekerNotFoundException jobSeekerNotFoundException) {
            throw new JobSeekerNotFoundException();
        } catch (Exception exception) {
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @GetMapping("/recruiter/getUserByFirstName/{firstName}")
    public ResponseEntity<?> getAllRecruiterByFirstName(@PathVariable String firstName) throws RecruiterNotFoundException {
        try {
            return new ResponseEntity<>(registerService.getAllRecruiterByFirstName(firstName), HttpStatus.OK);
        } catch (RecruiterNotFoundException recruiterNotFoundException) {
            throw new RecruiterNotFoundException();
        } catch (Exception exception) {
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @GetMapping("/recruiter/getUserByFirstNameAndLastName/{firstName}/{lastName}")
    public ResponseEntity<?> getAllRecruiterByFirstNameAndLastName(@PathVariable String firstName, String lastName) throws RecruiterNotFoundException {
        try {
            return new ResponseEntity<>(registerService.getAllRecruiterByFirstNameAndLastName(firstName, lastName), HttpStatus.OK);
        } catch (RecruiterNotFoundException recruiterNotFoundException) {
            throw new RecruiterNotFoundException();
        } catch (Exception exception) {
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @GetMapping("/recruiter/getUserByFullName/{firstName}/{middleName}/{lastName}")
    public ResponseEntity<?> getAllRecruiterByFullName(@PathVariable String firstName, String middleName, String lastName) throws RecruiterNotFoundException {
        try {
            return new ResponseEntity<>(registerService.getAllRecruiterByFirstNameAndMiddleNameAndLastName(firstName, middleName, lastName), HttpStatus.OK);
        } catch (RecruiterNotFoundException recruiterNotFoundException) {
            throw new RecruiterNotFoundException();
        } catch (Exception exception) {
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @GetMapping("/organizationDetails/getOrganizationDetailsName/{organizationName}")
    public ResponseEntity<?> getAllOrganizationDetailsByOrganizationName(@PathVariable String organizationName) throws OrganizationDetailsAlreadyExistException
    {
        try {
            return new ResponseEntity<>(registerService.getAllOrganizationDetailsByOrganizationName(organizationName), HttpStatus.OK);
        }
        catch (OrganizationDetailsAlreadyExistException organizationDetailsAlreadyExistException)
        {
            throw new OrganizationDetailsAlreadyExistException();
        } catch (Exception exception) {
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//---------------------------------------------------------------------------------------------------------------------

    @PutMapping("/jobSeeker/{jobSeekerId}")
    public ResponseEntity<?> updateJobSeekerDetails(@RequestBody JobSeeker jobSeeker, @PathVariable String jobSeekerId) throws JobSeekerNotFoundException {
        try {
            return new ResponseEntity<>(registerService.updateJobSeekerDetails(jobSeeker, jobSeekerId), HttpStatus.OK);
        } catch (JobSeekerNotFoundException e) {
            throw new JobSeekerNotFoundException();
        } catch (Exception exception) {
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @PutMapping("/recruiter/{recruiterId}")
    public ResponseEntity<?> updateRecruiterDetails(@RequestBody Recruiter recruiter, @PathVariable String recruiterId) throws RecruiterNotFoundException
    {
        try
        {
            return new ResponseEntity<>(registerService.updateRecruiterDetails(recruiter, recruiterId), HttpStatus.OK);
        }
        catch (RecruiterNotFoundException recruiterNotFoundException)
        {
            throw new RecruiterNotFoundException();
        }
        catch (Exception exception)
        {
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @DeleteMapping("/jobSeeker/{jobSeekerId}")
    public ResponseEntity<?> deleteJobSeekerDetails (@PathVariable String jobSeekerId) throws JobSeekerNotFoundException
    {
        try {
            return new ResponseEntity<>(registerService.deleteJobSeekerDetails(jobSeekerId),HttpStatus.OK);
        }
        catch (JobSeekerNotFoundException jobSeekerNotFoundException)
        {
            throw new JobSeekerNotFoundException();
        }
        catch(Exception e)
        {
            return new ResponseEntity<>("Try after some time.",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

//---------------------------------------------------------------------------------------------------------------------

    @DeleteMapping("/recruiter/{recruiterId}")
    public ResponseEntity<?> deleteRecruiterDetails (@PathVariable String recruiterId) throws RecruiterNotFoundException
    {
        try {
            return new ResponseEntity<>(registerService.deleteRecruiterDetails(recruiterId),HttpStatus.OK);
        }
        catch (RecruiterNotFoundException recruiterNotFoundException)
        {
            throw new RecruiterNotFoundException();
        }
        catch(Exception e)
        {
            return new ResponseEntity<>("Try after some time.",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

