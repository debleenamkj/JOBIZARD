//package com.satckroute.applicationRegisterService.ControllerTests;
//
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.satckroute.applicationRegisterService.controller.RegisterController;
//import com.satckroute.applicationRegisterService.domain.*;
//import com.satckroute.applicationRegisterService.exception.RecruiterAlreadyExistException;
//import com.satckroute.applicationRegisterService.exception.RecruiterNotFoundException;
//import com.satckroute.applicationRegisterService.repository.RecruiterRegisterRepository;
//import com.satckroute.applicationRegisterService.service.RegisterServiceImpl;
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
//import java.text.ParseException;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//public class RecruiterRegisterControllerTests
//{
//    @Mock
//    private RegisterServiceImpl registerServiceImpl;
//
//    @InjectMocks
//    private RegisterController registerController;
//
//    private RecruiterRegisterRepository recruiterRegisterRepository;
//
//    private Recruiter recruiter;
//    private Address address;
//    private OrganizationDetails organizationDetails;
//
//    private List<Recruiter> recruiterList;
//
//    //Create a bean
//    @Autowired
//    private MockMvc mockMvc;
//
////----------------------------------------------------------------------------------------------------------------------
//
//    @BeforeEach
//    public void setUp() throws ParseException
//    {
//        recruiter = new Recruiter();
//        recruiter.setEmailId("recruiter@gmail.com");
////        recruiter.setFirstName("FirstName1");
////        recruiter.setLastName("LastName1");
////        recruiter.setGender("MALE");
////        recruiter.setMobileNumber("7894560123");
//        recruiter.setPassword("pass123");
////        recruiter.setAddressDetails(address);
////        recruiter.setOrganizationDetails(organizationDetails);
//
//        recruiterList = Arrays.asList(recruiter);
//
//        mockMvc = MockMvcBuilders.standaloneSetup(registerController).build();
//        // it allows to register one or more controller without the need to use the full WebApplicationContext
//    }
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
//
//    //converting json data to string
//
//    private static String jsonToString(final Object obj) throws JsonProcessingException
//    {
//        String output = null;
//        try
//        {
//            ObjectMapper mapper = new ObjectMapper();
//            //ObjectMapper provides functionality for reading and writing JSON from POJO class
//            String jsonContent = mapper.writeValueAsString(obj);
//            output = jsonContent;
//        }
//        catch (JsonProcessingException e)
//        {
//            output = "Error while Conversion.";
//        }
//        return output;
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
//
////    registerNewRecruiter
//
//    //positive test case
//    @Test
//    public void saveNewRecruiter() throws Exception
//    {
//        when(registerServiceImpl.registerNewRecruiter(any())).thenReturn(recruiter);
//
//        mockMvc.perform(post("/api/v1/registerRecruiter")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(recruiter)))
//                .andExpect(status()
//                        .isCreated())
//                .andDo(MockMvcResultHandlers.print());
//
//        verify(registerServiceImpl, times(1)).registerNewRecruiter(any());
//    }
//
//    //negative test case
//    @Test
//    public void saveNewRecruiterFailure() throws Exception {
//        when(registerServiceImpl.registerNewRecruiter(any())).thenThrow(RecruiterAlreadyExistException.class);
//
//        mockMvc.perform(post("/api/v1/registerRecruiter")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(recruiter)))
//                .andExpect(status()
//                        .isConflict())
//                .andDo(MockMvcResultHandlers.print());
//
//        //isConflict()) = @ResponseStatus(code= HttpStatus.CONFLICT,reason = "Product already exist.")
//
//        verify(registerServiceImpl, times(1)).registerNewRecruiter(any());
//    }
////----------------------------------------------------------------------------------------------------------------------
//
////  getAllRecruiter
//
//
//    //positive test case
//    @Test
//    public void showAllRecruiter() throws Exception {
//        when(registerServiceImpl.getAllRecruiter()).thenReturn(recruiterList);
//
//        mockMvc.perform(get("/api/v1/getAllRecruiter")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())     // isOk() ref = its is status code of ResponseEntity
//                .andDo(MockMvcResultHandlers.print());
//
//        verify(registerServiceImpl, times(1)).getAllRecruiter();
//    }
//
//
//    //negative test case
//    @Test
//    public void showAllRecruiterFailure() throws Exception {
//        when(registerServiceImpl.getAllRecruiter()).thenThrow(RecruiterNotFoundException.class);
//
//        mockMvc.perform(get("/api/v1/getAllRecruiter")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isInternalServerError())  // catch
//                .andDo(MockMvcResultHandlers.print());
//
//        verify(registerServiceImpl, times(1)).getAllRecruiter();
//    }
//
//
////----------------------------------------------------------------------------------------------------------------------
//
////    getAllRecruiterByFirstName
//
//    //positive test case
////    @Test
////    public void showRecruiterByFirstName() throws Exception {
////        when(registerServiceImpl.getAllRecruiterByFirstName(anyString())).thenReturn(recruiterList);
////
////        mockMvc.perform(get("/api/v1//recruiter/getUserByFirstName/FirstName1")
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(jsonToString(recruiter)))
////                .andExpect(status().isOk())
////                .andDo(MockMvcResultHandlers.print());
////
////        verify(registerServiceImpl, times(1)).getAllRecruiterByFirstName(anyString());
////    }
////
////
////    //negative test case
////    @Test
////    public void showRecruiterByFirstNameFailure() throws Exception {
////        when(registerServiceImpl.getAllRecruiterByFirstName(anyString())).thenThrow(RecruiterNotFoundException.class);
////
////        mockMvc.perform(get("/api/v1//recruiter/getUserByFirstName/FirstName1")
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(jsonToString(recruiter)))
////                .andExpect(status().isConflict())
////                .andDo(MockMvcResultHandlers.print());
////
////        //isNotFound()) = ref @ResponseStatus(code= HttpStatus.*NOT_FOUND*,reason = "Product not exist.")
////
////        verify(registerServiceImpl, times(1)).getAllRecruiterByFirstName(anyString());
////    }
//
////----------------------------------------------------------------------------------------------------------------------
//
////    updateRecruiterDetails
//
//    //positive test case
//    @Test
//    public void updateRecruiterDetails() throws Exception {
//        when(registerServiceImpl.updateRecruiterDetails(any(), anyString())).thenReturn(recruiter);
//
//        mockMvc.perform(put("/api/v1/recruiter/emailId")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(recruiter)))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//        verify(registerServiceImpl, times(1)).updateRecruiterDetails(any(), anyString());
//    }
//
//
//    //negative test case
//    @Test
//    public void updateRecruiterDetailsFailure() throws Exception {
//        when(registerServiceImpl.updateRecruiterDetails(any(), anyString())).thenThrow(RecruiterNotFoundException.class);
//
//        mockMvc.perform(put("/api/v1/recruiter/emailId")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(recruiter)))
//                .andExpect(status().isConflict())
//                .andDo(MockMvcResultHandlers.print());
//
//        verify(registerServiceImpl, times(1)).updateRecruiterDetails(any(), anyString());
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
////    deleteRecruiterDetails
//
//    //positive test case
//    @Test
//    public void deleteRecruiterDetail() throws Exception
//    {
//        when(registerServiceImpl.deleteRecruiterDetails(anyString())).thenReturn(true);
//
//        mockMvc.perform(delete("/api/v1/recruiter/emailId")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//        verify(registerServiceImpl,times(1)).deleteRecruiterDetails(anyString());
//    }
//
//
//    //negative test case
//    @Test
//    public void deleteRecruiterDetailFailure() throws Exception
//    {
//        when(registerServiceImpl.deleteRecruiterDetails("recruiter@gmail.com")).thenThrow(RecruiterNotFoundException.class);
//
//        mockMvc.perform(delete("/api/v1/recruiter/emailId")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isInternalServerError())
//                .andDo(MockMvcResultHandlers.print());
//
//        verify(registerServiceImpl,times(1)).deleteRecruiterDetails(anyString());
//    }
//
//
//
////----------------------------------------------------------------------------------------------------------------------
//
//}
