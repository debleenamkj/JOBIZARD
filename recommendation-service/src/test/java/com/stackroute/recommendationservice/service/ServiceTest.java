//package com.stackroute.recommendationservice.service;
//
//import com.stackroute.recommendationservice.exception.JobAlreadyPresentException;
//import com.stackroute.recommendationservice.exception.JobNotFoundException;
//import com.stackroute.recommendationservice.exception.UserAlreadyExistsException;
//import com.stackroute.recommendationservice.exception.UserNotFoundException;
//import com.stackroute.recommendationservice.model.JobDetails;
//import com.stackroute.recommendationservice.model.Seeker;
//import com.stackroute.recommendationservice.repository.JobRepository;
//import com.stackroute.recommendationservice.repository.UserRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.*;
//
//import static org.junit.Assert.assertNotEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.mockito.internal.verification.VerificationModeFactory.times;
//
//@DataNeo4jTest
//@Transactional(propagation = Propagation.NEVER)
//@Slf4j
//public class ServiceTest {
//
//
//    @Mock
//    private UserRepository userRepository;
//    @Mock
//    private JobRepository jobRepository;
//
//
//    @InjectMocks
//    private RecommendationserviceImpl recommendationService;
//
//    @DynamicPropertySource
//    static void neo4jProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.neo4j.authentication.username", () -> "neo4j");
//        registry.add("spring.neo4j.authentication.password", () -> "jobizard");
//    }
//
//    private JobDetails jobDetails;
//    private Seeker seeker;
//    private Set<String> matchSet = new HashSet<>();
//
//    @BeforeEach
//    void setUp() {
//        ArrayList skills = new ArrayList();
//        skills.add("java");
//        skills.add("spring");
//        jobDetails = new JobDetails("1001",skills,"software developer");
//        ArrayList preferences = new ArrayList();
//        preferences.add("software developer");
//        preferences.add("software engineer");
//        seeker = new Seeker("malumalathi032@gmail.com",preferences,skills);
//        matchSet.add("malumalathi032@gmail.com");
//    }
//
//    @AfterEach
//    void tearDown() {
//        jobDetails = null;
//        seeker = null;
//    }
//
//
//    @Test
//    public void givenJobToRegisterReturnSuccess() throws JobAlreadyPresentException {
//        when(jobRepository.findById(jobDetails.getEmailId())).thenReturn(Optional.ofNullable(null));
//        when(jobRepository.save(jobDetails)).thenReturn(jobDetails);
//
//        JobDetails jobDetails1 = recommendationService.savejob(jobDetails);
//        assertEquals(jobDetails1,jobDetails);
//        verify(jobRepository,times(1)).save(jobDetails);
//        verify(jobRepository,times(1)).findById(jobDetails1.getEmailId());
//    }
//
////    @Test
////    public void givenJobToRegisterReturnFailure() throws JobAlreadyPresentException {
////
////        when(jobRepository.findById(jobDetails.getEmailId())).thenReturn(Optional.ofNullable(jobDetails));
////        JobDetails jobDetails1 = recommendationService.savejob(jobDetails);
////        assertNotEquals(null,jobDetails);
////        verify(jobRepository,times(0)).save(jobDetails);
////        verify(jobRepository,times(1)).findById(jobDetails.getEmailId());
////    }
//
//
//    @Test
//    public void givenSeekerToRegisterReturnSuccess() throws  UserAlreadyExistsException {
//        when(userRepository.findById(seeker.getEmail())).thenReturn(Optional.ofNullable(null));
//        when(userRepository.save(seeker)).thenReturn(seeker);
//
//        Seeker seeker1 = recommendationService.saveUser(seeker);
//        assertEquals(seeker1,seeker);
//        verify(userRepository,times(1)).save(seeker);
//        verify(userRepository,times(1)).findById(seeker1.getEmail());
//    }
//
////    @Test
////    public void givenSeekerToRegisterReturnFailure() throws  UserAlreadyExistsException {
////        when(userRepository.findById(seeker.getEmail())).thenReturn(Optional.ofNullable(seeker));
////        when(userRepository.save(seeker)).thenReturn(null);
////
////        Seeker seeker1 = recommendationService.saveUser(seeker);
////        assertNotEquals(null,seeker);
////        verify(userRepository,times(0)).save(seeker);
////        verify(userRepository,times(1)).findById(seeker1.getEmail());
////    }
//
//    @Test
//    public void getMatchingJobReturnSuccess() throws JobNotFoundException {
//        List<Seeker> seeker1 = null;
//        Set<Seeker> seeker2 = new HashSet<>();
//        when(jobRepository.findById(jobDetails.getEmailId())).thenReturn(Optional.ofNullable((jobDetails)));
//        for(Object skill : seeker.getSkillSet()){
//            seeker1 = userRepository.findBySkillSet((String) skill);
//            seeker2.addAll(seeker1);
//            when(userRepository.findBySkillSet((String) skill)).thenReturn(seeker1);
//        }
//        Set<String> matchSet1 = recommendationService.getMatchingJobSeeker(jobDetails);
//        assertEquals(matchSet1,seeker2);
//
//        verify(userRepository,times(2)).findBySkillSet("java");
//        verify(jobRepository,times(1)).findById(jobDetails.getEmailId());
//    }
//
//    @Test
//    public void getMatchingJobReturnfailure() throws UserNotFoundException {
//        List<Seeker> seeker1 = null;
//        Set<Seeker> seeker2 = new HashSet<>();
//        when(jobRepository.findById(jobDetails.getEmailId())).thenReturn(null);
//        assertNotEquals(null,seeker2);
//
//        verify(userRepository,times(0)).findBySkillSet("java");
//        verify(jobRepository,times(0)).findById(jobDetails.getEmailId());
//    }
//
//    @Test
//    public void createRelationshipReturnSuccess(){
//        when(userRepository.checkRelation(seeker.getEmail(),jobDetails.getEmailId())).thenReturn(false);
//        when(userRepository.createRelation(seeker.getEmail(),jobDetails.getEmailId())).thenReturn(seeker);
//
//        assertEquals(userRepository.createRelation(seeker.getEmail(),jobDetails.getEmailId()),seeker);
//        verify(userRepository,times(0)).checkRelation(seeker.getEmail(),jobDetails.getEmailId());
//        verify(userRepository,times(1)).createRelation(seeker.getEmail(),jobDetails.getEmailId());
//    }
//
//    @Test
//    public void createRelationshipReturnFailure(){
//        when(userRepository.checkRelation(seeker.getEmail(),jobDetails.getEmailId())).thenReturn(true);
//        assertNotEquals(null,seeker);
//        verify(userRepository,times(0)).checkRelation(seeker.getEmail(),jobDetails.getEmailId());
//    }
//
//
//
//}
//
