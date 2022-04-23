package com.satckroute.applicationRegisterService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.satckroute.applicationRegisterService.domain.*;
import com.satckroute.applicationRegisterService.exception.*;
import com.satckroute.applicationRegisterService.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@Slf4j
@RestController
@RequestMapping("api/v1")
public class RegisterController
{
    private RegisterService registerService;
    private ResponseEntity responseEntity;

    @Autowired
    public RegisterController(RegisterService registerService)
    {
        log.info("Autowiring - RegisterService");
        this.registerService = registerService;
    }

    @PostMapping("/registerJobSeeker")
    public ResponseEntity<?> registerNewJobSeeker(@RequestBody JobSeeker jobSeeker) throws JobSeekerAlreadyExistException
    {
        try
        {
            log.info("RegisterController - registerNewJobSeeker");
            return new ResponseEntity<>(registerService.registerNewJobSeeker(jobSeeker), HttpStatus.CREATED);
        }
        catch (JobSeekerAlreadyExistException jobSeekerAlreadyExistException)
        {
            log.error("RegisterController - registerNewJobSeeker"+jobSeekerAlreadyExistException);
            throw new JobSeekerAlreadyExistException();
        }
        catch (Exception exception)
        {
            log.error("RegisterController - registerNewJobSeeker"+exception);
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @PostMapping("/registerRecruiter")
    public ResponseEntity<?> registerNewRecruiter(@RequestBody Recruiter recruiter) throws RecruiterAlreadyExistException
    {
        try
        {

            log.debug("RegisterController - registerNewRecruiter");
            return new ResponseEntity<>(registerService.registerNewRecruiter(recruiter), HttpStatus.CREATED);
        }
        catch (RecruiterAlreadyExistException recruiterAlreadyExistException)
        {
            log.error("RegisterController - registerNewRecruiter"+recruiterAlreadyExistException);
            throw new RecruiterAlreadyExistException();

        }
        catch (Exception exception)
        {
            log.error("RegisterController - registerNewRecruiter"+exception);
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);

//        } catch (Exception exception) {
//            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @PostMapping("/saveOrganizationDetails")
    public ResponseEntity<?> registerOrganizationDetails(@RequestBody OrganizationDetails organizationDetails) throws OrganizationDetailsAlreadyExistException
    {
        try
        {
            log.debug("RegisterController - registerOrganizationDetails");
            return new ResponseEntity<>(registerService.saveOrganizationDetails(organizationDetails), HttpStatus.CREATED);
        }
        catch (OrganizationDetailsAlreadyExistException organizationDetailsAlreadyExistException)
        {
            log.error("RegisterController - registerOrganizationDetails"+organizationDetailsAlreadyExistException);
            throw new OrganizationDetailsAlreadyExistException();
        }
        catch (Exception exception)
        {
            log.error("RegisterController - registerOrganizationDetails"+exception);
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @GetMapping("/getALlJobSeeker")
    public ResponseEntity<?> getAllJobSeekerDetails()
    {
        try
        {
            log.debug("RegisterController - getAllJobSeekerDetails");
            return new ResponseEntity<>(registerService.getAllJobSeeker(), HttpStatus.OK);
        }
        catch (Exception exception)
        {
            log.error("RegisterController - getAllJobSeekerDetails"+exception);
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @GetMapping("/getAllRecruiter")
    public ResponseEntity<?> getAllRecruiter()
    {
        try
        {
            log.debug("RegisterController - getAllRecruiter");
            return new ResponseEntity<>(registerService.getAllRecruiter(), HttpStatus.OK);
        }
        catch (Exception exception)
        {
            log.error("RegisterController - getAllRecruiter"+exception);
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @GetMapping("/getAllOrganization")
    public ResponseEntity<?> getAllOrganization()
    {
        try
        {
            log.debug("RegisterController - getAllOrganization");
            return new ResponseEntity<>(registerService.getAllOrganization(), HttpStatus.OK);
        }
        catch (Exception exception)
        {
            log.error("RegisterController - getAllOrganization"+exception);
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @GetMapping("/jobSeeker/getUserByFirstName/{firstName}")
    public ResponseEntity<?> getJobSeekerByFirstName(@PathVariable String firstName) throws JobSeekerNotFoundException
    {
        try
        {
            log.debug("RegisterController - getJobSeekerByFirstName");
            return new ResponseEntity<>(registerService.getAllJobSeekerByFirstName(firstName), HttpStatus.OK);
        }
        catch (JobSeekerNotFoundException jobSeekerNotFoundException)
        {
            log.error("RegisterController - getJobSeekerByFirstName"+jobSeekerNotFoundException);
            throw new JobSeekerNotFoundException();
        }
        catch (Exception exception)
        {
            log.error("RegisterController - getJobSeekerByFirstName"+exception);
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//---------------------------------------------------------------------------------------------------------------------

//    @GetMapping("/recruiter/getUserByFirstName/{firstName}")
//    public ResponseEntity<?> getAllRecruiterByFirstName(@PathVariable String firstName) throws RecruiterNotFoundException
//    {
//        try
//        {
//            log.debug("RegisterController - getAllRecruiterByFirstName");
//            return new ResponseEntity<>(registerService.getAllRecruiterByFirstName(firstName), HttpStatus.OK);
//        }
//        catch (RecruiterNotFoundException recruiterNotFoundException)
//        {
//            log.error("RegisterController - getAllRecruiterByFirstName"+recruiterNotFoundException);
//            throw new RecruiterNotFoundException();
//        }
//        catch (Exception exception)
//        {
//            log.error("RegisterController - getAllRecruiterByFirstName"+exception);
//            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//---------------------------------------------------------------------------------------------------------------------

    @GetMapping("/organizationDetails/getOrganizationDetailsName/{organizationName}")
    public ResponseEntity<?> getAllOrganizationDetailsByOrganizationName(@PathVariable String organizationName) throws OrganizationDetailsNotFoundException
    {
        try
        {
            log.debug("RegisterController - getAllOrganizationDetailsByOrganizationName");
            return new ResponseEntity<>(registerService.getAllOrganizationDetailsByOrganizationName(organizationName), HttpStatus.OK);
        }
        catch (OrganizationDetailsNotFoundException organizationDetailsNotFoundException)
        {
            log.error("RegisterController - getAllOrganizationDetailsByOrganizationName"+organizationDetailsNotFoundException);
            throw new OrganizationDetailsNotFoundException();
        }
        catch (Exception exception)
        {
            log.error("RegisterController - getAllOrganizationDetailsByOrganizationName"+exception);
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/jobSeeker/education/{emailId}")
    public ResponseEntity<?> updateJobSeekerEducation(@RequestBody Education education, @PathVariable String emailId) throws JobSeekerNotFoundException
    {
        System.out.println(education);
        try
        {
            log.debug("RegisterController - updateJobSeekerDetails");
            return new ResponseEntity<>(registerService.updateEducationDetails(emailId,education), HttpStatus.OK);
        }
        catch (JobSeekerNotFoundException jobSeekerNotFoundException)
        {
            log.error("RegisterController - updateJobSeekerDetails"+jobSeekerNotFoundException);
            throw new JobSeekerNotFoundException();
        }
        catch (Exception exception)
        {
            log.error("RegisterController - updateJobSeekerDetails"+exception);
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @PutMapping("/jobSeekerWithoutImage/{emailId}")
    public ResponseEntity<?> updateJobSeekerDetails(@RequestBody JobSeeker jobSeeker, @PathVariable String emailId) throws JobSeekerNotFoundException
    {
        try
        {
            System.out.println(jobSeeker);
            log.debug("RegisterController - updateJobSeekerDetails");
            return new ResponseEntity<>(registerService.updateJobSeekerDetails(jobSeeker, emailId), HttpStatus.OK);
        }
        catch (JobSeekerNotFoundException jobSeekerNotFoundException)
        {
            log.error("RegisterController - updateJobSeekerDetails"+jobSeekerNotFoundException);
            throw new JobSeekerNotFoundException();
        }
        catch (Exception exception)
        {
            log.error("RegisterController - updateJobSeekerDetails"+exception);
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @PutMapping("/jobSeekerWithImage/{emailId}")
    public ResponseEntity<?> updateJobSeekerDetail(@RequestParam("jobSeeker1") String jobSeeker, @PathVariable String emailId , @RequestParam("file") MultipartFile file) throws JobSeekerNotFoundException, IOException
    {
//        try
//        {
            log.debug("RegisterController - updateJobSeekerDetail");
            JobSeeker jobSeeker1 = new ObjectMapper().readValue(jobSeeker,JobSeeker.class);
            //
//            ResponseEntity responseEntity = new ResponseEntity(registerService.updateJobSeekerDetail(jobSeeker1,emailId,file),HttpStatus.CREATED);
            return new ResponseEntity<>(registerService.updateJobSeekerDetail(jobSeeker1, emailId,file), HttpStatus.OK);
//        }
//        catch (JobSeekerNotFoundException jobSeekerNotFoundException)
//        {
//            log.error("RegisterController - updateJobSeekerDetail"+jobSeekerNotFoundException);
//            throw new JobSeekerNotFoundException();
//        }
//        catch (Exception exception)
//        {
//            log.error("RegisterController - updateJobSeekerDetail"+exception);
//            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }
//---------------------------------------------------------------------------------------------------------------------

    @PutMapping("/recruiterWithoutImage/{emailId}")
    public ResponseEntity<?> updateRecruiterDetails(@RequestParam("jobs") String recruiter, @PathVariable String emailId) throws RecruiterNotFoundException
    {
        try
        {
            System.out.println(recruiter);
            Recruiter recruiter1 = new ObjectMapper().readValue(recruiter,Recruiter.class);
            log.debug("RegisterController - updateRecruiterDetails");
            return new ResponseEntity<>(registerService.updateRecruiterDetails(recruiter1, emailId), HttpStatus.OK);
        }
        catch (RecruiterNotFoundException recruiterNotFoundException)
        {
            log.error("RegisterController - updateRecruiterDetails"+recruiterNotFoundException);
            throw new RecruiterNotFoundException();
        }
        catch (Exception exception)
        {
            log.error("RegisterController - updateRecruiterDetails"+exception);
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @PutMapping("/recruiterWithImage/{emailId}")
    public ResponseEntity<?> updateRecruiterDetail(@PathVariable String emailId ,@RequestParam("recruiter1") String recruiter, @RequestParam("file") MultipartFile file) throws RecruiterNotFoundException, IOException
    {
        try
        {
            log.debug("RegisterController - updateRecruiterDetail");
            Recruiter recruiter1 = new ObjectMapper().readValue(recruiter,Recruiter.class);
            //
//            ResponseEntity responseEntity = new ResponseEntity(registerService.updateRecruiterDetail(recruiter1,emailId,file),HttpStatus.CREATED);
            return new ResponseEntity<>(registerService.updateRecruiterDetail(recruiter1,emailId,file), HttpStatus.OK);
        }
        catch (RecruiterNotFoundException recruiterNotFoundException)
        {
            log.error("RegisterController - updateRecruiterDetail"+recruiterNotFoundException);
            throw new RecruiterNotFoundException();
        }
        catch (Exception exception)
        {
            log.error("RegisterController - updateRecruiterDetail"+exception);
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @PutMapping("/organization/{emailId}")
    public ResponseEntity<?> updateOrganizationDetails(@RequestBody OrganizationDetails organizationDetails, @PathVariable String emailId) throws OrganizationDetailsNotFoundException
    {
        try
        {
            log.debug("RegisterController - updateOrganizationDetails");
            return new ResponseEntity<>(registerService.updateOrganizationDetails(organizationDetails, emailId), HttpStatus.OK);
        }
        catch (OrganizationDetailsNotFoundException organizationDetailsNotFoundException)
        {
            log.error("RegisterController - updateOrganizationDetails"+organizationDetailsNotFoundException);
            throw new OrganizationDetailsNotFoundException();
        }
        catch (Exception exception)
        {
            log.error("RegisterController - updateOrganizationDetails"+exception);
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//---------------------------------------------------------------------------------------------------------------------

    @DeleteMapping("/jobSeeker/{emailId}")
    public ResponseEntity<?> deleteJobSeekerDetails (@PathVariable String emailId) throws JobSeekerNotFoundException
    {
        try
        {
            log.debug("RegisterController - deleteJobSeekerDetails");
            return new ResponseEntity<>(registerService.deleteJobSeekerDetails(emailId),HttpStatus.OK);
        }
        catch (JobSeekerNotFoundException jobSeekerNotFoundException)
        {
            log.error("RegisterController - deleteJobSeekerDetails"+jobSeekerNotFoundException);
            throw new JobSeekerNotFoundException();
        }
        catch(Exception exception)
        {
            log.error("RegisterController - deleteJobSeekerDetails"+exception);
            return new ResponseEntity<>("Try after some time.",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

//---------------------------------------------------------------------------------------------------------------------

    @DeleteMapping("/recruiter/{emailId}")
    public ResponseEntity<?> deleteRecruiterDetails (@PathVariable String emailId) throws RecruiterNotFoundException
    {
        try
        {
            log.debug("RegisterController - deleteRecruiterDetails");
            return new ResponseEntity<>(registerService.deleteRecruiterDetails(emailId),HttpStatus.OK);
        }
        catch (RecruiterNotFoundException recruiterNotFoundException)
        {
            log.error("RegisterController - deleteRecruiterDetails"+recruiterNotFoundException);
            throw new RecruiterNotFoundException();
        }
        catch(Exception exception)
        {
            log.error("RegisterController - deleteRecruiterDetails"+exception);
            return new ResponseEntity<>("Try after some time.",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

//---------------------------------------------------------------------------------------------------------------------

    @DeleteMapping("/organizationDetails/{emailId}")
    public ResponseEntity<?> deleteOrganizationDetails (@PathVariable String emailId) throws OrganizationDetailsNotFoundException
    {
        try
        {
            log.debug("RegisterController - deleteOrganizationDetails");
            return new ResponseEntity<>(registerService.deleteOrganizationDetails(emailId),HttpStatus.OK);
        }
        catch (OrganizationDetailsNotFoundException organizationDetailsNotFoundException)
        {
            log.error("RegisterController - deleteOrganizationDetails"+organizationDetailsNotFoundException);
            throw new OrganizationDetailsNotFoundException();
        }
        catch(Exception exception)
        {
            log.error("RegisterController - deleteOrganizationDetails"+exception);
            return new ResponseEntity<>("Try after some time.",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

//---------------------------------------------------------------------------------------------------------------------

    //RecruiterLanding..................................................................................
    @GetMapping("/recruiterProfile/{emailId}")
    public ResponseEntity<?> getRecruiterProfile(@PathVariable String emailId) throws RecruiterNotFoundException
    {
        try
        {
            log.debug("RegisterController - getRecruiterProfile");
            return new ResponseEntity<>(registerService.getRecruiterProfile(emailId), HttpStatus.OK);
        }
        catch (RecruiterNotFoundException recruiterNotFoundException)
        {
            log.error("RegisterController - getRecruiterProfile"+recruiterNotFoundException);
            throw new RecruiterNotFoundException();
        }
        catch (Exception exception)
        {
            log.error("RegisterController - getRecruiterProfile"+exception);
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @GetMapping("/jobSeekers")
    public ResponseEntity<?> getAllJobSeekers() throws JobSeekerNotFoundException
    {
        try
        {
            log.debug("RegisterController - getAllJobSeekers");
            return new ResponseEntity<>(registerService.getAllJobSeekers(), HttpStatus.OK);
        }
        catch (JobSeekerNotFoundException jobSeekerNotFoundException)
        {
            log.error("RegisterController - getAllJobSeekers"+jobSeekerNotFoundException);
            throw new JobSeekerNotFoundException();
        }
        catch (Exception exception)
        {
            log.error("RegisterController - getAllJobSeekers"+exception);
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @GetMapping("/skillSet/{emailId}")
    public ResponseEntity<?> getSkillSet(@PathVariable String emailId) throws JobSeekerNotFoundException
    {
        try
        {
            log.debug("RegisterController - getSkillSet");
            return new ResponseEntity<>(registerService.getSkillSet(emailId), HttpStatus.OK);
        }
        catch (JobSeekerNotFoundException jobSeekerNotFoundException)
        {
            log.error("RegisterController - getSkillSet"+jobSeekerNotFoundException);
            throw new JobSeekerNotFoundException();
        }
        catch (Exception exception)
        {
            log.error("RegisterController - getSkillSet"+exception);
            return new ResponseEntity<>("Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//---------------------------------------------------------------------------------------------------------------------


    @PostMapping("/match/jobSeeker")
    public List<JobSeeker> getJobseekers(@RequestBody List<String> emailId){
        return registerService.getJobSeekers(emailId);
    }

//---------------------------------------------------------------------------------------------------------------------

    @GetMapping("/{emailId}")
    public JobSeeker getJobseeker(@PathVariable String emailId) throws JobSeekerNotFoundException {
        return registerService.getJobseeker(emailId);
    }

//---------------------------------------------------------------------------------------------------------------------

    @PutMapping("/recruiter/add/{emailId}")
    public Recruiter addDetailsInRecruiter(@PathVariable String emailId,@RequestParam("recruiter") String recruiter) throws JsonProcessingException
    {
        Recruiter recruiter1 = new ObjectMapper().readValue(recruiter,Recruiter.class);
        return registerService.addDetailsInRecruiter(recruiter1,emailId);
    }

//---------------------------------------------------------------------------------------------------------------------

    @PutMapping("/marks/{emailId}")
    public JobSeeker updateMarks(@PathVariable String emailId,@RequestBody Skill skill) throws JobSeekerNotFoundException {
        System.out.println(skill);
        return registerService.updateDetails(emailId,skill);
    }

    @PutMapping("/shortList/{jobSeekerEmailId}/{emailId}")
    public Recruiter shortlist(@PathVariable String jobSeekerEmailId,@PathVariable String emailId) throws JobSeekerNotFoundException, RecruiterNotFoundException {
        return registerService.selecteJobSeeker(emailId, jobSeekerEmailId);
    }

    @GetMapping("/get/{jobSeekerEmailId}/{emailId}")
    public JobSeeker getJobSeeker(@PathVariable String jobSeekerEmailId,@PathVariable String emailId) throws JobSeekerNotFoundException, RecruiterNotFoundException {
        System.out.println("job seeker :"+jobSeekerEmailId+"\t recruiter email :"+emailId);
        return registerService.getJobSeeker(jobSeekerEmailId,emailId);
    }

    }
