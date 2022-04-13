package com.satckroute.applicationRegisterService.ServiceTests;

import com.satckroute.applicationRegisterService.domain.*;
import com.satckroute.applicationRegisterService.exception.JobSeekerNotFoundException;
import com.satckroute.applicationRegisterService.repository.JobSeekerRegisterRepository;
import com.satckroute.applicationRegisterService.repository.RecruiterRegisterRepository;
import com.satckroute.applicationRegisterService.service.RegisterServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RecruiterRegisterImplTests
{
    @InjectMocks
    private RegisterServiceImpl registerServiceImpl;

    @Mock
    private RecruiterRegisterRepository recruiterRegisterRepository;

    private Recruiter recruiter;
    private Address address;
    private OrganizationDetails organizationDetails;

    private List<Recruiter> recruiterList;

//----------------------------------------------------------------------------------------------------------------------

    @BeforeEach
    public void setUp() throws ParseException
    {
        recruiter= new Recruiter();
        recruiter.setEmailId("recruiter@gmail.com");
        recruiter.setFirstName("FirstName1");
        recruiter.setLastName("LastName1");
        recruiter.setGender("MALE");
        recruiter.setMobileNumber("7894560123");
        recruiter.setPassword("pass123");
        recruiter.setAddressDetails(address);
        recruiter.setOrganizationDetails(organizationDetails);
//        recruiter.setRecruiterImage();
    }


//----------------------------------------------------------------------------------------------------------------------

    @AfterEach
    public void tearDown()
    {
        recruiter = null;
    }

//----------------------------------------------------------------------------------------------------------------------


//----------------------------------------------------------------------------------------------------------------------

}