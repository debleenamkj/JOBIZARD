package com.satckroute.applicationRegisterService.service;

import com.satckroute.applicationRegisterService.config.Producer;
import com.satckroute.applicationRegisterService.domain.*;
import com.satckroute.applicationRegisterService.exception.*;
import com.satckroute.applicationRegisterService.rabbitMQ.JobDetails;
import com.satckroute.applicationRegisterService.rabbitMQ.Seeker;
import com.satckroute.applicationRegisterService.rabbitMQ.User;
import com.satckroute.applicationRegisterService.rabbitMQ.UserDTO;
import com.satckroute.applicationRegisterService.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        UserDTO userDTO = new UserDTO(jobSeeker.getEmailId(),jobSeeker.getPassword(),jobSeeker.role.toString());
//        UserDTO userDTO = new UserDTO(jobSeeker.getEmailId(),jobSeeker.getPassword(),jobSeeker.getRole().toString());
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

//    @Override
//    public List<Recruiter> getAllRecruiterByFirstName(String firstName) throws RecruiterNotFoundException
//    {
//        if(recruiterRegisterRepository.findAllRecruiterByFirstName(firstName).isEmpty())
//        {
//            throw new RecruiterNotFoundException();
//        }
//        else
//        {
//            return recruiterRegisterRepository.findAllRecruiterByFirstName(firstName);
//        }
//    }

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
    public JobSeeker updateEducationDetails(String email, Education education) throws JobSeekerNotFoundException {
        List<Education> educationList = new ArrayList<>();
        JobSeeker jobSeeker1 = jobSeekerRegisterRepository.findById(email).get();
        if(jobSeeker1==null){
            throw new JobSeekerNotFoundException();
        }
        else{
            if(jobSeeker1.getEducationDetails()!=null){
                educationList.addAll(jobSeeker1.getEducationDetails());
                educationList.add(education);
                System.out.println(educationList);
            }else if(jobSeeker1.getEducationDetails()==null){
                educationList.add(education);

            }
            jobSeeker1.setEducationDetails(educationList);
            if (jobSeeker1.getProgress() < 100) {
                JobSeekerProgress jobSeekerProgress = new JobSeekerProgress();
                jobSeekerProgress.setAdditionalInfo(jobSeeker1.getSeekerProgress().getAdditionalInfo());
                jobSeekerProgress.setContactInfo(jobSeeker1.getSeekerProgress().getContactInfo());
                jobSeekerProgress.setPersonalInfo(jobSeeker1.getSeekerProgress().getPersonalInfo());
                jobSeekerProgress.setPersonalInfo(25);
                jobSeeker1.setProgress(jobSeeker1.getProgress()+25);
            }

            System.out.println(jobSeeker1);
        }
        return jobSeekerRegisterRepository.save(jobSeeker1);
    }

    @Override
    public JobSeeker updateJobSeekerDetails(JobSeeker jobSeeker, String emailId) throws JobSeekerNotFoundException
    {
        System.out.println("update details of jobseeker");
        Seeker seeker = new Seeker();
        if(jobSeekerRegisterRepository.findById(emailId).isEmpty())
        {
            throw new JobSeekerNotFoundException();
        }
        else
        {
            JobSeeker jobSeeker1 = jobSeekerRegisterRepository.findById(emailId).get();
            jobSeeker.setJobSeekerImage(jobSeeker1.getJobSeekerImage());
            jobSeeker.setProgress(jobSeeker.getSeekerProgress().getAdditionalInfo()+jobSeeker.getSeekerProgress().getContactInfo()+jobSeeker.getSeekerProgress().getPersonalInfo()+jobSeeker.getSeekerProgress().getEducationalInfo());

            ArrayList<String> education = new ArrayList<>();

            if(jobSeeker.getEducationDetails()!=null){
                for (Education courses:jobSeeker.getEducationDetails()){
                    education.add(courses.getCourses());
                    System.out.println(education);
                }
            }

//            if(jobSeeker.getAdditionalDetails().getSkillSet()!=null){
//                for (Skill skill:jobSeeker.getAdditionalDetails().getSkillSet()){
//                    skills.add(skill.getSkillName());
//                    System.out.println(skills);
//                }
//            }
            ArrayList<Skill> skillsAlreadyPresent= new ArrayList<>();
            if(jobSeeker.getAdditionalDetails().getSkillSet()!=null){
                System.out.println("skills");
                ArrayList<Skill> matchedSkills = new ArrayList<Skill>();
                ArrayList<Skill> skills = jobSeeker.getAdditionalDetails().getSkillSet();
                System.out.println("current skill set");
                System.out.println(skills);
                seeker.setSkillSet(skills);
                if(jobSeeker1.getAdditionalDetails().getSkillSet()!=null){
                    skillsAlreadyPresent = jobSeeker1.getAdditionalDetails().getSkillSet();
                    System.out.println("already present skills"+skillsAlreadyPresent);
                    for (Skill skill:skillsAlreadyPresent) {
                        for (Skill skill1:skills) {
                            if(skill.getSkillName().equalsIgnoreCase(skill1.getSkillName())){
                                matchedSkills.add(skill);

                            }
                        }
                    }
                    System.out.println("matched skills");
                    System.out.println(matchedSkills);
                }
                for (int i=0;i<matchedSkills.size();i++){
                    for (int j=0;j<skills.size();j++){
                        if(matchedSkills.get(i).getSkillName().equalsIgnoreCase(skills.get(j).getSkillName())){
                            skills.remove(j);
                        }
                    }
                }
                System.out.println("removed matched skills");
                System.out.println(skills);
                skillsAlreadyPresent.addAll(skills);

                Details details = new Details();
                details.setJobPreferences(jobSeeker.getAdditionalDetails().getJobPreferences());
                details.setAchievements(jobSeeker.getAdditionalDetails().getAchievements());
                details.setAcademicsCertification(jobSeeker.getAdditionalDetails().getAcademicsCertification());
                details.setSkillSet(skillsAlreadyPresent);

                jobSeeker.setAdditionalDetails(details);
            }
                seeker.setEmail(emailId);
                seeker.setEducation(education);
                producer.sendJobSeekerMessage(seeker);


            return jobSeekerRegisterRepository.save(jobSeeker);
        }

    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public JobSeeker updateJobSeekerDetail(JobSeeker jobSeeker, String emailId, MultipartFile file) throws JobSeekerNotFoundException, IOException
    {

        User user = new User();
        jobSeeker.setJobSeekerImage(file.getBytes());
        if(jobSeekerRegisterRepository.findById(emailId).isEmpty())
        {
            throw new JobSeekerNotFoundException();
        }
        else
        {

            jobSeeker.setProgress(jobSeeker.getSeekerProgress().getAdditionalInfo()+jobSeeker.getSeekerProgress().getContactInfo()+jobSeeker.getSeekerProgress().getPersonalInfo()+jobSeeker.getSeekerProgress().getEducationalInfo());
            user.setUserEmailId(emailId);
            user.setUserName(jobSeeker.getFirstName()+" "+jobSeeker.getLastName());

            producer.posting(user);
            producer.cvGeneration(jobSeeker);
//            user.setUserImage(file.getBytes());

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
            JobDetails jobDetails = new JobDetails();

            Recruiter recruiter1 = recruiterRegisterRepository.findById(emailId).get();
            recruiter.setLogo(recruiter1.getLogo());
            jobDetails.setEmailId(emailId);
            jobDetails.setSkillsRequired((ArrayList) recruiter.getSkillsRequired());
            jobDetails.setEducation(recruiter.getEducationRequired());
            producer.sendRecruiter(jobDetails);
            return recruiterRegisterRepository.save(recruiter);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public Recruiter updateRecruiterDetail(Recruiter recruiter, String emailId, MultipartFile file) throws RecruiterNotFoundException, IOException
    {
        recruiter.setLogo(file.getBytes());
        JobDetails jobDetails = new JobDetails();
        if(recruiterRegisterRepository.findById(emailId).isEmpty())
        {
            throw new RecruiterNotFoundException();
        }
        else
        {
            jobDetails.setEmailId(emailId);
            jobDetails.setSkillsRequired((ArrayList) recruiter.getSkillsRequired());
            jobDetails.setEducation(recruiter.getEducationRequired());
//            ---------- call the producer method
            producer.sendRecruiter(jobDetails);
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
        return jobSeekerRegisterRepository.findById(emailId).get().getAdditionalDetails().getSkillSet();
    }


    @Override
    public Recruiter addDetailsInRecruiter(Recruiter recruiter,String emailId)
    {

        Recruiter recruiter1 = recruiterRegisterRepository.findById(emailId).get();
                if(recruiter1!=null){
                    recruiter1.setEducationRequired(recruiter.getEducationRequired());
                    recruiter1.setSkillsRequired(recruiter.getSkillsRequired());
                    recruiterRegisterRepository.save(recruiter1);
                }
                return recruiter1;

    }

    @Override
    public List<JobSeeker> getJobSeekers(List<String> emailid){
        List<JobSeeker> jobSeekerList = new ArrayList<>();
        for (String email: emailid){
            JobSeeker jobSeeker = jobSeekerRegisterRepository.findById(email).get();
            if(jobSeeker!=null){
                jobSeekerList.add(jobSeeker);
            }
        }
      return jobSeekerList;
    }

    @Override
    public JobSeeker getJobseeker(String emailId) throws JobSeekerNotFoundException {
        System.out.println(emailId);
        System.out.println("get jobseeker");
        JobSeeker jobSeeker = jobSeekerRegisterRepository.findById(emailId).get();
        if(jobSeeker==null){
            throw new JobSeekerNotFoundException();
        }
        return jobSeeker;
    }

    @Override
    public JobSeeker updateDetails(String emaild, Skill skill) throws JobSeekerNotFoundException {
        JobSeeker jobSeeker = jobSeekerRegisterRepository.findById(emaild).get();
        if(jobSeeker==null){
            throw new JobSeekerNotFoundException();
        }
        Details details = new Details();
        ArrayList<Skill> skillSet=jobSeeker.getAdditionalDetails().getSkillSet();
        String[] academicsCertification=jobSeeker.getAdditionalDetails().getAcademicsCertification();
        String[] jobPreferences=jobSeeker.getAdditionalDetails().getJobPreferences();
        String[] achievements=jobSeeker.getAdditionalDetails().getAchievements();
        details.setAchievements(achievements);
        details.setAcademicsCertification(academicsCertification);
        details.setJobPreferences(jobPreferences);

        for (int i=0;i< skillSet.size();i++){
            if(skillSet.get(i).getSkillName().equalsIgnoreCase(skill.getSkillName())){
                skillSet.set(i,skill);
            }
        }
        details.setSkillSet(skillSet);
        jobSeeker.setAdditionalDetails(details);
        System.out.println(jobSeeker);
        return jobSeekerRegisterRepository.save(jobSeeker);
    }

    @Override
    public Recruiter selecteJobSeeker(String recruiterEmail,String jobSeekerEmail) throws JobSeekerNotFoundException, RecruiterNotFoundException {
        Recruiter recruiter = recruiterRegisterRepository.findById(recruiterEmail).get();
        JobSeeker jobSeeker = jobSeekerRegisterRepository.findById(jobSeekerEmail).get();
        if(jobSeeker==null){
            throw new JobSeekerNotFoundException();
        }
        if(recruiter==null){
            throw new RecruiterNotFoundException();
        }
        if(jobSeeker!=null&&recruiter!=null){
            List<JobSeeker> jobSeekerList = recruiter.getSelectedJobSeekers();
            List<JobSeeker> shortListedList = new ArrayList<>();
            if(jobSeekerList!=null){
                for (JobSeeker jobSeeker1:jobSeekerList) {
                    if(jobSeeker1.getEmailId()!=jobSeekerEmail){
                        shortListedList.addAll(jobSeekerList);
                        shortListedList.add(jobSeeker);
                    }
                }
            }else {
                shortListedList.add(jobSeeker);
            }

            recruiter.setSelectedJobSeekers(shortListedList);
            recruiterRegisterRepository.save(recruiter);
        }
        return recruiter;
    }

    @Override
    public JobSeeker getJobSeeker(String email,String recruiterEmail) throws JobSeekerNotFoundException, RecruiterNotFoundException {
        JobSeeker jobSeeker = null;
        Recruiter recruiter = recruiterRegisterRepository.findById(recruiterEmail).get();
        if(jobSeekerRegisterRepository.findById(email).isEmpty()){
            throw  new JobSeekerNotFoundException();
        }
        if(recruiter==null){
            throw new RecruiterNotFoundException();
        }
        if(recruiter!=null){
            System.out.println("job and rec not null");
            List<JobSeeker> jobSeekerList = recruiter.getSelectedJobSeekers();
            System.out.println(jobSeekerList.size());
            boolean flag = false;
            if(jobSeekerList!=null){
                for (JobSeeker jobSeeker1:jobSeekerList) {
                    System.out.println(jobSeeker1.getEmailId());
                    if((jobSeeker1.getEmailId().equalsIgnoreCase(email))){
                        flag = true;
                        System.out.println(jobSeeker1.getEmailId()+" "+email);
                    }
                }
            }
            if(!flag){
                jobSeeker = jobSeekerRegisterRepository.findById(email).get();
            }
        }
        return jobSeeker;
    }


}


