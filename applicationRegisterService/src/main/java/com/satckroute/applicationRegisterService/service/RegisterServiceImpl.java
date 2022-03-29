package com.satckroute.applicationRegisterService.service;

import com.satckroute.applicationRegisterService.config.Producer;
import com.satckroute.applicationRegisterService.domain.JobSeeker;
import com.satckroute.applicationRegisterService.domain.OrganizationDetails;
import com.satckroute.applicationRegisterService.domain.Recruiter;
import com.satckroute.applicationRegisterService.exception.*;
import com.satckroute.applicationRegisterService.rabbitMQ.DTOEnum;
import com.satckroute.applicationRegisterService.rabbitMQ.UserDTO;
import com.satckroute.applicationRegisterService.repository.JobSeekerRegisterRepository;
import com.satckroute.applicationRegisterService.repository.OrganizationDetailsRepository;
import com.satckroute.applicationRegisterService.repository.RecruiterRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class RegisterServiceImpl implements RegisterService
{

    private JobSeekerRegisterRepository jobSeekerRegisterRepository;
    private RecruiterRegisterRepository recruiterRegisterRepository;
    private OrganizationDetailsRepository organizationDetailsRepository;
    private Producer producer;

    @Autowired
    public RegisterServiceImpl(JobSeekerRegisterRepository jobSeekerRegisterRepository, RecruiterRegisterRepository recruiterRegisterRepository, OrganizationDetailsRepository organizationDetailsRepository, Producer producer)
    {
        this.jobSeekerRegisterRepository = jobSeekerRegisterRepository;
        this.recruiterRegisterRepository = recruiterRegisterRepository;
        this.organizationDetailsRepository = organizationDetailsRepository;
        this.producer = producer;
    }


//---------------------------------------------------------------------------------------------------------------------

    @Override
    public JobSeeker saveJobSeekerImage(JobSeeker jobSeeker, MultipartFile file) throws JobSeekerImageAlreadyExistException, IOException
    {
        UserDTO userDTO = new UserDTO(jobSeeker.getEmailId(),jobSeeker.getPassword(),jobSeeker.getJobSeeker());//
        jobSeeker.setJobSeekerId(UUID.randomUUID().toString());
        jobSeeker.setJobSeekerImage(file.getBytes());
        if(jobSeekerRegisterRepository.findById(jobSeeker.getJobSeekerId()).isPresent())
        {
            throw new JobSeekerImageAlreadyExistException();
        }
        producer.sendMessage(userDTO);//
        return jobSeekerRegisterRepository.save(jobSeeker);
    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public Recruiter saveRecruiterImage(Recruiter recruiter, MultipartFile file) throws RecruiterImageAlreadyExistException , IOException
    {
        UserDTO userDTO = new UserDTO(recruiter.getEmailId(),recruiter.getPassword(),recruiter.getRecruiter());//
        recruiter.setRecruiterId(UUID.randomUUID().toString());
        recruiter.setRecruiterImage(file.getBytes());
        if(recruiterRegisterRepository.findById(recruiter.getRecruiterId()).isPresent())
        {
            throw new RecruiterImageAlreadyExistException();
        }
        producer.sendMessage(userDTO);
        return recruiterRegisterRepository.save(recruiter);
    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public OrganizationDetails saveOrganizationDetails(OrganizationDetails organizationDetails, MultipartFile file) throws OrganizationDetailsAlreadyExistException, IOException
    {
        organizationDetails.setOrganizationID(UUID.randomUUID().toString());
        organizationDetails.setOrganizationLogo(file.getBytes());
        if(organizationDetailsRepository.findById(organizationDetails.getOrganizationID()).isPresent())
        {
            throw new OrganizationDetailsAlreadyExistException();
        }
        return organizationDetailsRepository.save(organizationDetails);
    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public JobSeeker registerNewJobSeeker(JobSeeker jobSeeker) throws JobSeekerAlreadyExistException
    {
        if (jobSeekerRegisterRepository.findById(jobSeeker.getJobSeekerId()).isPresent())
        {
            throw new JobSeekerAlreadyExistException();
        }
        else
        {
            return jobSeekerRegisterRepository.save(jobSeeker);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public Recruiter registerNewRecruiter(Recruiter recruiter) throws RecruiterAlreadyExistException
    {
        if(recruiterRegisterRepository.findById(recruiter.getRecruiterId()).isPresent())
        {
            throw new RecruiterAlreadyExistException();
        }
        else
        {
            return recruiterRegisterRepository.save(recruiter);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public List<JobSeeker> getAllJobSeeker() throws Exception
    {
        return jobSeekerRegisterRepository.findAll();
    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public List<Recruiter> getAllRecruiter() throws Exception
    {
        return recruiterRegisterRepository.findAll();
    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public List<JobSeeker> getAllJobSeekerByFirstName(String firstName) throws JobSeekerNotFoundException
    {
        if(jobSeekerRegisterRepository.findAllJobSeekerByFirstName(firstName).isEmpty())
        {
            throw new JobSeekerNotFoundException();
        }
        else
        {
            return jobSeekerRegisterRepository.findAllJobSeekerByFirstName(firstName);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public List<Recruiter> getAllRecruiterByFirstName(String firstName) throws RecruiterNotFoundException
    {
        if(recruiterRegisterRepository.findAllRecruiterByFirstName(firstName).isEmpty())
        {
            throw new RecruiterNotFoundException();
        }
        else
        {
            return recruiterRegisterRepository.findAllRecruiterByFirstName(firstName);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public List<OrganizationDetails> getAllOrganizationDetailsByOrganizationName(String organizationName) throws OrganizationDetailsAlreadyExistException
    {
        if(organizationDetailsRepository.findAllOrganizationByOrganizationName(organizationName).isEmpty())
        {
            throw new OrganizationDetailsAlreadyExistException();
        }
        else
        {
            return organizationDetailsRepository.findAllOrganizationByOrganizationName(organizationName);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public List<JobSeeker> getAllJobSeekerByFirstNameAndLastName(String firstName, String lastName) throws JobSeekerNotFoundException
    {
        if(jobSeekerRegisterRepository.findJobSeekerByFirstNameAndLastName(firstName , lastName).isEmpty())
        {
            throw new JobSeekerNotFoundException();
        }
        return jobSeekerRegisterRepository.findJobSeekerByFirstNameAndLastName(firstName ,lastName);
    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public List<Recruiter> getAllRecruiterByFirstNameAndLastName(String firstName, String lastName) throws RecruiterNotFoundException
    {
        if(recruiterRegisterRepository.findAllRecruiterByFirstNameAndLastName(firstName, lastName).isEmpty())
        {
            throw new RecruiterNotFoundException();
        }
        return recruiterRegisterRepository.findAllRecruiterByFirstNameAndLastName(firstName, lastName);
    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public List<JobSeeker> getAllJobSeekerByFirstNameAndMiddleNameAndLastName(String firstName, String middleName, String lastName) throws JobSeekerNotFoundException
    {
        if(jobSeekerRegisterRepository.findAllJobSeekerByFirstNameAndMiddleNameAndLastName(firstName, middleName, lastName).isEmpty())
        {
            throw new JobSeekerNotFoundException();
        }
        return jobSeekerRegisterRepository.findAllJobSeekerByFirstNameAndMiddleNameAndLastName(firstName, middleName, lastName);
    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public List<Recruiter> getAllRecruiterByFirstNameAndMiddleNameAndLastName(String firstName, String middleName, String lastName) throws RecruiterNotFoundException
    {
        if(recruiterRegisterRepository.findAllRecruiterByFirstNameAndMiddleNameAndLastName(firstName, middleName, lastName).isEmpty())
        {
            throw new RecruiterNotFoundException();
        }
        return recruiterRegisterRepository.findAllRecruiterByFirstNameAndMiddleNameAndLastName(firstName, middleName, lastName);
    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public JobSeeker updateJobSeekerDetails(JobSeeker jobSeeker, String jobSeekerId) throws JobSeekerNotFoundException
    {
        if(jobSeekerRegisterRepository.findById(jobSeekerId).isEmpty())
        {
            throw new JobSeekerNotFoundException();
        }
        else
        {
            return jobSeekerRegisterRepository.save(jobSeeker);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public Recruiter updateRecruiterDetails(Recruiter recruiter, String recruiterId) throws RecruiterNotFoundException
    {
        if(recruiterRegisterRepository.findById(recruiterId).isEmpty())
        {
            throw new RecruiterNotFoundException();
        }
        else
        {
            return recruiterRegisterRepository.save(recruiter);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public boolean deleteJobSeekerDetails(String jobSeekerId) throws JobSeekerNotFoundException
    {
        boolean output = false;

        if(jobSeekerRegisterRepository.findById(jobSeekerId).isEmpty()) {
            throw new JobSeekerNotFoundException();
        }
        else
        {
            jobSeekerRegisterRepository.deleteById(jobSeekerId);
            output = true;
        }
        return output;
    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public boolean deleteRecruiterDetails(String recruiterId) throws RecruiterNotFoundException
    {
        boolean output = false;

        if(recruiterRegisterRepository.findById(recruiterId).isEmpty())
        {
            throw new RecruiterNotFoundException();
        }
        else
        {
            recruiterRegisterRepository.deleteById(recruiterId);
            output = true;
        }
        return output;
    }
}


