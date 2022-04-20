//package com.stackroute.authenticationService.RepositoryTests;
//
//
//import com.stackroute.authenticationService.domain.UserLogIn;
//import com.stackroute.authenticationService.repository.AuthenticationRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class AuthenticationRepositoryTests
//{
//    @Autowired
//    private AuthenticationRepository authenticationRepository;
//    private UserLogIn userLogIn;
//
////----------------------------------------------------------------------------------------------------------------------
//
//    @BeforeEach
//    public void setUp()
//    {
//        userLogIn = new UserLogIn();
//
//        userLogIn.setEmailId("email@gmail.com");
//        userLogIn.setPassword("Email123");
//        userLogIn.setRole("JOBSEEKER");
//
//
//    }
////----------------------------------------------------------------------------------------------------------------------
//
//    @AfterEach
//    public void tearDown()
//    {
//        userLogIn = null;
//        authenticationRepository.deleteAll();
//    }
//
////----------------------------------------------------------------------------------------------------------------------
////    saveUserDetails
//
//    //positive test case
//    @Test
//    public void saveNewUserDetails()
//    {
//        authenticationRepository.save(userLogIn);
//        UserLogIn userLogIn1 = authenticationRepository.findById(userLogIn.getEmailId()).get();
//        assertEquals(userLogIn.getEmailId(),userLogIn1.getEmailId());
//        assertEquals("email@gmail.com",userLogIn1.getEmailId());
//        assertEquals("Email123",userLogIn1.getPassword());
//        assertEquals("JOBSEEKER",userLogIn1.getRole());
//    }
//
//
//    //negative test case
//    @Test
//    public void saveNewUserDetailsFailure()
//    {
//        authenticationRepository.save(userLogIn);
//        UserLogIn userLogIn1 = authenticationRepository.findById(userLogIn.getEmailId()).get();
//        assertNotNull(userLogIn1);
////        assertNotEquals(0,userLogIn1.getEmailId());
////        assertNotEquals(2,user1.getEmail());
//        assertNotEquals("Email@gmail.com",userLogIn.getEmailId());
//        assertNotEquals("EMAIL@GMAIL.COM",userLogIn.getEmailId());
//        assertNotEquals(null,userLogIn.getEmailId());
//
//        assertNotEquals(null,userLogIn.getPassword());
//        assertNotEquals("email123",userLogIn.getPassword());
//        assertNotEquals("EMAIL123",userLogIn.getPassword());
//        assertNotEquals(123,userLogIn.getPassword());
//
//    }
////----------------------------------------------------------------------------------------------------------------------
//
////    findByEmailIdAndPassword
//
//    @Test
//    public void searchByEmailIdAndPassword()
//    {
//        //role
//        authenticationRepository.save(userLogIn);
//        authenticationRepository.findByEmailIdAndPassword(userLogIn.getEmailId(),userLogIn.getPassword());
//        UserLogIn userLogIn1 = authenticationRepository.findByEmailIdAndPassword(userLogIn.getEmailId(),userLogIn.getPassword());
////        UserLogIn userLogIn1 = authenticationRepository.findByEmailIdAndPassword("email@gmail.com","Email123");
////        assertEquals(userLogIn.getEmailId(),userLogIn1.getEmailId());
//        assertEquals("email@gmail.com",userLogIn1.getEmailId());
//        assertEquals("Email123",userLogIn1.getPassword());
//
//
////        authenticationRepository.findByEmailIdAndPassword(userLogIn.getEmailId(),userLogIn.getPassword());
////        UserLogIn userLogIn2 = authenticationRepository.findById(userLogIn.getEmailId()).get();
////        List<UserLogIn> list = authenticationRepository.findByEmailIdAndPassword("email@gmail.com","Email123");
////        assertEquals("FirstName01",list.get(0).getFirstName());
//
//    }
//
//
//    @Test
//    public void searchByEmailIdAndPasswordFailure()
//    {
//        //role
//        authenticationRepository.save(userLogIn);
//        authenticationRepository.findByEmailIdAndPassword(userLogIn.getEmailId(),userLogIn.getPassword());
//        UserLogIn userLogIn1 = authenticationRepository.findByEmailIdAndPassword(userLogIn.getEmailId(),userLogIn.getPassword());
////        UserLogIn userLogIn1 = authenticationRepository.findByEmailIdAndPassword("email@gmail.com","Email123");
////        assertEquals(userLogIn.getEmailId(),userLogIn1.getEmailId());
//        assertNotEquals("Email@gmail.com",userLogIn1.getEmailId());
//        assertNotEquals("email123",userLogIn1.getPassword());
//
//
////        authenticationRepository.findByEmailIdAndPassword(userLogIn.getEmailId(),userLogIn.getPassword());
////        UserLogIn userLogIn2 = authenticationRepository.findById(userLogIn.getEmailId()).get();
////        List<UserLogIn> list = authenticationRepository.findByEmailIdAndPassword("email@gmail.com","Email123");
////        assertEquals("FirstName01",list.get(0).getFirstName());
//
//    }
//
////----------------------------------------------------------------------------------------------------------------------
//
//
////    findByEmailIdAndPassword
//
//    @Test
//    public void searchByEmailId()
//    {
//        //role
//        authenticationRepository.save(userLogIn);
//        authenticationRepository.findByEmailId(userLogIn.getEmailId());
//        UserLogIn userLogIn1 = authenticationRepository.findByEmailId(userLogIn.getEmailId());
////        UserLogIn userLogIn1 = authenticationRepository.findByEmailIdAndPassword("email@gmail.com","Email123");
////        assertEquals(userLogIn.getEmailId(),userLogIn1.getEmailId());
//        assertEquals("email@gmail.com",userLogIn1.getEmailId());
////        assertEquals("Email123",userLogIn1.getPassword());
//
//
////        authenticationRepository.findByEmailIdAndPassword(userLogIn.getEmailId(),userLogIn.getPassword());
////        UserLogIn userLogIn2 = authenticationRepository.findById(userLogIn.getEmailId()).get();
////        List<UserLogIn> list = authenticationRepository.findByEmailIdAndPassword("email@gmail.com","Email123");
////        assertEquals("FirstName01",list.get(0).getFirstName());
//
//    }
//
//
//    @Test
//    public void searchByEmailIdFailure()
//    {
//        //role
//        authenticationRepository.save(userLogIn);
//        authenticationRepository.findByEmailId(userLogIn.getEmailId());
//        UserLogIn userLogIn1 = authenticationRepository.findByEmailId(userLogIn.getEmailId());
////        UserLogIn userLogIn1 = authenticationRepository.findByEmailIdAndPassword("email@gmail.com","Email123");
////        assertEquals(userLogIn.getEmailId(),userLogIn1.getEmailId());
//        assertNotEquals("Email@gmail.com",userLogIn1.getEmailId());
////        assertNotEquals("email123",userLogIn1.getPassword());
//
//
////        authenticationRepository.findByEmailIdAndPassword(userLogIn.getEmailId(),userLogIn.getPassword());
////        UserLogIn userLogIn2 = authenticationRepository.findById(userLogIn.getEmailId()).get();
////        List<UserLogIn> list = authenticationRepository.findByEmailIdAndPassword("email@gmail.com","Email123");
////        assertEquals("FirstName01",list.get(0).getFirstName());
//
//    }
//
//}
