package com.stackroute.emailservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.controller.EmailController;
import com.stackroute.model.EmailRequest;
import com.stackroute.service.MailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class EmailControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Mock
    MailService mailService;
    @InjectMocks
    private EmailController emailController;
    private EmailRequest emailRequest;
    @BeforeEach
    void setUp() throws Exception{
        emailRequest=new EmailRequest("sreshthi.singh@gmail.com","Profile is selected","Hello user, your profile is selected","wipro" );
        mockMvc= MockMvcBuilders.standaloneSetup(emailController).build();
    }
    @Test
        public void saveProductTest() throws Exception {
            when(mailService.sendEmail(emailRequest.getTo(),emailRequest.getSubject(),emailRequest.getMessage(),emailRequest.getCompanyName())).thenReturn(true);
            mockMvc.perform(
                            post("/api/v1/sendemail")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(jsonToString(emailRequest))
                    )
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print()
                    );
            verify(mailService,times(1)).sendEmail((String) any(), (String) any(), (String) any(), (String) any());
        }


    public static String jsonToString(final Object obj)throws JsonProcessingException {
        String result = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(obj);
            result = jsonContent;
        } catch (JsonProcessingException e) {
            result = "Json Processing Error";
        }
        return result;
    }  }