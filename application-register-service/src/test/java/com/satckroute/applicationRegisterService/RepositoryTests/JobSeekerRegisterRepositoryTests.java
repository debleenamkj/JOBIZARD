//package com.satckroute.applicationRegisterService.RepositoryTests;
//
//import com.satckroute.applicationRegisterService.domain.*;
//import com.satckroute.applicationRegisterService.repository.*;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@ExtendWith(SpringExtension.class)
//@DataMongoTest
//public class JobSeekerRegisterRepositoryTests
//{
//
//    @Autowired
//    private JobSeekerRegisterRepository jobSeekerRegisterRepository;
//
//    //not needed
////    private RecruiterRegisterRepository recruiterRegisterRepository;
////    private OrganizationDetailsRepository organizationDetailsRepository;
////    private EducationDetailsRepository educationDetailsRepository;
////    private AddressDetailsRepository addressDetailsRepository;
//
//    private JobSeeker jobSeeker;
//    private Address address;
//    private Details details;
//
//    //not needed
////    private Recruiter recruiter;
////    private OrganizationDetails organizationDetails;
////    private Education education;
//
//
////    String[] languagesKnown = new String[5];
////----------------------------------------------------------------------------------------------------------------------
//
//    @BeforeEach
//    public void setUp() throws ParseException
//    {
//        String testDate = "29-Apr-2010,13:00:14 PM";
//        DateFormat formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss aaa");
//        Date date = formatter.parse(testDate);
//        System.out.println(date);
//
////        ,Role.JOBSEEKER
//        jobSeeker = new JobSeeker("emailId@gmail.com","FirstName01","LastName1",
//                "GENDER1",date,"1234567890","Password1",address,details);
//
//
//
//
////        jobSeeker = new JobSeeker();
////        jobSeeker.setEmailId("jobSeeker@gmail.com");
////        jobSeeker.setFirstName("FirstName1");
////        jobSeeker.setLastName("LastName1");
////        jobSeeker.setGender("MALE");
////        jobSeeker.setMobileNumber("7894561230");
////        jobSeeker.setPassword("pass123");
////        jobSeeker.setEducationDetails(new ArrayList<>());
////        jobSeeker.setLanguagesKnown(languagesKnown);
////        jobSeeker.setJobSeekerImage();
//
//
//        // not needed
////        recruiter= new Recruiter();
////        recruiter.setEmailId("recruiter@gmail.com");
////        recruiter.setFirstName("FirstName1");
////        recruiter.setLastName("LastName1");
////        recruiter.setGender("MALE");
////        recruiter.setMobileNumber("7894560123");
////        recruiter.setPassword("pass123");
////        recruiter.setAddressDetails(address);
////        recruiter.setOrganizationDetails(organizationDetails);
//////        recruiter.setRecruiterImage();
////
////        organizationDetails = new OrganizationDetails();
////        organizationDetails.setEmailId("organization@gmail.com");
////        organizationDetails.setOrganizationName("XYZ");
////        organizationDetails.setOrganizationSector("IT");
////        organizationDetails.setOrganizationAddress(address);
////        organizationDetails.setContactNumber("7894560123");
////        organizationDetails.setPassword("pass1233");
////        organizationDetails.setOrganizationLogo();
//
//
////        address =new Address();
////        address.setCity("Mumbai");
////        address.setState("MAHARASHTRA");
////        address.setPinCode(12450);
////        address.setNationality("INDIAN");
//
//
//    }
//
//
////----------------------------------------------------------------------------------------------------------------------
//
//    @AfterEach
//    public void tearDown()
//    {
//
//        jobSeeker = null;
//        address = null;
//
//        //not needed
////        organizationDetails = null;
////        details = null;
////        recruiter = null;
//
//
//        jobSeekerRegisterRepository.deleteAll();
//
//        //not needed
////        recruiterRegisterRepository.deleteAll();
////        organizationDetailsRepository.deleteAll();
////        educationDetailsRepository.deleteAll();
////        addressDetailsRepository.deleteAll();
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
////    registerNewJobSeeker
//
//    //positive test case
//    @Test
//    public void saveNewJobSeekerDetails()
//    {
//        jobSeekerRegisterRepository.insert(jobSeeker);
//        JobSeeker jobSeeker1  = jobSeekerRegisterRepository.findById(jobSeeker.getEmailId()).get();
//        assertEquals(jobSeeker.getEmailId(),jobSeeker1.getEmailId());
//    }
//
//    //negative test case
//    @Test
//    public void saveNewJobSeekerDetailsFailure()
//    {
//        jobSeekerRegisterRepository.insert(jobSeeker);
//        JobSeeker jobSeeker1 = jobSeekerRegisterRepository.findById(jobSeeker.getEmailId()).get();
//        assertNotNull(jobSeeker1);
//    }
//
//
////----------------------------------------------------------------------------------------------------------------------
//
////  getAllJobSeeker
//
//    //positive test case
//    @Test
//    public void showAllJobSeekers()
//    {
//        jobSeekerRegisterRepository.insert(jobSeeker);
//        List<JobSeeker> list=jobSeekerRegisterRepository.findAll();
//        assertEquals(1,list.size());
//        assertEquals("FirstName01",list.get(0).getFirstName());
//    }
//
//    //negative test case
//    @Test
//    public void showAllJobSeekersFailure()
//    {
//        jobSeekerRegisterRepository.insert(jobSeeker);
//        List<JobSeeker> list=jobSeekerRegisterRepository.findAll();
//        assertNotEquals(0,list.size());
//        assertNotEquals(2,list.size());
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
////    getAllJobSeekerByFirstName
//
//    //positive test case
//    @Test
//    public void showAllJobSeekerByFirstName()
//    {
//        jobSeekerRegisterRepository.insert(jobSeeker);
//        JobSeeker jobSeeker1 = jobSeekerRegisterRepository.findById(jobSeeker.getEmailId()).get();
//        List<JobSeeker> list = jobSeekerRegisterRepository.findAllJobSeekerByFirstName("FirstName01");
//        assertEquals("FirstName01",list.get(0).getFirstName());
//    }
//
//
//    //negative test case
//    @Test
//    public void showAllJobSeekerByFirstNameFailure()
//    {
//        jobSeekerRegisterRepository.insert(jobSeeker);
//        JobSeeker jobSeeker1 = jobSeekerRegisterRepository.findById(jobSeeker.getEmailId()).get();
//        List<JobSeeker> list = jobSeekerRegisterRepository.findAllJobSeekerByFirstName("FirstName01");
//        assertNotEquals("firstName1",list.get(0).getFirstName());
//    }
//
//
////----------------------------------------------------------------------------------------------------------------------
//
////    updateJobSeekerDetails
//
//    //positive test case
//    @Test
//    public void updateJobSeekerDetails()
//    {
//        jobSeekerRegisterRepository.save(jobSeeker);
//        jobSeeker.setFirstName("FirstName2");
//        JobSeeker jobSeeker1 = jobSeekerRegisterRepository.findById(jobSeeker.getEmailId()).get();
//        jobSeekerRegisterRepository.save(jobSeeker);
//        assertEquals(jobSeeker.getEmailId(),jobSeeker1.getEmailId());
//        assertEquals("FirstName2",jobSeeker.getFirstName());
//    }
//
//
//    //negative test case
//    @Test
//    public void updateJobSeekerDetailsFailure()
//    {
//        jobSeekerRegisterRepository.save(jobSeeker);
//        jobSeeker.setFirstName("FirstName01");
//        JobSeeker jobSeeker1 = jobSeekerRegisterRepository.findById(jobSeeker.getEmailId()).get();
//        jobSeekerRegisterRepository.save(jobSeeker);
//        assertNotEquals("FirstName2",jobSeeker.getFirstName());
//    }
//
//
////----------------------------------------------------------------------------------------------------------------------
//
////    deleteJobSeekerDetails
//
//    //positive test case
//    @Test
//    public void deleteJobSeekerDetail()
//    {
//        jobSeekerRegisterRepository.insert(jobSeeker);
//        JobSeeker jobSeeker1=jobSeekerRegisterRepository.findById(jobSeeker.getEmailId()).get();
//        jobSeekerRegisterRepository.delete(jobSeeker1);
//        assertEquals(Optional.empty(),jobSeekerRegisterRepository.findById(jobSeeker.getEmailId()));
//        assertEquals(Optional.empty(),jobSeekerRegisterRepository.findById((jobSeeker.getEmailId())));
//    }
//
//
//    //negative test case
//    @Test
//    public void deleteJobSeekerDetailFailure()
//    {
//        jobSeekerRegisterRepository.insert(jobSeeker);
//        JobSeeker jobSeeker1=jobSeekerRegisterRepository.findById(jobSeeker.getEmailId()).get();
//        jobSeekerRegisterRepository.delete(jobSeeker1);
//        assertNotEquals(1,jobSeekerRegisterRepository.findById(jobSeeker.getEmailId()));
//    }
//
//
////----------------------------------------------------------------------------------------------------------------------
//
////----------------------------------------------------------------------------------------------------------------------
//
////----------------------------------------------------------------------------------------------------------------------
//
//}
