//package com.stackroute.authenticationService.ServiceTests;
//
//import com.stackroute.authenticationService.domain.UserLogIn;
//import com.stackroute.authenticationService.exception.UserAlreadyExistException;
//import com.stackroute.authenticationService.exception.UserNotFoundException;
//import com.stackroute.authenticationService.repository.AuthenticationRepository;
//import com.stackroute.authenticationService.service.AuthenticationServiceImpl;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class AuthenticationServiceTests
//{
//    @Mock
//    AuthenticationRepository authenticationRepository;
//
//    @InjectMocks
//    AuthenticationServiceImpl authenticationServiceImpl;
//
//    private UserLogIn userLogIn,userLogIn2;
//
//
////----------------------------------------------------------------------------------------------------------------------
//
//    @BeforeEach
//    public void setUp()
//    {
//        //jobSeeker
//        userLogIn = new UserLogIn();
//
//        userLogIn.setEmailId("email@gmail.com");
//        userLogIn.setPassword("Email123");
//        userLogIn.setRole("JOBSEEKER");
//
//
//        //recruiter
//        userLogIn2 = new UserLogIn();
//
//        userLogIn2.setEmailId("email@gmail.com");
//        userLogIn2.setPassword("Email123");
//        userLogIn2.setRole("RECRUITER");
//
//    }
////----------------------------------------------------------------------------------------------------------------------
//
//    @AfterEach
//    public void tearDown()
//    {
//        userLogIn = null;
//        userLogIn2 = null;
//        authenticationRepository.deleteAll();
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
////    saveUserDetails
//
//    @Test
//    public void saveUserDetails() throws UserAlreadyExistException
//    {
//        when(authenticationRepository.findById(userLogIn.getEmailId())).thenReturn(Optional.ofNullable(null));
//        when(authenticationRepository.save(userLogIn)).thenReturn(userLogIn);
//        assertEquals(userLogIn,authenticationServiceImpl.saveUserDetails(userLogIn));
//        verify(authenticationRepository,times(1)).findById(userLogIn.getEmailId());
//        verify(authenticationRepository,times(1)).save(userLogIn);
//    }
//
//    @Test
//    public void saveUserDetailsFailure() throws UserAlreadyExistException
//    {
//        when(authenticationRepository.findById(userLogIn.getEmailId())).thenReturn(Optional.ofNullable(userLogIn));
//        assertThrows(UserAlreadyExistException.class,()->authenticationServiceImpl.saveUserDetails(userLogIn));
////        assertNotEquals(userLogIn,authenticationServiceImpl.saveUserDetails(userLogIn));
//        verify(authenticationRepository,times(1)).findById(userLogIn.getEmailId());
//        verify(authenticationRepository,times(0)).save(userLogIn); //can't call
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
////    findByEmailIdAndPassword
//
//    @Test
//    public void findByEmailIdAndPassword() throws UserNotFoundException
//    {
////        when(authenticationRepository.save(userLogIn)).thenReturn(userLogIn);
//        when(authenticationRepository.findByEmailIdAndPassword(userLogIn.getEmailId(),userLogIn.getPassword())).thenReturn(userLogIn);
//        assertEquals(userLogIn,authenticationServiceImpl.findByEmailIdAndPassword(userLogIn.getEmailId(),userLogIn.getPassword()));
//        verify(authenticationRepository,times(1)).findByEmailIdAndPassword(userLogIn.getEmailId(),userLogIn.getPassword());
////        verify(authenticationRepository,times(1)).save(userLogIn);
//    }
//
//    @Test
//    public void findByEmailIdAndPasswordFailure() throws UserNotFoundException
//    {
//        when(authenticationRepository.findByEmailIdAndPassword(userLogIn.getEmailId(),userLogIn.getPassword())).thenReturn(null);
//        assertThrows(UserNotFoundException.class,()->authenticationServiceImpl.findByEmailIdAndPassword(userLogIn.getEmailId(),userLogIn.getPassword()));
////        assertNotEquals(userLogIn,authenticationServiceImpl.saveUserDetails(userLogIn));
//        verify(authenticationRepository,times(1)).findByEmailIdAndPassword(userLogIn.getEmailId(),userLogIn.getPassword());
////        verify(authenticationRepository,times(0)).save(userLogIn); //can't call
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
//    //    findByEmailId
//
//    @Test
//    public void findByEmailId() throws UserNotFoundException
//    {
////        when(authenticationRepository.save(userLogIn)).thenReturn(userLogIn);
//        when(authenticationRepository.findByEmailId(userLogIn.getEmailId())).thenReturn(userLogIn);
//        assertEquals(userLogIn,authenticationServiceImpl.findByEmailId(userLogIn.getEmailId()));
//        verify(authenticationRepository,times(1)).findByEmailId(userLogIn.getEmailId());
////        verify(authenticationRepository,times(1)).save(userLogIn);
//    }
//
//    @Test
//    public void findByEmailIdFailure() throws UserNotFoundException
//    {
//        when(authenticationRepository.findByEmailId(userLogIn.getEmailId())).thenReturn(null);
//        assertThrows(UserNotFoundException.class,()->authenticationServiceImpl.findByEmailId(userLogIn.getEmailId()));
////        assertNotEquals(userLogIn,authenticationServiceImpl.saveUserDetails(userLogIn));
//        verify(authenticationRepository,times(1)).findByEmailId(userLogIn.getEmailId());
////        verify(authenticationRepository,times(0)).save(userLogIn); //can't call
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
//}
