//package com.satckroute.applicationRegisterService.ServiceTests;
//
//
//import com.satckroute.applicationRegisterService.config.Producer;
//import com.satckroute.applicationRegisterService.domain.Address;
//import com.satckroute.applicationRegisterService.domain.Details;
//import com.satckroute.applicationRegisterService.domain.JobSeeker;
//import com.satckroute.applicationRegisterService.domain.Role;
//import com.satckroute.applicationRegisterService.exception.JobSeekerAlreadyExistException;
//import com.satckroute.applicationRegisterService.exception.JobSeekerNotFoundException;
//import com.satckroute.applicationRegisterService.rabbitMQ.UserDTO;
//import com.satckroute.applicationRegisterService.repository.JobSeekerRegisterRepository;
//import com.satckroute.applicationRegisterService.service.RegisterServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class JobSeekerRegisterServiceTests
//{
//    @InjectMocks
//    private RegisterServiceImpl registerServiceImpl;
//
//    @Mock
//    private JobSeekerRegisterRepository jobSeekerRegisterRepository;
//
//    private JobSeeker jobSeeker;
//    private Address address;
//    private Details details;
//
//    private List<JobSeeker> jobSeekerList;
//
//    private UserDTO userDTO;
//
//    @Autowired
//    private Producer producer;
//
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
////        , Role.JOBSEEKER
//        jobSeeker = new JobSeeker("emailId@gmail.com","FirstName01","LastName1",
//                "GENDER1",date,"1234567890","Password1",address,details);
//
//        jobSeekerList= Arrays.asList(jobSeeker);
//
////        ,"JOBSEEKER"
//        userDTO = new UserDTO("abc@mail.com","pass123","JOBSEEKER");
//    }
//
//
////----------------------------------------------------------------------------------------------------------------------
//
//    @AfterEach
//    public void tearDown()
//    {
//        jobSeeker = null;
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
////  registerNewJobSeeker
//
//
//    //positive test case
////    @Test
////    public void saveJobSeekerDetails() throws JobSeekerAlreadyExistException , NullPointerException
////    {
////        when(jobSeekerRegisterRepository.findById(jobSeeker.getEmailId())).thenReturn(Optional.ofNullable(null));
////        when(jobSeekerRegisterRepository.save(jobSeeker)).thenReturn(jobSeeker);
//////        when(producer.sendMessage(userDTO)).thenReturn(jobSeeker);
////
////        assertEquals(jobSeeker,registerServiceImpl.registerNewJobSeeker(jobSeeker));
////
////        verify(jobSeekerRegisterRepository,times(1)).findById(jobSeeker.getEmailId());
////        verify(jobSeekerRegisterRepository,times(1)).save(jobSeeker);
////                                //or
////        //verify(productRepository,times(1)).findById(any());
////        //verify(productRepository,times(1)).save(any());
////    }
//
//
//    //negative test case
////    @Test
////    public void saveJobSeekerFailure()
////    {
////        when(jobSeekerRegisterRepository.findById(jobSeeker.getEmailId())).thenReturn(Optional.ofNullable(null));
////
////        assertThrows(JobSeekerAlreadyExistException.class,()->registerServiceImpl.registerNewJobSeeker(jobSeeker));
////
////        verify(jobSeekerRegisterRepository,times(1)).findById(jobSeeker.getEmailId());
////        verify(jobSeekerRegisterRepository,times(0)).save(jobSeeker);
////    }
//
////----------------------------------------------------------------------------------------------------------------------
//
////  getAllJobSeeker
//
//    //positive test case
//    @Test
//    public void showAllJobSeeker() throws Exception
//    {
//        when(jobSeekerRegisterRepository.findAll()).thenReturn(jobSeekerList);
//        assertEquals(jobSeekerList,registerServiceImpl.getAllJobSeeker());
//        verify(jobSeekerRegisterRepository,times(1)).findAll();
//    }
//
//    //negative test case
//    @Test
//    public void showAllJobSeekerFailure() throws Exception
//    {
//        when(jobSeekerRegisterRepository.findAll()).thenReturn(null);
//        assertNotEquals(jobSeekerList,registerServiceImpl.getAllJobSeeker());
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
////  getAllJobSeekerByFirstName
//
//
//    //positive test case
//    @Test
//    public void showJobSeekerByFirstName() throws JobSeekerNotFoundException
//    {
//        when(jobSeekerRegisterRepository.findAllJobSeekerByFirstName(jobSeeker.getFirstName())).thenReturn(Optional.ofNullable(jobSeekerList).get());
//        assertEquals(jobSeekerList,registerServiceImpl.getAllJobSeekerByFirstName("FirstName01"));
//        verify(jobSeekerRegisterRepository,times(2)).findAllJobSeekerByFirstName("FirstName01");
//    }
//
//    //negative test case
//    @Test
//    public void showJobSeekerByFirstNameFailure()
//    {
//        when(jobSeekerRegisterRepository.findAllJobSeekerByFirstName(jobSeeker.getFirstName())).thenReturn(null);
//        assertThrows(Exception.class,()->registerServiceImpl.getAllJobSeekerByFirstName(jobSeeker.getFirstName()));
//        verify(jobSeekerRegisterRepository,times(1)).findAllJobSeekerByFirstName(jobSeeker.getFirstName());
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
////    updateJobSeekerDetails
//
//
//    //positive test case
//    @Test
//    public void updateJobSeekerDetails() throws JobSeekerNotFoundException
//    {
//        when(jobSeekerRegisterRepository.findById(jobSeeker.getEmailId())).thenReturn(Optional.ofNullable(jobSeeker));
//        jobSeeker.setFirstName("FirstName01");
//        when(jobSeekerRegisterRepository.save(jobSeeker)).thenReturn(jobSeeker);
//
//        assertEquals("FirstName01",registerServiceImpl.updateJobSeekerDetails(jobSeeker, jobSeeker.getEmailId()).getFirstName());
//
//        verify(jobSeekerRegisterRepository,times(1)).findById(jobSeeker.getEmailId());
//        verify(jobSeekerRegisterRepository,times(1)).save(jobSeeker);
//    }
//
//
//    //negative test case
//    @Test
//    public void updateJobSeekerDetailsFailure()
//    {
//        when(jobSeekerRegisterRepository.findById(jobSeeker.getEmailId())).thenReturn(Optional.ofNullable(null));
//        assertThrows(JobSeekerNotFoundException.class,()->registerServiceImpl.updateJobSeekerDetails(jobSeeker,jobSeeker.getEmailId()));
//        verify(jobSeekerRegisterRepository,times(1)).findById(jobSeeker.getEmailId());
//        verify(jobSeekerRegisterRepository,times(0)).save(jobSeeker);
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
////    deleteJobSeekerDetails
//
//    //positive test case
//    @Test
//    public void deleteProductDetails() throws JobSeekerNotFoundException
//    {
//        when(jobSeekerRegisterRepository.findById(jobSeeker.getEmailId())).thenReturn(Optional.ofNullable(jobSeeker));
//        boolean flag = registerServiceImpl.deleteJobSeekerDetails(jobSeeker.getEmailId());
//        assertEquals(true,flag);
//
//        verify(jobSeekerRegisterRepository,times(1)).findById(any());
//        verify(jobSeekerRegisterRepository,times(1)).deleteById(any());
//                                        //or
//        //verify(productRepository,times(1)).findById(product1.getProductCode());
//        //verify(productRepository,times(1)).deleteById(product1.getProductCode());
//    }
//
//    //negative test case
//    @Test
//    public void deleteProductDetailsFailure()
//    {
//        when(jobSeekerRegisterRepository.findById(jobSeeker.getEmailId())).thenReturn(Optional.ofNullable(null));
//        assertThrows(JobSeekerNotFoundException.class,()->registerServiceImpl.deleteJobSeekerDetails(jobSeeker.getEmailId()));
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//}
