//package com.satckroute.applicationRegisterService.ServiceTests;
//
//
//import com.satckroute.applicationRegisterService.domain.Address;
//import com.satckroute.applicationRegisterService.domain.OrganizationDetails;
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
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class OrganizationRegisterServiceTests
//{
//    @InjectMocks
//    private RegisterServiceImpl registerServiceImpl;
//
//    @Mock
//    private OrganizationDetailsRepository organizationDetailsRepository;
//
//    private OrganizationDetails organizationDetails;
//    private Address address;
//
//    private List<OrganizationDetails> organizationDetailsList;
//
////----------------------------------------------------------------------------------------------------------------------
//
//    @BeforeEach
//    public void setUp()
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
//
//        organizationDetailsList= Arrays.asList(organizationDetails);
//    }
//
//
////----------------------------------------------------------------------------------------------------------------------
//
//    @AfterEach
//    public void tearDown()
//    {
//        organizationDetails = null;
//    }
////----------------------------------------------------------------------------------------------------------------------
////  getAllOrganization
//
//    //positive test case
//    @Test
//    public void showAllOrganization() throws Exception
//    {
//        when(organizationDetailsRepository.findAll()).thenReturn(organizationDetailsList);
//        assertEquals(organizationDetailsList,registerServiceImpl.getAllOrganization());
//        verify(organizationDetailsRepository,times(1)).findAll();
//    }
//
//    //negative test case
//    @Test
//    public void showAllOrganizationFailure() throws Exception
//    {
//        when(organizationDetailsRepository.findAll()).thenReturn(null);
//        assertNotEquals(organizationDetailsList,registerServiceImpl.getAllOrganization());
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
////  getAllOrganizationDetailsByOrganizationName
//
//
//    //positive test case
//    @Test
//    public void showOrganizationDetailsByOrganizationName() throws OrganizationDetailsNotFoundException
//    {
//        when(organizationDetailsRepository.findAllOrganizationByOrganizationName(organizationDetails.getOrganizationName())).thenReturn(Optional.ofNullable(organizationDetailsList).get());
//        assertEquals(organizationDetailsList,registerServiceImpl.getAllOrganizationDetailsByOrganizationName("ORG"));
//        verify(organizationDetailsRepository,times(2)).findAllOrganizationByOrganizationName("ORG");
//    }
//
//    //negative test case
//    @Test
//    public void showOrganizationDetailsByOrganizationNameFailure()
//    {
//        when(organizationDetailsRepository.findAllOrganizationByOrganizationName(organizationDetails.getOrganizationName())).thenReturn(null);
//        assertThrows(Exception.class,()->registerServiceImpl.getAllOrganizationDetailsByOrganizationName(organizationDetails.getOrganizationName()));
//        verify(organizationDetailsRepository,times(1)).findAllOrganizationByOrganizationName(organizationDetails.getOrganizationName());
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
////    updateOrganizationDetails
//
//    //positive test case
//    @Test
//    public void updateOrganizationDetails() throws OrganizationDetailsNotFoundException
//    {
//        when(organizationDetailsRepository.findById(organizationDetails.getEmailId())).thenReturn(Optional.ofNullable(organizationDetails));
//        organizationDetails.setOrganizationName("ORG");
//        when(organizationDetailsRepository.save(organizationDetails)).thenReturn(organizationDetails);
//
//        assertEquals("ORG",registerServiceImpl.updateOrganizationDetails(organizationDetails, organizationDetails.getEmailId()).getOrganizationName());
//
//        verify(organizationDetailsRepository,times(1)).findById(organizationDetails.getEmailId());
//        verify(organizationDetailsRepository,times(1)).save(organizationDetails);
//    }
//
//    //negative test case
//    @Test
//    public void updateOrganizationDetailsFailure()
//    {
//        when(organizationDetailsRepository.findById(organizationDetails.getEmailId())).thenReturn(Optional.ofNullable(null));
//        assertThrows(OrganizationDetailsNotFoundException.class,()->registerServiceImpl.updateOrganizationDetails(organizationDetails,organizationDetails.getEmailId()));
//        verify(organizationDetailsRepository,times(1)).findById(organizationDetails.getEmailId());
//        verify(organizationDetailsRepository,times(0)).save(organizationDetails);
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
////    deleteOrganizationDetails
//
//    //positive test case
//    @Test
//    public void deleteOrganizationDetails() throws OrganizationDetailsNotFoundException
//    {
//        when(organizationDetailsRepository.findById(organizationDetails.getEmailId())).thenReturn(Optional.ofNullable(organizationDetails));
//        boolean flag = registerServiceImpl.deleteOrganizationDetails(organizationDetails.getEmailId());
//        assertEquals(true,flag);
//
//        verify(organizationDetailsRepository,times(1)).findById(any());
//        verify(organizationDetailsRepository,times(1)).deleteById(any());
//                                           //or
//        //verify(productRepository,times(1)).findById(product1.getProductCode());
//        //verify(productRepository,times(1)).deleteById(product1.getProductCode());
//    }
//
//    //negative test case
//    @Test
//    public void deleteOrganizationDetailsFailure()
//    {
//        when(organizationDetailsRepository.findById(organizationDetails.getEmailId())).thenReturn(Optional.ofNullable(null));
//        assertThrows(OrganizationDetailsNotFoundException.class,()->registerServiceImpl.deleteOrganizationDetails(organizationDetails.getEmailId()));
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
//}
