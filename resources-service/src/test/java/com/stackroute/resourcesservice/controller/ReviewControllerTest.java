package com.stackroute.resourcesservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.resourcesservice.domain.*;
import com.stackroute.resourcesservice.service.ReviewServiceImpl;
import org.junit.jupiter.api.AfterEach;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ReviewControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Mock
    ReviewServiceImpl reviewService;
    @InjectMocks
    ReviewController reviewController;

    private Company company;
    private Review review;
    private WorkDetails workDetails;
    private User user;
    private Date date;
    private Ratings rating;
    private List<Review> reviewList;

    @BeforeEach
    public void setUp(){
        user = new User();
        user.setAnonymousUser(true);
        user.setWorkDetails(workDetails);

        date = new Date();

        review=new Review();
        review.setReviewId(0);
        review.setReviewDate(date);
        review.setCompanyRatings(rating);
        review.setConsMessage("dislikeMessage");
        review.setProsMessage("likeMessage");
        review.setUser(user);

        reviewList = new ArrayList<>();
        reviewList.add(review);


        company = new Company();
        company.setCin("c1");
        company.setCompanyName("NIIT");
        company.setReviews(reviewList);
        workDetails = new WorkDetails();
        workDetails.setCurrentlyWorking(false);
//    mockMvc= MockMvcBuilders.standaloneSetup()

    }
    @AfterEach
    public void tearDown(){
        company = null;
        workDetails = null;
        user = null;
        date = null;
        rating = null;
        review=null;
    }

    private static String jsonToString(final Object o) throws JsonProcessingException {
        String jsonContent;
        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonContent = mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            jsonContent = "JsonProcessingException";
        }
        return jsonContent;
    }

    /*@Test
    public void givenCompanyToSaveReturnCompany() throws Exception{
        when(reviewService.saveCompany(any())).thenReturn(company);

        mockMvc.perform(post("/api/v1/resources/saveCompany")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(company)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(reviewService,times(1)).saveCompany(any());
    }
//***Review Test Cases***
    @Test
    public void givenReviewAndCompanyNameToSaveReturnReview() throws Exception{
        when(reviewService.saveReview(any(),any())).thenReturn(review);

        mockMvc.perform(post("/api/v1/resources/saveReview")
                        .contentType(MediaType.APPLICATION_JSON,MediaType.MULTIPART_FORM_DATA)
                        .content(jsonToString(review)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(reviewService,times(1)).saveReview(any(),any());
    }*/
}
