//package com.satckroute.applicationRegisterService.ControllerTests;
//
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.satckroute.applicationRegisterService.controller.RegisterController;
//import com.satckroute.applicationRegisterService.domain.Address;
//import com.satckroute.applicationRegisterService.domain.OrganizationDetails;
//import com.satckroute.applicationRegisterService.exception.OrganizationDetailsAlreadyExistException;
//import com.satckroute.applicationRegisterService.exception.OrganizationDetailsNotFoundException;
//import com.satckroute.applicationRegisterService.repository.OrganizationDetailsRepository;
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
//public class OrganizationRegisterControllerTests
//{
//    @Mock
//    private RegisterServiceImpl registerServiceImpl;
//
//    @InjectMocks
//    private RegisterController registerController;
//
//    private OrganizationDetailsRepository organizationDetailsRepository;
//
//    private OrganizationDetails organizationDetails;
//    private Address address;
//
//    private List<OrganizationDetails> organizationDetailsList;
//
//    //Create a bean
//    @Autowired
//    private MockMvc mockMvc;
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
//
//        organizationDetailsList = Arrays.asList(organizationDetails);
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
//        organizationDetails = null;
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
////    saveOrganizationDetails
//
//    //positive test case
//    @Test
//    public void saveNewOrganizationDetails() throws Exception
//    {
//        when(registerServiceImpl.saveOrganizationDetails(any())).thenReturn(organizationDetails);
//
//        mockMvc.perform(post("/api/v1/saveOrganizationDetails")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(organizationDetails)))
//                .andExpect(status()
//                        .isCreated())
//                .andDo(MockMvcResultHandlers.print());
//
//        verify(registerServiceImpl, times(1)).saveOrganizationDetails(any());
//    }
//
//    //negative test case
//    @Test
//    public void saveNewOrganizationDetailsFailure() throws Exception {
//        when(registerServiceImpl.saveOrganizationDetails(any())).thenThrow(OrganizationDetailsAlreadyExistException.class);
//
//        mockMvc.perform(post("/api/v1/saveOrganizationDetails")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(organizationDetails)))
//                .andExpect(status()
//                        .isConflict())
//                .andDo(MockMvcResultHandlers.print());
//
//        //isConflict()) = @ResponseStatus(code= HttpStatus.CONFLICT,reason = "Product already exist.")
//
//        verify(registerServiceImpl, times(1)).saveOrganizationDetails(any());
//    }
////----------------------------------------------------------------------------------------------------------------------
//
////  getAllOrganization
//
//
//    //positive test case
//    @Test
//    public void showAllOrganizationDetails() throws Exception {
//        when(registerServiceImpl.getAllOrganization()).thenReturn(organizationDetailsList);
//
//        mockMvc.perform(get("/api/v1/getAllOrganization")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())     // isOk() ref = its is status code of ResponseEntity
//                .andDo(MockMvcResultHandlers.print());
//
//        verify(registerServiceImpl, times(1)).getAllOrganization();
//    }
//
//
//    //negative test case
//    @Test
//    public void showAllOrganizationDetailsFailure() throws Exception {
//        when(registerServiceImpl.getAllOrganization()).thenThrow(OrganizationDetailsNotFoundException.class);
//
//        mockMvc.perform(get("/api/v1/getAllOrganization")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isInternalServerError())  // catch
//                .andDo(MockMvcResultHandlers.print());
//
//        verify(registerServiceImpl, times(1)).getAllOrganization();
//    }
//
//
////----------------------------------------------------------------------------------------------------------------------
//
////    getAllOrganizationDetailsByOrganizationName
//
//    //positive test case
//    @Test
//    public void showOrganizationDetailsByOrganizationName() throws Exception {
//        when(registerServiceImpl.getAllOrganizationDetailsByOrganizationName(anyString())).thenReturn(organizationDetailsList);
//
//        mockMvc.perform(get("/api/v1//organizationDetails/getOrganizationDetailsName/organizationName")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(organizationDetails)))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//        verify(registerServiceImpl, times(1)).getAllOrganizationDetailsByOrganizationName(anyString());
//    }
//
//
//    //negative test case
//    @Test
//    public void showOrganizationDetailsByOrganizationNameFailure() throws Exception {
//        when(registerServiceImpl.getAllOrganizationDetailsByOrganizationName(anyString())).thenThrow(OrganizationDetailsNotFoundException.class);
//
//        mockMvc.perform(get("/api/v1//organizationDetails/getOrganizationDetailsName/organizationName")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(organizationDetails)))
//                .andExpect(status().isConflict())
//                .andDo(MockMvcResultHandlers.print());
//
//        //isNotFound()) = ref @ResponseStatus(code= HttpStatus.*NOT_FOUND*,reason = "Product not exist.")
//
//        verify(registerServiceImpl, times(1)).getAllOrganizationDetailsByOrganizationName(anyString());
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
////    updateOrganizationDetails
//
//    //positive test case
//    @Test
//    public void updateOrganizationDetails() throws Exception {
//        when(registerServiceImpl.updateOrganizationDetails(any(), anyString())).thenReturn(organizationDetails);
//
//        mockMvc.perform(put("/api/v1/organization/emailId")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(organizationDetails)))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//        verify(registerServiceImpl, times(1)).updateOrganizationDetails(any(), anyString());
//    }
//
//
//    //negative test case
//    @Test
//    public void updateOrganizationDetailsFailure() throws Exception {
//        when(registerServiceImpl.updateOrganizationDetails(any(), anyString())).thenThrow(OrganizationDetailsNotFoundException.class);
//
//        mockMvc.perform(put("/api/v1/organization/emailId")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(organizationDetails)))
//                .andExpect(status().isConflict())
//                .andDo(MockMvcResultHandlers.print());
//
//        verify(registerServiceImpl, times(1)).updateOrganizationDetails(any(), anyString());
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
////    deleteOrganizationDetails
//
//    //positive test case
//    @Test
//    public void deleteOrganizationDetail() throws Exception
//    {
//        when(registerServiceImpl.deleteOrganizationDetails(anyString())).thenReturn(true);
//
//        mockMvc.perform(delete("/api/v1/organizationDetails/emailId")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//        verify(registerServiceImpl,times(1)).deleteOrganizationDetails(anyString());
//    }
//
//
//    //negative test case
//    @Test
//    public void deleteOrganizationDetailFailure() throws Exception
//    {
//        when(registerServiceImpl.deleteOrganizationDetails("organization@gmail.com")).thenThrow(OrganizationDetailsNotFoundException.class);
//
//        mockMvc.perform(delete("/api/v1/organizationDetails/emailId")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isInternalServerError())
//                .andDo(MockMvcResultHandlers.print());
//
//        verify(registerServiceImpl,times(1)).deleteOrganizationDetails(anyString());
//    }
//
//
//
////----------------------------------------------------------------------------------------------------------------------
//
//}
