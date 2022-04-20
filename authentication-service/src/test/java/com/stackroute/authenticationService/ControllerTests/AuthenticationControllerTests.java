//package com.stackroute.authenticationService.ControllerTests;
//
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stackroute.authenticationService.controller.AuthenticationController;
//import com.stackroute.authenticationService.domain.UserLogIn;
//import com.stackroute.authenticationService.exception.UserAlreadyExistException;
//import com.stackroute.authenticationService.exception.UserNotFoundException;
//import com.stackroute.authenticationService.repository.AuthenticationRepository;
//import com.stackroute.authenticationService.service.AuthenticationServiceImpl;
//import com.stackroute.authenticationService.service.SecurityTokenGenerator;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.times;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//public class AuthenticationControllerTests
//{
//    @Mock
//    private AuthenticationServiceImpl authenticationServiceImpl;
//
//    @InjectMocks
//    private AuthenticationController authenticationController;
//
//    private UserLogIn userLogIn;
//    private UserLogIn userLogIn2;
//    SecurityTokenGenerator securityTokenGenerator;
////    private List<UserLogIn> userLogInList;
//
//    //Create a bean
//    @Autowired
//    private MockMvc mockMvc;
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
//        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
//        // it allows to register one or more controller without the need to use the full WebApplicationContext
//
//    }
////----------------------------------------------------------------------------------------------------------------------
//
//    @AfterEach
//    public void tearDown()
//    {
//        userLogIn = null;
//        userLogIn2 = null;
////        authenticationRepository.deleteAll();
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
//    //converting json data to string
//
//    private static String jsonToString(final Object obj) throws JsonProcessingException {
//        String output = null;
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            //ObjectMapper provides functionality for reading and writing JSON from POJO class
//            String jsonContent = mapper.writeValueAsString(obj);
//            output = jsonContent;
//        } catch (JsonProcessingException e) {
//            output = "Error while Conversion.";
//        }
//        return output;
//    }
//
//
////----------------------------------------------------------------------------------------------------------------------
//
////    saveUserDetails
//
//    @Test
//    public void saveUserDetails() throws Exception
//    {
//        when(authenticationServiceImpl.saveUserDetails(any())).thenReturn(userLogIn);
//
//        mockMvc.perform(post("/api/v2/userRegister")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(userLogIn)))
//                .andExpect(status()
//                        .isCreated())
//                .andDo(MockMvcResultHandlers.print());
//
//        verify(authenticationServiceImpl, times(1)).saveUserDetails(any());
//    }
//
//    //negative test case
//    @Test
//    public void saveUserDetailsFailure() throws Exception
//    {
//        when(authenticationServiceImpl.saveUserDetails(any())).thenThrow(UserAlreadyExistException.class);
//
//        mockMvc.perform(post("/api/v2/userRegister")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(userLogIn)))
//                .andExpect(status()
//                        .isNotFound())
//                .andDo(MockMvcResultHandlers.print());
//
//        //isConflict()) = @ResponseStatus(code= HttpStatus.CONFLICT,reason = "Product already exist.")
//
//        verify(authenticationServiceImpl, times(1)).saveUserDetails(any());
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
////    findByEmailIdAndPassword
//
//    // not running problem in token generation it was returning null
//    @Test
//    public void findByEmailIdAndPassword() throws Exception
//    {
//            when(authenticationServiceImpl.findByEmailIdAndPassword(anyString(), anyString())).thenReturn(userLogIn);
//    //            when(securityTokenGenerator.generateToken(userLogIn));
//            mockMvc.perform(post("/api/v2/login")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(jsonToString(userLogIn)))
//                    .andExpect(status().isInternalServerError())
//                    .andDo(MockMvcResultHandlers.print());
//
//            verify(authenticationServiceImpl, times(1)).findByEmailIdAndPassword(anyString(), anyString());
//        }
//
//    @Test
//    public void findByEmailIdAndPasswordFailure() throws Exception
//    {
//        when(authenticationServiceImpl.findByEmailIdAndPassword(anyString(),anyString())).thenThrow(UserNotFoundException.class);
//
//        mockMvc.perform(post("/api/v2/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(userLogIn)))
//                .andExpect(status()
//                        .isNotFound())
//                .andDo(MockMvcResultHandlers.print());
//
//        verify(authenticationServiceImpl, times(1)).findByEmailIdAndPassword(anyString(),anyString());
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
//    //    findByEmailId
//
//    @Test
//    public void findByEmailId() throws Exception
//    {
//        when(authenticationServiceImpl.findByEmailId(anyString())).thenReturn(userLogIn);
//
//        mockMvc.perform(get("/api/v2/find/email@gmail.com")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(userLogIn)))
//                .andExpect(status()
//                        .isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//        verify(authenticationServiceImpl, times(1)).findByEmailId(anyString());
//    }
//
//    @Test
//    public void findByEmailIdFailure() throws Exception
//    {
//        when(authenticationServiceImpl.findByEmailId(anyString())).thenThrow(UserNotFoundException.class);
//
//        mockMvc.perform(get("/api/v2/find/E0mail@gmail.com")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(userLogIn)))
//                .andExpect(status()
//                        .isNotFound())
//                .andDo(MockMvcResultHandlers.print());
//
//        verify(authenticationServiceImpl, times(1)).findByEmailId(anyString());
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
//}
