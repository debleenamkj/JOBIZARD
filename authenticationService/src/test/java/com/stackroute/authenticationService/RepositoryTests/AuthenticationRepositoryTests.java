package com.stackroute.authenticationService.RepositoryTests;


import com.stackroute.authenticationService.domain.UserLogIn;
import com.stackroute.authenticationService.repository.AuthenticationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AuthenticationRepositoryTests
{
    @Autowired
    private AuthenticationRepository authenticationRepository;
    private UserLogIn userLogIn;

//----------------------------------------------------------------------------------------------------------------------

    @BeforeEach
    public void setUp()
    {
        userLogIn = new UserLogIn();

        userLogIn.setEmailId("email@gmail.com");
        userLogIn.setPassword("Email123");


    }
//----------------------------------------------------------------------------------------------------------------------

    @AfterEach
    public void tearDown()
    {
        userLogIn = null;
        authenticationRepository.deleteAll();
    }

//----------------------------------------------------------------------------------------------------------------------
//    saveUserDetails

    //positive test case
    @Test
    public void saveNewUserDetails()
    {
        authenticationRepository.save(userLogIn);
        UserLogIn userLogIn1  = authenticationRepository.findById(userLogIn.getEmailId()).get();
        assertEquals(userLogIn.getEmailId(),userLogIn1.getEmailId());

    }





    //negative test case
//    @Test
//    public void saveNewUserDetailsFailure()
//    {
//        jobSeekerRegisterRepository.insert(jobSeeker);
//        JobSeeker jobSeeker1 = jobSeekerRegisterRepository.findById(jobSeeker.getEmailId()).get();
//        assertNotNull(jobSeeker1);
//    }
//----------------------------------------------------------------------------------------------------------------------

}
