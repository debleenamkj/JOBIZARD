package com.satckroute.applicationRegisterService.service;

import com.satckroute.applicationRegisterService.config.Producer;
import com.satckroute.applicationRegisterService.domain.*;
import com.satckroute.applicationRegisterService.exception.*;
import com.satckroute.applicationRegisterService.rabbitMQ.UserDTO;
import com.satckroute.applicationRegisterService.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
@Slf4j
public class RegisterServiceImpl implements RegisterService
{

    private JobSeekerRegisterRepository jobSeekerRegisterRepository;
    private RecruiterRegisterRepository recruiterRegisterRepository;
    private OrganizationDetailsRepository organizationDetailsRepository;
    private AddressDetailsRepository addressDetailsRepository;
    private EducationDetailsRepository educationDetailsRepository;
    private MongoOperations mongoOperations;
    private Producer producer;

    @Autowired
    public RegisterServiceImpl(JobSeekerRegisterRepository jobSeekerRegisterRepository,
                               RecruiterRegisterRepository recruiterRegisterRepository,
                               OrganizationDetailsRepository organizationDetailsRepository,
                               MongoOperations mongoOperations,
                               Producer producer, AddressDetailsRepository addressDetailsRepository,
                               EducationDetailsRepository educationDetailsRepository)
    {
        log.info("Autowiring - RegisterService");
        this.jobSeekerRegisterRepository = jobSeekerRegisterRepository;
        this.recruiterRegisterRepository = recruiterRegisterRepository;
        this.organizationDetailsRepository = organizationDetailsRepository;
        this.mongoOperations = mongoOperations;
        this.producer = producer;
        this.addressDetailsRepository = addressDetailsRepository;
        this.educationDetailsRepository =educationDetailsRepository;
    }


//---------------------------------------------------------------------------------------------------------------------


//---------------------------------------------------------------------------------------------------------------------


// with images
//    @Override
//    public JobSeeker saveJobSeekerImage(JobSeeker jobSeeker, MultipartFile file) throws JobSeekerImageAlreadyExistException, IOException
//    {
////        Role role = Role.JOBSEEKER;
////        jobSeeker.setRole("jobSeeker");
////        UserDTO userDTO = new UserDTO(jobSeeker.getEmailId(),jobSeeker.getPassword(),jobSeeker.getRole());//
//        UserDTO userDTO = new UserDTO(jobSeeker.getEmailId(),jobSeeker.getPassword());
//        //jobSeeker.setJobSeekerId(generateJobSeekerIdInSequence(JobSeeker.SEQUENCE_NAME));
//        //jobSeeker.setJobSeekerId(UUID.randomUUID().toString());
//        jobSeeker.setJobSeekerImage(file.getBytes());
//        System.out.println("service");
//        System.out.println(jobSeeker);
//        if(jobSeekerRegisterRepository.findById(jobSeeker.getEmailId()).isPresent())
//        {
//            throw new JobSeekerImageAlreadyExistException();
//        }
//        producer.sendMessage(userDTO);//
//        return jobSeekerRegisterRepository.save(jobSeeker);
//    }
//
//
////---------------------------------------------------------------------------------------------------------------------
//
//    @Override
//    public Recruiter saveRecruiterImage(Recruiter recruiter, MultipartFile file) throws RecruiterImageAlreadyExistException , IOException
//    {
////        Role role = Role.RECRUITER;
////        recruiter.setRole("recruiter");
////        UserDTO userDTO = new UserDTO(recruiter.getEmailId(),recruiter.getPassword(),recruiter.getRole());//
//        UserDTO userDTO = new UserDTO(recruiter.getEmailId(),recruiter.getPassword());
//
////        recruiter.setRecruiterId(UUID.randomUUID().toString());
//        recruiter.setRecruiterImage(file.getBytes());
//        if(recruiterRegisterRepository.findById(recruiter.getEmailId()).isPresent())
//        {
//            throw new RecruiterImageAlreadyExistException();
//        }
//        producer.sendMessage(userDTO);
//        return recruiterRegisterRepository.save(recruiter);
//    }
//
////---------------------------------------------------------------------------------------------------------------------
//
//    @Override
//    public OrganizationDetails saveOrganizationDetails(OrganizationDetails organizationDetails, MultipartFile file) throws OrganizationDetailsAlreadyExistException, IOException
//    {
////        organizationDetails.setOrganizationID(UUID.randomUUID().toString());
//        UserDTO userDTO = new UserDTO(organizationDetails.getEmailId(),organizationDetails.getPassword());
//        organizationDetails.setOrganizationLogo(file.getBytes());
//        if(organizationDetailsRepository.findById(organizationDetails.getEmailId()).isPresent())
//        {
//            throw new OrganizationDetailsAlreadyExistException();
//        }
//        producer.sendMessage(userDTO);
//        return organizationDetailsRepository.save(organizationDetails);
//    }
//---------------------------------------------------------------------------------------------------------------------

//    @Override
//    public Address saveAddressDetails(Address address) throws Exception
//    {
//        if (addressDetailsRepository.findById(address.getState()).isPresent())
//        {
//            throw new Exception();
//        }
//        else
//        {
//            return addressDetailsRepository.save(address);
//        }
//    }
//---------------------------------------------------------------------------------------------------------------------

//    @Override
//    public Details saveExtraDetails(Details details) throws Exception
//    {
//        return null;
//    }
////---------------------------------------------------------------------------------------------------------------------
//
//    @Override
//    public Education saveEducationDetails(Education education) throws Exception
//    {
//        if (educationDetailsRepository.findById(education.getEducation()).isPresent())
//        {
//            throw new Exception();
//        }
//        else
//        {
//            return educationDetailsRepository.save(education);
//        }
//    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public JobSeeker registerNewJobSeeker(JobSeeker jobSeeker) throws JobSeekerAlreadyExistException
    {
        //,jobSeeker.role.toString()
//        UserDTO userDTO = new UserDTO(jobSeeker.getEmailId(),jobSeeker.getPassword());

        //role
        UserDTO userDTO = new UserDTO(jobSeeker.getEmailId(),jobSeeker.getPassword(),jobSeeker.role.toString());;
        if (jobSeekerRegisterRepository.findById(jobSeeker.getEmailId()).isPresent())
        {
            throw new JobSeekerAlreadyExistException();
        }
        else
        {
//            System.out.println(jobSeeker);
            producer.sendMessage(userDTO);
            return jobSeekerRegisterRepository.save(jobSeeker);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public Recruiter registerNewRecruiter(Recruiter recruiter) throws RecruiterAlreadyExistException
    {
//        ,recruiter.role.toString()
//        UserDTO userDTO = new UserDTO(recruiter.getEmailId(),recruiter.getPassword());

        //role
        UserDTO userDTO = new UserDTO(recruiter.getEmailId(),recruiter.getPassword(),recruiter.role.toString());
        if(recruiterRegisterRepository.findById(recruiter.getEmailId()).isPresent())
        {
            throw new RecruiterAlreadyExistException();
        }
        else
        {
            producer.sendMessage(userDTO);
            return recruiterRegisterRepository.save(recruiter);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public OrganizationDetails saveOrganizationDetails(OrganizationDetails organizationDetails) throws OrganizationDetailsAlreadyExistException
    {
//        UserDTO userDTO = new UserDTO(organizationDetails.getEmailId(),organizationDetails.getPassword());
        if(organizationDetailsRepository.findById(organizationDetails.getEmailId()).isPresent())
        {
            throw new OrganizationDetailsAlreadyExistException();
        }
        else
        {
//            producer.sendMessage(userDTO);
            return organizationDetailsRepository.save(organizationDetails);
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

    @Override
    public List<OrganizationDetails> getAllOrganization() throws Exception
    {
        return organizationDetailsRepository.findAll();
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
    public List<OrganizationDetails> getAllOrganizationDetailsByOrganizationName(String organizationName) throws OrganizationDetailsNotFoundException
    {
        if(organizationDetailsRepository.findAllOrganizationByOrganizationName(organizationName).isEmpty())
        {
            throw new OrganizationDetailsNotFoundException();
        }
        else
        {
            return organizationDetailsRepository.findAllOrganizationByOrganizationName(organizationName);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public JobSeeker updateJobSeekerDetails(JobSeeker jobSeeker, String emailId) throws JobSeekerNotFoundException
    {
        if(jobSeekerRegisterRepository.findById(emailId).isEmpty())
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
    public JobSeeker updateJobSeekerDetail(JobSeeker jobSeeker, String emailId, MultipartFile file) throws JobSeekerNotFoundException, IOException
    {
        jobSeeker.setJobSeekerImage(file.getBytes());
        if(jobSeekerRegisterRepository.findById(emailId).isEmpty())
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
    public Recruiter updateRecruiterDetails(Recruiter recruiter, String emailId) throws RecruiterNotFoundException
    {
        if(recruiterRegisterRepository.findById(emailId).isEmpty())
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
    public Recruiter updateRecruiterDetail(Recruiter recruiter, String emailId, MultipartFile file) throws RecruiterNotFoundException, IOException
    {
        recruiter.setRecruiterImage(file.getBytes());
        if(recruiterRegisterRepository.findById(emailId).isEmpty())
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
    public OrganizationDetails updateOrganizationDetails(OrganizationDetails organizationDetails, String emailId) throws OrganizationDetailsNotFoundException
    {
        if(organizationDetailsRepository.findById(emailId).isEmpty())
        {
            throw new OrganizationDetailsNotFoundException();
        }
        else
        {
            return organizationDetailsRepository.save(organizationDetails);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public boolean deleteJobSeekerDetails(String emailId) throws JobSeekerNotFoundException
    {
        boolean output = false;

        if(jobSeekerRegisterRepository.findById(emailId).isEmpty()) {
            throw new JobSeekerNotFoundException();
        }
        else
        {
            jobSeekerRegisterRepository.deleteById(emailId);
            output = true;
        }
        return output;
    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public boolean deleteRecruiterDetails(String emailId) throws RecruiterNotFoundException
    {
        boolean output = false;

        if(recruiterRegisterRepository.findById(emailId).isEmpty())
        {
            throw new RecruiterNotFoundException();
        }
        else
        {
            recruiterRegisterRepository.deleteById(emailId);
            output = true;
        }
        return output;
    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public boolean deleteOrganizationDetails(String organizationId) throws OrganizationDetailsNotFoundException
    {
        boolean output = false;

        if(organizationDetailsRepository.findById(organizationId).isEmpty())
        {
            throw new OrganizationDetailsNotFoundException();
        }
        else
        {
            organizationDetailsRepository.deleteById(organizationId);
            output = true;
        }
        return output;
    }

//---------------------------------------------------------------------------------------------------------------------

//    @Override
//    public int generateJobSeekerIdInSequence(String sequenceName)
//    {
//        Query query = new Query(Criteria.where("id").is(sequenceName));
//        Update update = new Update().inc("seqNo", 1);
//        JobSeekerIdSequence counter = mongoOperations
//                .findAndModify(query,
//                        update, options().returnNew(true).upsert(true),
//                        JobSeekerIdSequence.class);
//        return !Objects.isNull(counter) ? counter.getSequenceNumber(): 1;
//    }

    //Recruiter Landing.....................................................
    @Override
    public Recruiter getRecruiterProfile(String emailId) throws RecruiterNotFoundException {
        if (recruiterRegisterRepository.findById(emailId).isEmpty()) {
            throw new RecruiterNotFoundException();
        }
        return recruiterRegisterRepository.findById(emailId).get();
    }

    @Override
    public List<JobSeeker> getAllJobSeekers() throws JobSeekerNotFoundException {
        return jobSeekerRegisterRepository.findAll();
    }

    @Override
    public List<Skill> getSkillSet(String emailId) throws JobSeekerNotFoundException
    {
        if (jobSeekerRegisterRepository.findById(emailId).isEmpty())
        {
            throw new JobSeekerNotFoundException();
        }
        return List.of(jobSeekerRegisterRepository.findById(emailId).get().getAdditionalDetails().getSkillSet());
    }
}


