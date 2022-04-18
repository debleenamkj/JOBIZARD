package com.stackroute.emailservice.service;

import com.stackroute.model.EmailRequest;
import com.stackroute.service.MailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class MailServiceImplTest {
    @InjectMocks
    MailServiceImpl mailService;
    private EmailRequest emailRequest;
    @BeforeEach
    void  setUp() throws Exception {
        emailRequest = new EmailRequest("sreshthi.singh@gmail.com", "Intereseted in your profile", "recruiter interested in your profile", "wipro");

    }
    @Test
    public void saveSkillTest() throws Exception {
        assertEquals(true,mailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getMessage(), emailRequest.getCompanyName()));
    }



}
