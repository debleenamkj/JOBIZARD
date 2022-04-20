//package com.satckroute.applicationRegisterService.RepositoryTests;
//
//import com.satckroute.applicationRegisterService.domain.Address;
//import com.satckroute.applicationRegisterService.domain.OrganizationDetails;
//import com.satckroute.applicationRegisterService.repository.OrganizationDetailsRepository;
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
//
//@ExtendWith(SpringExtension.class)
//@DataMongoTest
//public class OrganizationDetailsRepositoryTests
//{
//    @Autowired
//    private OrganizationDetailsRepository organizationDetailsRepository;
//
//    // not needed
////    private JobSeekerRegisterRepository jobSeekerRegisterRepository;
////    private RecruiterRegisterRepository recruiterRegisterRepository;
////    private EducationDetailsRepository educationDetailsRepository;
////    private AddressDetailsRepository addressDetailsRepository;
//
//
//    private OrganizationDetails organizationDetails;
//    private Address address;
//
//    // not needed
////    private JobSeeker jobSeeker;
////    private Details details;
////    private Recruiter recruiter;
////    private Education education;
//
////----------------------------------------------------------------------------------------------------------------------
//
//    @BeforeEach
//    public void setUp() throws ParseException
//    {
//        organizationDetails = new OrganizationDetails();
//
//        organizationDetails.setEmailId("organization@gmail.com");
//        organizationDetails.setOrganizationName("ORG");
//        organizationDetails.setOrganizationSector("IT");
//        organizationDetails.setOrganizationAddress(address);
//        organizationDetails.setContactNumber("7894560123");
//        organizationDetails.setPassword("pass1233");
////        organizationDetails.setOrganizationLogo();
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
//    @AfterEach
//    public void tearDown()
//    {
//        organizationDetails = null;
//        address = null;
//
//        //not needed
////        jobSeeker = null;
////        details = null;
////        recruiter = null;
//
//        organizationDetailsRepository.deleteAll();
//
//        // not needed
////        jobSeekerRegisterRepository.deleteAll();
////        recruiterRegisterRepository.deleteAll();
////        educationDetailsRepository.deleteAll();
////        addressDetailsRepository.deleteAll();
//    }
////----------------------------------------------------------------------------------------------------------------------
//
////    saveOrganizationDetails
//
//    @Test
//    public void saveNewOrganizationDetails()
//    {
//        organizationDetailsRepository.insert(organizationDetails);
//        OrganizationDetails organizationDetails1  = organizationDetailsRepository.findById(organizationDetails.getEmailId()).get();
//        assertEquals(organizationDetails.getEmailId(),organizationDetails1.getEmailId());
//    }
//
//
//    //negative test case
//    @Test
//    public void saveNewOrganizationDetailsFailure()
//    {
//        organizationDetailsRepository.insert(organizationDetails);
//        OrganizationDetails organizationDetails1  = organizationDetailsRepository.findById(organizationDetails.getEmailId()).get();
//        assertNotNull(organizationDetails1);
//    }
////----------------------------------------------------------------------------------------------------------------------
//
////    getAllOrganization
//
//    //positive test case
//    @Test
//    public void showAllOrganizations()
//    {
//        organizationDetailsRepository.insert(organizationDetails);
//        List<OrganizationDetails> list=organizationDetailsRepository.findAll();
//        assertEquals(1,list.size());
//        assertEquals("ORG",list.get(0).getOrganizationName());
//    }
//
//
//    //negative test case
//    @Test
//    public void showAllOrganizationsFailure()
//    {
//        organizationDetailsRepository.insert(organizationDetails);
//        List<OrganizationDetails> list=organizationDetailsRepository.findAll();
//        assertNotEquals(0,list.size());
//        assertNotEquals(2,list.size());
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
////    getAllOrganizationDetailsByOrganizationName
//
//    //positive test case
//    @Test
//    public void showAllOrganizationDetailsByOrganizationName()
//    {
//        organizationDetailsRepository.insert(organizationDetails);
//        OrganizationDetails organizationDetails1 = organizationDetailsRepository.findById(organizationDetails.getEmailId()).get();
//        List<OrganizationDetails> list = organizationDetailsRepository.findAllOrganizationByOrganizationName("ORG");
//        assertEquals("ORG",list.get(0).getOrganizationName());
//    }
//
//
//    //negative test case
//    @Test
//    public void showAllOrganizationDetailsByOrganizationNameFailure()
//    {
//        organizationDetailsRepository.insert(organizationDetails);
//        OrganizationDetails organizationDetails1 = organizationDetailsRepository.findById(organizationDetails.getEmailId()).get();
//        List<OrganizationDetails> list = organizationDetailsRepository.findAllOrganizationByOrganizationName("ORG");
//        assertNotEquals("Org",list.get(0).getOrganizationName());
//    }
////----------------------------------------------------------------------------------------------------------------------
//
////    updateOrganizationDetails
//
//    //positive test case
//    @Test
//    public void updateOrganizationDetails()
//    {
//        organizationDetailsRepository.save(organizationDetails);
//        organizationDetails.setOrganizationName("ORG");
//        OrganizationDetails organizationDetails1 = organizationDetailsRepository.findById(organizationDetails.getEmailId()).get();
//        organizationDetailsRepository.save(organizationDetails);
//        assertEquals(organizationDetails.getEmailId(),organizationDetails1.getEmailId());
//        assertEquals("ORG",organizationDetails.getOrganizationName());
//    }
//
//
//    //negative test case
//    @Test
//    public void updateOrganizationDetailsFailure()
//    {
//        organizationDetailsRepository.save(organizationDetails);
//        organizationDetails.setOrganizationName("ORG");
//        OrganizationDetails organizationDetails1 = organizationDetailsRepository.findById(organizationDetails.getEmailId()).get();
//        organizationDetailsRepository.save(organizationDetails);
//        assertNotEquals("Org",organizationDetails.getOrganizationName());
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
////    deleteOrganizationDetails
//
//    //positive test case
//    @Test
//    public void deleteOrganizationDetail()
//    {
//        organizationDetailsRepository.insert(organizationDetails);
//        OrganizationDetails organizationDetails1 = organizationDetailsRepository.findById(organizationDetails.getEmailId()).get();
//        organizationDetailsRepository.delete(organizationDetails1);
//        assertEquals(Optional.empty(),organizationDetailsRepository.findById(organizationDetails.getEmailId()));
//    }
//
//
//    //negative test case
//    @Test
//    public void deleteOrganizationDetailFailure()
//    {
//        organizationDetailsRepository.insert(organizationDetails);
//        OrganizationDetails organizationDetails1 = organizationDetailsRepository.findById(organizationDetails.getEmailId()).get();
//        organizationDetailsRepository.delete(organizationDetails1);
//        assertNotEquals(1,organizationDetailsRepository.findById(organizationDetails.getEmailId()));
//    }
////----------------------------------------------------------------------------------------------------------------------
//
//}
