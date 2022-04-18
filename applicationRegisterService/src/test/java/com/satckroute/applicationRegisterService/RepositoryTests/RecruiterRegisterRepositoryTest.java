//package com.satckroute.applicationRegisterService.RepositoryTests;
//
//
//import com.satckroute.applicationRegisterService.domain.Address;
//import com.satckroute.applicationRegisterService.domain.OrganizationDetails;
//import com.satckroute.applicationRegisterService.domain.Recruiter;
//import com.satckroute.applicationRegisterService.repository.*;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.text.ParseException;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//
//@ExtendWith(SpringExtension.class)
//@DataMongoTest
//public class RecruiterRegisterRepositoryTest
//{
//    @Autowired
//    private RecruiterRegisterRepository recruiterRegisterRepository;
//
//    // not needed
////    private OrganizationDetailsRepository organizationDetailsRepository;
////    private AddressDetailsRepository addressDetailsRepository;
//
//
//    private Recruiter recruiter;
//    private OrganizationDetails organizationDetails;
//    private Address address;
//
////----------------------------------------------------------------------------------------------------------------------
//
//    @BeforeEach
//    public void setUp() throws ParseException
//    {
//        recruiter= new Recruiter();
//        recruiter.setEmailId("recruiter@gmail.com");
////        recruiter.setFirstName("FirstName1");
////        recruiter.setLastName("LastName1");
////        recruiter.setGender("MALE");
////        recruiter.setMobileNumber("7894560123");
//        recruiter.setPassword("pass123");
////        recruiter.setAddressDetails(address);
////        recruiter.setOrganizationDetails(organizationDetails);
////        recruiter.setRecruiterImage();
//
//
//        // not needed
////        address =new Address();
////        address.setCity("Mumbai");
////        address.setState("MAHARASHTRA");
////        address.setPinCode(12450);
////        address.setNationality("INDIAN");
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
//    @AfterEach
//    public void tearDown()
//    {
//
//        recruiter = null;
//        organizationDetails = null;
//        address = null;
//
//        recruiterRegisterRepository.deleteAll();
////        organizationDetailsRepository.deleteAll();
////        addressDetailsRepository.deleteAll();
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
////    registerNewRecruiter
//
//    @Test
//    public void saveNewRecruiterDetails()
//    {
//        recruiterRegisterRepository.insert(recruiter);
//        Recruiter recruiter1  = recruiterRegisterRepository.findById(recruiter.getEmailId()).get();
//        assertEquals(recruiter.getEmailId(),recruiter1.getEmailId());
//    }
//
//
//    //negative test case
//    @Test
//    public void saveNewRecruiterDetailsFailure()
//    {
//        recruiterRegisterRepository.insert(recruiter);
//        Recruiter recruiter1  = recruiterRegisterRepository.findById(recruiter.getEmailId()).get();
//        assertNotNull(recruiter1);
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
////    getAllRecruiter
//
//    //positive test case
//    @Test
//    public void showAllRecruiters()
//    {
//        recruiterRegisterRepository.insert(recruiter);
//        List<Recruiter> list=recruiterRegisterRepository.findAll();
//        assertEquals(1,list.size());
//        assertEquals("recruiter@gmail.com",list.get(0).getEmailId());
//    }
//
//
//    //negative test case
//    @Test
//    public void showAllRecruitersFailure()
//    {
//        recruiterRegisterRepository.insert(recruiter);
//        List<Recruiter> list=recruiterRegisterRepository.findAll();
//        assertNotEquals(0,list.size());
//        assertNotEquals(2,list.size());
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
////    getAllRecruiterByFirstName
//
//    //positive test case
////    @Test
////    public void showAllRecruiterByFirstName()
////    {
////        recruiterRegisterRepository.insert(recruiter);
////        Recruiter recruiter1 = recruiterRegisterRepository.findById(recruiter.getEmailId()).get();
////        List<Recruiter> list = recruiterRegisterRepository.findAllRecruiterByFirstName("FirstName1");
////        assertEquals("FirstName1",list.get(0).getFirstName());
////    }
////
////
////    //negative test case
////    @Test
////    public void showAllRecruiterByFirstNameFailure()
////    {
////        recruiterRegisterRepository.insert(recruiter);
////        Recruiter recruiter1 = recruiterRegisterRepository.findById(recruiter.getEmailId()).get();
////        List<Recruiter> list = recruiterRegisterRepository.findAllRecruiterByFirstName("FirstName1");
////        assertNotEquals("FirstName",list.get(0).getFirstName());
////    }
//
////----------------------------------------------------------------------------------------------------------------------
//
////    updateRecruiterDetails
//
//    //positive test case
//    @Test
//    public void updateRecruiterDetails()
//    {
//        recruiterRegisterRepository.save(recruiter);
//        recruiter.setEmailId("recruiter@gmail.com");
//        Recruiter recruiter1 = recruiterRegisterRepository.findById(recruiter.getEmailId()).get();
//        recruiterRegisterRepository.save(recruiter1);
//        assertEquals(recruiter.getEmailId(),recruiter1.getEmailId());
//        assertEquals("recruiter@gmail.com",recruiter.getEmailId());
//    }
//
//
//    //negative test case
//    @Test
//    public void updateRecruiterDetailsFailure()
//    {
//        recruiterRegisterRepository.save(recruiter);
//        recruiter.setEmailId("recruiter@gmail.com");
//        Recruiter recruiter1 = recruiterRegisterRepository.findById(recruiter.getEmailId()).get();
//        recruiterRegisterRepository.save(recruiter1);
//        assertNotEquals("recruiter@gmail.com",recruiter.getEmailId());
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
////    deleteRecruiterDetails
//
//    //positive test case
//    @Test
//    public void deleteRecruiterDetail()
//    {
//        recruiterRegisterRepository.insert(recruiter);
//        Recruiter recruiter1 = recruiterRegisterRepository.findById(recruiter.getEmailId()).get();
//        recruiterRegisterRepository.delete(recruiter1);
//        assertEquals(Optional.empty(),recruiterRegisterRepository.findById(recruiter.getEmailId()));
//    }
//
//
//    //negative test case
//    @Test
//    public void deleteRecruiterDetailFailure()
//    {
//        recruiterRegisterRepository.insert(recruiter);
//        Recruiter recruiter1 = recruiterRegisterRepository.findById(recruiter.getEmailId()).get();
//        recruiterRegisterRepository.delete(recruiter1);
//        assertNotEquals(1,recruiterRegisterRepository.findById(recruiter.getEmailId()));
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
//}
