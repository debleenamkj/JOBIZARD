package com.stackroute.resourcesservice.repository;

import com.stackroute.resourcesservice.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class ReviewRepositoryTest {
    @Autowired
    private ReviewRepository reviewRepository;

    private Company company;
    private Review review;
    private WorkDetails workDetails;
    private User user;
    private Date date;
    private Ratings rating;

    @BeforeEach
    public void setUp(){
        company = new Company("c1","NIIT",null,null);
        workDetails = new WorkDetails(false,"","");
        user = new User(true,"","",workDetails );
        date = new Date();
        review=new Review();
        review.setReviewId(0);
        review.setReviewDate(date);
        review.setCompanyRatings(rating);
        review.setConsMessage("dislikeMessage");
        review.setProsMessage("likeMessage");
        review.setUser(user);
    }
    @AfterEach
    public void tearDown(){
        reviewRepository.deleteById(company.getCin());
        company = null;
        workDetails = null;
        user = null;
        date = null;
        rating = null;
        review=null;
    }
    @Test
    public void givenCompanyToSaveReturnCompany(){
        reviewRepository.insert(company);
        Company company1 = reviewRepository.findById(company.getCin()).get();
        assertNotNull(company1);
        assertEquals(company.getCin(), company1.getCin());
    }
    @Test
    public void givenCompanyToDeleteReturnDeleteProduct(){
        reviewRepository.insert(company);
        Company company1 = reviewRepository.findById(company.getCin()).get();
        reviewRepository.deleteById(company1.getCin());
        assertEquals(Optional.empty(), reviewRepository.findById(company1.getCin()));
    }
    @Test
    public void givenCompanyNameReturnCompany(){
        reviewRepository.insert(company);
        Company company1 = reviewRepository.findById(company.getCin()).get();
        Company companyFound = reviewRepository.findByCompanyName(company1.getCompanyName());
        assertEquals(companyFound.getCompanyName(), company1.getCompanyName());
    }
   /* @Test
    public void givenCompanyNameAndReviews_ReviewIdReturnReview(){
        Review review2 = new Review();// = new Review(2,null,"aa","bb",null,null);
        review2.setReviewId(2);
        review2.setReviewDate(date);
        review2.setCompanyRatings(rating);
        review2.setConsMessage("2dislikeMessage");
        review2.setProsMessage("2likeMessage");
        review2.setUser(user);

        company.setReviews(List.of(review,review2));
        reviewRepository.insert(company);
        Company company1 = reviewRepository.findById(company.getCin()).get();

        Review reviewFound = reviewRepository.findReviewByCompanyNameAndReviews_ReviewId(company1.getCompanyName(), review2.getReviewId()).get(0);

        assertEquals(review2, reviewFound);
    }*/


    @Test
    public void findAllCompaniesReturnCompaniesWithNullReviewList(){
        List<Company> companies = reviewRepository.findAllCompanyDetails();
        List<Company> companyList = reviewRepository.findAll();

        assertEquals(companies.size(), companyList.size());
        companies.forEach((companyObj)->{
            assertNull(companyObj.getReviews());
        });
    }
}
