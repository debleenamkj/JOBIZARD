//package com.stackroute.recommendationservice.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stackroute.recommendationservice.exception.JobAlreadyPresentException;
//import com.stackroute.recommendationservice.exception.JobNotFoundException;
//import com.stackroute.recommendationservice.exception.UserAlreadyExistsException;
//import com.stackroute.recommendationservice.exception.UserNotFoundException;
//import com.stackroute.recommendationservice.model.JobDetails;
//import com.stackroute.recommendationservice.model.Seeker;
//import com.stackroute.recommendationservice.service.RecommendationService;
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
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Set;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//public class controllerTest {
//
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private RecommendationService recommendationService;
//
//    @InjectMocks
//    private RecommendationController recommendationController;
//
//    private JobDetails jobDetails;
//    private Seeker seeker;
//    private   Set<String> matchSet = new HashSet<>();
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
//        mockMvc = MockMvcBuilders.standaloneSetup(recommendationController).build();
//        matchSet.add("malumalathi032@gmail.com");
//    }
//
//    @AfterEach
//    void tearDown() {
//        jobDetails = null;
//        seeker = null;
//    }
//
//    @Test
//    public void saveJobSeekerReturnSucces() throws Exception{
//        when(recommendationService.saveUser(any())).thenReturn(seeker);
//        mockMvc.perform(post("/api/v1/recommend/user")
//                        .contentType(MediaType.APPLICATION_JSON).
//                        content(jsonToString(seeker)))
//                .andExpect(status().isCreated())
//                .andDo(print());
//        verify(recommendationService,times(1)).saveUser(any());
//    }
//
//
//    @Test
//    public void saveJobSeekerReturnFailure() throws Exception{
//        when(recommendationService.saveUser(any())).thenThrow(UserAlreadyExistsException.class);
//        mockMvc.perform(post("/api/v1/recommend/user")
//                        .contentType(MediaType.APPLICATION_JSON).
//                        content(jsonToString(seeker)))
//                .andExpect(status().isConflict())
//                .andDo(print());
//        verify(recommendationService,times(1)).saveUser(any());
//    }
//
////    @Test
////    public void saveJobDetailsReturnSucces() throws Exception, JobAlreadyPresentException {
////        when(recommendationService.savejob(any())).thenReturn(jobDetails);
////        mockMvc.perform(post("/api/v1/recommend/job")
////                        .contentType(MediaType.APPLICATION_JSON).
////                        content(jsonToString(seeker)))
////                .andExpect(status().isCreated())
////                .andDo(print());
////        verify(recommendationService,times(1)).savejob(any());
////    }
////
////    @Test
////    public void saveJobDetailsReturnFailure() throws Exception, JobAlreadyPresentException {
////        when(recommendationService.savejob(any())).thenThrow(JobAlreadyPresentException.class);
////        mockMvc.perform(post("/api/v1/recommend/job")
////                        .contentType(MediaType.APPLICATION_JSON).
////                        content(jsonToString(seeker)))
////                .andExpect(status().isConflict())
////                .andDo(print());
////        verify(recommendationService,times(1)).savejob(any());
////    }
////
////    @Test
////    public void matchJobWithJobSeekerReturnSuccess() throws Exception, JobNotFoundException {
////        when(recommendationService.getMatchingJobSeeker(any())).thenReturn(matchSet);
////        mockMvc.perform(post("/api/v1/recommend/match")
////                        .contentType(MediaType.APPLICATION_JSON).
////                        content(jsonToString(seeker)))
////                .andExpect(status().isOk())
////                .andDo(print());
////        verify(recommendationService,times(1)).getMatchingJobSeeker(any());
////    }
////
////    @Test
////    public void matchJobWithJobSeekerReturnFailure() throws Exception, JobNotFoundException {
////        when(recommendationService.getMatchingJobSeeker(any())).thenReturn(matchSet);
////        mockMvc.perform(post("/api/v1/recommend/match")
////                .contentType(MediaType.APPLICATION_JSON).
////                content(jsonToString(seeker)))
////                .andExpect(status().isOk());
////        verify(recommendationService,times(1)).getMatchingJobSeeker(any());
////
////  }
//
//    private static String jsonToString(final Object ob) throws JsonProcessingException {
//        String result;
//
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            String jsonContent = mapper.writeValueAsString(ob);
//            result = jsonContent;
//        } catch(JsonProcessingException e) {
//            result = "JSON processing error";
//        }
//
//        return result;
//    }
//}
