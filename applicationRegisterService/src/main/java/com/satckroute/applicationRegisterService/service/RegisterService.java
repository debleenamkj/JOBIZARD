package com.satckroute.applicationRegisterService.service;

import com.satckroute.applicationRegisterService.domain.JobSeeker;
import com.satckroute.applicationRegisterService.domain.OrganizationDetails;
import com.satckroute.applicationRegisterService.domain.Recruiter;
import com.satckroute.applicationRegisterService.exception.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RegisterService
{
    JobSeeker saveJobSeekerImage(JobSeeker jobSeeker, MultipartFile file) throws JobSeekerImageAlreadyExistException, IOException;
    Recruiter saveRecruiterImage(Recruiter recruiter, MultipartFile file) throws RecruiterImageAlreadyExistException, IOException;
    OrganizationDetails saveOrganizationDetails(OrganizationDetails organizationDetails , MultipartFile file) throws OrganizationDetailsAlreadyExistException , IOException;

    JobSeeker registerNewJobSeeker(JobSeeker jobSeeker) throws JobSeekerAlreadyExistException;
    Recruiter registerNewRecruiter(Recruiter recruiter) throws RecruiterAlreadyExistException;

    List<JobSeeker> getAllJobSeeker() throws Exception;
    List<Recruiter> getAllRecruiter() throws Exception;

    List<JobSeeker> getAllJobSeekerByFirstName(String firstName) throws JobSeekerNotFoundException;
    List<Recruiter> getAllRecruiterByFirstName(String firstName) throws RecruiterNotFoundException;
    List<OrganizationDetails> getAllOrganizationDetailsByOrganizationName(String organizationName) throws OrganizationDetailsAlreadyExistException;


    JobSeeker updateJobSeekerDetails(JobSeeker jobSeeker, String emailId) throws  JobSeekerNotFoundException;
    Recruiter updateRecruiterDetails(Recruiter recruiter, String emailId) throws  RecruiterNotFoundException;

    boolean deleteJobSeekerDetails(String emailId) throws JobSeekerNotFoundException;
    boolean deleteRecruiterDetails(String emailId) throws RecruiterNotFoundException;
    boolean deleteOrganizationDetails(String organizationId) throws OrganizationDetailsNotFoundException;


    //public int generateJobSeekerIdInSequence(String sequenceName);
}