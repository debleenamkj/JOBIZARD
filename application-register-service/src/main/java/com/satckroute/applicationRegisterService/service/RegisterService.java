package com.satckroute.applicationRegisterService.service;

import com.satckroute.applicationRegisterService.domain.*;
import com.satckroute.applicationRegisterService.exception.*;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RegisterService
{
// with images
//    JobSeeker saveJobSeekerImage(JobSeeker jobSeeker, MultipartFile file) throws JobSeekerImageAlreadyExistException, IOException;
//    Recruiter saveRecruiterImage(Recruiter recruiter, MultipartFile file) throws RecruiterImageAlreadyExistException, IOException;
//    OrganizationDetails saveOrganizationDetails(OrganizationDetails organizationDetails , MultipartFile file) throws OrganizationDetailsAlreadyExistException , IOException;

//    Address saveAddressDetails(Address address) throws Exception;
//    Details saveExtraDetails(Details details) throws Exception;
//    Education saveEducationDetails(Education education) throws Exception;

    JobSeeker registerNewJobSeeker(JobSeeker jobSeeker) throws JobSeekerAlreadyExistException;
    Recruiter registerNewRecruiter(Recruiter recruiter) throws RecruiterAlreadyExistException;
    OrganizationDetails saveOrganizationDetails(OrganizationDetails organizationDetails) throws OrganizationDetailsAlreadyExistException;

    List<JobSeeker> getAllJobSeeker() throws Exception;
    List<Recruiter> getAllRecruiter() throws Exception;
    List<OrganizationDetails> getAllOrganization() throws Exception;

    List<JobSeeker> getAllJobSeekerByFirstName(String firstName) throws JobSeekerNotFoundException;
//    List<Recruiter> getAllRecruiterByFirstName(String firstName) throws RecruiterNotFoundException;
    List<OrganizationDetails> getAllOrganizationDetailsByOrganizationName(String organizationName) throws OrganizationDetailsNotFoundException;

    JobSeeker updateEducationDetails(String email, Education education) throws JobSeekerNotFoundException;

    JobSeeker updateJobSeekerDetails(JobSeeker jobSeeker, String emailId) throws  JobSeekerNotFoundException;
    JobSeeker updateJobSeekerDetail(JobSeeker jobSeeker, String emailId, MultipartFile file) throws  JobSeekerNotFoundException , IOException;


    Recruiter updateRecruiterDetails(Recruiter recruiter, String emailId) throws  RecruiterNotFoundException;
    Recruiter updateRecruiterDetail(Recruiter recruiter, String emailId, MultipartFile file) throws  RecruiterNotFoundException, IOException;

    OrganizationDetails updateOrganizationDetails(OrganizationDetails organizationDetails, String emailId) throws OrganizationDetailsNotFoundException;


    boolean deleteJobSeekerDetails(String emailId) throws JobSeekerNotFoundException;
    boolean deleteRecruiterDetails(String emailId) throws RecruiterNotFoundException;
    boolean deleteOrganizationDetails(String organizationEmailId) throws OrganizationDetailsNotFoundException;

    //Recruiter Landing.....................................................
    Recruiter getRecruiterProfile(String emailId) throws RecruiterNotFoundException;
    List<JobSeeker> getAllJobSeekers() throws JobSeekerNotFoundException;
    List<Skill> getSkillSet(String emailId) throws JobSeekerNotFoundException;



    Recruiter addDetailsInRecruiter(Recruiter recruiter,String emailId);

    List<JobSeeker> getJobSeekers(List<String> emailid);

    JobSeeker getJobseeker(String emailId) throws JobSeekerNotFoundException;

    JobSeeker updateDetails(String emaild, Skill skill) throws JobSeekerNotFoundException;

    Recruiter selecteJobSeeker(String recruiterEmail, String jobSeekerEmail) throws JobSeekerNotFoundException, RecruiterNotFoundException;

    JobSeeker getJobSeeker(String email, String recruiterEmail) throws JobSeekerNotFoundException, RecruiterNotFoundException;


    //public int generateJobSeekerIdInSequence(String sequenceName);
}