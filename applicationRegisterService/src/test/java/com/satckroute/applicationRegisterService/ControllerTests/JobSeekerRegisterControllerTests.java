package com.satckroute.applicationRegisterService.ControllerTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.satckroute.applicationRegisterService.controller.RegisterController;
import com.satckroute.applicationRegisterService.domain.Address;
import com.satckroute.applicationRegisterService.domain.Details;
import com.satckroute.applicationRegisterService.domain.JobSeeker;
import com.satckroute.applicationRegisterService.domain.Role;
import com.satckroute.applicationRegisterService.exception.JobSeekerAlreadyExistException;
import com.satckroute.applicationRegisterService.repository.JobSeekerRegisterRepository;
import com.satckroute.applicationRegisterService.service.RegisterServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class JobSeekerRegisterControllerTests
{
    @Mock
    private RegisterServiceImpl registerServiceImpl;

    @InjectMocks
    private RegisterController registerController;

    private JobSeekerRegisterRepository jobSeekerRegisterRepository;

    private JobSeeker jobSeeker;
    private Address address;
    private Details details;

    private List<JobSeeker> jobSeekerList;

    //Create a bean
    @Autowired
    private MockMvc mockMvc;

//----------------------------------------------------------------------------------------------------------------------

    @BeforeEach
    public void setUp() throws ParseException
    {
        String testDate = "29-Apr-2010,13:00:14 PM";
        DateFormat formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss aaa");
        Date date = formatter.parse(testDate);
        System.out.println(date);

//        , Role.JOBSEEKER
        jobSeeker = new JobSeeker("emailId@gmail.com","FirstName01","LastName1",
                "GENDER1",date,"1234567890","Password1",address,details);

        jobSeekerList= Arrays.asList(jobSeeker);

        mockMvc = MockMvcBuilders.standaloneSetup(registerController).build();
        // it allows to register one or more controller without the need to use the full WebApplicationContext
    }


//----------------------------------------------------------------------------------------------------------------------

    @AfterEach
    public void tearDown()
    {
        jobSeeker = null;
    }

//----------------------------------------------------------------------------------------------------------------------

    //converting json data to string

    private static String jsonToString(final Object obj) throws JsonProcessingException
    {
        String output = null;
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            //ObjectMapper provides functionality for reading and writing JSON from POJO class
            String jsonContent = mapper.writeValueAsString(obj);
            output = jsonContent;
        }
        catch (JsonProcessingException e)
        {
            output = "Error while Conversion.";
        }
        return output;
    }

//----------------------------------------------------------------------------------------------------------------------

    //positive test case
    @Test
    public void saveProductDetailsController() throws Exception
    {
        when(registerServiceImpl.registerNewJobSeeker(any())).thenReturn(jobSeeker);

        mockMvc.perform(post("/api/v1/registerJobSeeker")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(jobSeeker)))
                .andExpect(status()
                        .isCreated())
                .andDo(MockMvcResultHandlers.print());

        verify(registerServiceImpl,times(1)).registerNewJobSeeker(any());
    }

//----------------------------------------------------------------------------------------------------------------------



//----------------------------------------------------------------------------------------------------------------------


//----------------------------------------------------------------------------------------------------------------------


//----------------------------------------------------------------------------------------------------------------------

}
