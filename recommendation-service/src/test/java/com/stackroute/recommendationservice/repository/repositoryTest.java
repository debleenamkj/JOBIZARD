//package com.stackroute.recommendationservice.repository;
//
//import com.stackroute.recommendationservice.model.JobDetails;
//import com.stackroute.recommendationservice.model.Seeker;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
////mport org.neo4j.harness.Neo4j;
////import org.neo4j.harness.Neo4jBuilders;i
//import org.neo4j.ogm.model.Node;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//
//
//@DataNeo4jTest
//@Transactional(propagation = Propagation.NEVER)
//@Slf4j
//public class repositoryTest {
//    private  JobRepository jobRepository;
//    private UserRepository userRepository;
//
//
//    private JobDetails jobDetails;
//    private Seeker seeker;
//    private Set<String> matchSet = new HashSet<>();
//
//    @Autowired
//    public repositoryTest(JobRepository jobRepository, UserRepository userRepository) {
//        this.jobRepository = jobRepository;
//        this.userRepository = userRepository;
//    }
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
//
//        seeker = new Seeker("malumalathi032@gmail.com",preferences,skills);
//        matchSet.add("malumalathi032@gmail.com");
//    }
//
//    @DynamicPropertySource
//    static void neo4jProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.neo4j.authentication.username", () -> "neo4j");
//        registry.add("spring.neo4j.authentication.password", () -> "jobizard");
//    }
//
//    @AfterEach
//    void tearDown() {
//        jobDetails = null;
//        seeker = null;
//    }
//
////    @Test
////    public void givenJobDetailsToSaveShouldReturnSuccess(){
////        System.out.println(jobDetails);
////        jobRepository.save(jobDetails);
////        System.out.println(jobDetails);
////        JobDetails jobDetails1 = jobRepository.findById(jobDetails.getEmailId()).get();
////        assertEquals(jobDetails1.getEmailId(),jobDetails.getEmailId());
////    }
////
////    @Test
////    public void givenJobDetailsToSaveShouldReturnFailure(){
////        System.out.println(jobDetails);
////        jobRepository.save(jobDetails);
////        System.out.println(jobDetails);
////        JobDetails jobDetails1 = jobRepository.findById(jobDetails.getEmailId()).get();
////        assertNotEquals(null,jobDetails);
////    }
////
////    @Test
////    public void givenSeekerToSaveShouldReturnSuccess(){
////        userRepository.save(seeker);
////        Seeker seeker1 = userRepository.findById(seeker.getEmail()).get();
////        assertEquals(seeker1.getEmail(),seeker.getEmail());
////    }
////
////    @Test
////    public void givenSeekerToSaveShouldReturnFailure(){
////        userRepository.save(seeker);
////        Seeker seeker1 = userRepository.findById(seeker.getEmail()).get();
////        assertNotEquals(null,seeker.getEmail());
////    }
////
////    @Test
////    public void findBySkillSetShouldReturnSuccess(){
////        userRepository.save(seeker);
////        List<Seeker> seeker1 = new ArrayList<>();
////        for(Object skill : seeker.getSkillSet()){
////            seeker1 = userRepository.findBySkillSet((String) skill);
////        }
////
////        assertEquals(seeker1,seeker1);
////    }
////
////    @Test
////    public void findBySkillSetShouldReturnFailure(){
////        List<Seeker> seeker1 = new ArrayList<>();
////        for(Object skill : seeker.getSkillSet()){
////            seeker1 = userRepository.findBySkillSet((String) skill);
////        }
////
////        assertNotEquals(null,seeker1);
////    }
////
////    @Test
////    public void createRelationshipShouldReturnSuccess(){
////        userRepository.save(seeker);
////        jobRepository.save(jobDetails);
////        Seeker seeker1 = userRepository.createRelation(seeker.getEmail(),jobDetails.getEmailId());
////        assertEquals(seeker1.getEmail(),seeker.getEmail());
////    }
////
////    @Test
////    public void createRelationshipShouldReturnFailure(){
////        userRepository.save(seeker);
////        jobRepository.save(jobDetails);
////        Seeker seeker1 = userRepository.createRelation(seeker.getEmail(),jobDetails.getEmailId());
////        assertNotEquals(null,seeker.getEmail());
////    }
////
////    @Test
////    public void checkRelationshipExistsReturnTrue(){
////        userRepository.save(seeker);
////        jobRepository.save(jobDetails);
////        userRepository.createRelation(seeker.getEmail(),jobDetails.getEmailId());
////        boolean result = userRepository.checkRelation(seeker.getEmail(),jobDetails.getEmailId());
////        assertEquals(result,true);
////    }
////
////    @Test
////    public void checkRelationshipExistsReturnFalse(){
////        userRepository.save(seeker);
////        jobRepository.save(jobDetails);
////        userRepository.createRelation(seeker.getEmail(),jobDetails.getEmailId());
////        boolean result = userRepository.checkRelation(seeker.getEmail(),jobDetails.getEmailId());
////        assertNotEquals(result,false);
////    }
//}
