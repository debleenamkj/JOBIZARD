//package com.satckroute.applicationRegisterService.ServiceTests;
//
//import com.satckroute.applicationRegisterService.domain.*;
//import com.satckroute.applicationRegisterService.exception.JobSeekerNotFoundException;
//import com.satckroute.applicationRegisterService.exception.RecruiterNotFoundException;
//import com.satckroute.applicationRegisterService.repository.JobSeekerRegisterRepository;
//import com.satckroute.applicationRegisterService.repository.RecruiterRegisterRepository;
//import com.satckroute.applicationRegisterService.service.RegisterServiceImpl;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class RecruiterRegisterImplTests
//{
//    @InjectMocks
//    private RegisterServiceImpl registerServiceImpl;
//
//    @Mock
//    private RecruiterRegisterRepository recruiterRegisterRepository;
//
//    private Recruiter recruiter;
//    private Address address;
//    private OrganizationDetails organizationDetails;
//
//    private List<Recruiter> recruiterList;
//
////----------------------------------------------------------------------------------------------------------------------
//
//    @BeforeEach
//    public void setUp()
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
//        recruiterList= Arrays.asList(recruiter);
//    }
//
//
////----------------------------------------------------------------------------------------------------------------------
//
//    @AfterEach
//    public void tearDown()
//    {
//        recruiter = null;
//    }
//
////----------------------------------------------------------------------------------------------------------------------
////  getAllRecruiter
//
//    //positive test case
//    @Test
//    public void showAllRecruiter() throws Exception
//    {
//        when(recruiterRegisterRepository.findAll()).thenReturn(recruiterList);
//        assertEquals(recruiterList,registerServiceImpl.getAllRecruiter());
//        verify(recruiterRegisterRepository,times(1)).findAll();
//    }
//
//    //negative test case
//    @Test
//    public void showAllJobSeekerFailure() throws Exception
//    {
//        when(recruiterRegisterRepository.findAll()).thenReturn(null);
//        assertNotEquals(recruiterList,registerServiceImpl.getAllRecruiter());
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
////  getAllRecruiterByFirstName
//
//
//    //positive test case
////    @Test
////    public void showRecruiterByFirstName() throws RecruiterNotFoundException
////    {
////        when(recruiterRegisterRepository.findAllRecruiterByFirstName(recruiter.getFirstName())).thenReturn(Optional.ofNullable(recruiterList).get());
////        assertEquals(recruiterList,registerServiceImpl.getAllRecruiterByFirstName("FirstName1"));
////        verify(recruiterRegisterRepository,times(2)).findAllRecruiterByFirstName("FirstName1");
////    }
////
////    //negative test case
////    @Test
////    public void showRecruiterByFirstNameFailure()
////    {
////        when(recruiterRegisterRepository.findAllRecruiterByFirstName(recruiter.getFirstName())).thenReturn(null);
////        assertThrows(Exception.class,()->registerServiceImpl.getAllRecruiterByFirstName(recruiter.getFirstName()));
////        verify(recruiterRegisterRepository,times(1)).findAllRecruiterByFirstName(recruiter.getFirstName());
////    }
//
////----------------------------------------------------------------------------------------------------------------------
//
////    updateRecruiterDetails
//
//    //positive test case
//    @Test
//    public void updateRecruiterDetails() throws RecruiterNotFoundException
//    {
//        when(recruiterRegisterRepository.findById(recruiter.getEmailId())).thenReturn(Optional.ofNullable(recruiter));
//        recruiter.setEmailId("recruiter@gmail.com");
//        when(recruiterRegisterRepository.save(recruiter)).thenReturn(recruiter);
//
//        assertEquals("recruiter@gmail.com",registerServiceImpl.updateRecruiterDetails(recruiter, recruiter.getEmailId()).getEmailId());
//
//        verify(recruiterRegisterRepository,times(1)).findById(recruiter.getEmailId());
//        verify(recruiterRegisterRepository,times(1)).save(recruiter);
//    }
//
//    //negative test case
//    @Test
//    public void updateRecruiterDetailsFailure()
//    {
//        when(recruiterRegisterRepository.findById(recruiter.getEmailId())).thenReturn(Optional.ofNullable(null));
//        assertThrows(RecruiterNotFoundException.class,()->registerServiceImpl.updateRecruiterDetails(recruiter,recruiter.getEmailId()));
//        verify(recruiterRegisterRepository,times(1)).findById(recruiter.getEmailId());
//        verify(recruiterRegisterRepository,times(0)).save(recruiter);
//    }
//
////----------------------------------------------------------------------------------------------------------------------
////    deleteRecruiterDetails
//
//    //positive test case
//    @Test
//    public void deleteRecruiterDetails() throws RecruiterNotFoundException
//    {
//        when(recruiterRegisterRepository.findById(recruiter.getEmailId())).thenReturn(Optional.ofNullable(recruiter));
//        boolean flag = registerServiceImpl.deleteRecruiterDetails(recruiter.getEmailId());
//        assertEquals(true,flag);
//
//        verify(recruiterRegisterRepository,times(1)).findById(any());
//        verify(recruiterRegisterRepository,times(1)).deleteById(any());
//        //or
//        //verify(productRepository,times(1)).findById(product1.getProductCode());
//        //verify(productRepository,times(1)).deleteById(product1.getProductCode());
//    }
//
//    //negative test case
//    @Test
//    public void deleteRecruiterDetailsFailure()
//    {
//        when(recruiterRegisterRepository.findById(recruiter.getEmailId())).thenReturn(Optional.ofNullable(null));
//        assertThrows(RecruiterNotFoundException.class,()->registerServiceImpl.deleteRecruiterDetails(recruiter.getEmailId()));
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
//}