package com.stackroute.resourcesservice.service;

import com.stackroute.resourcesservice.domain.*;
import com.stackroute.resourcesservice.exception.CompanyAlreadyExistsException;
import com.stackroute.resourcesservice.exception.CompanyNotFoundException;
import com.stackroute.resourcesservice.exception.ReviewAlreadyExistsException;
import com.stackroute.resourcesservice.exception.ReviewNotFoundException;
import com.stackroute.resourcesservice.repository.ReviewRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

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

    //***Company Test Cases***
    @Test
    public void givenCompanyToSaveReturnSavedCompany() throws CompanyAlreadyExistsException {
        when(reviewRepository.findById(company.getCin())).thenReturn(Optional.ofNullable(null));
        when(reviewRepository.findByCompanyName(company.getCompanyName())).thenReturn(null);
        when(reviewRepository.save(any())).thenReturn(company);

        assertEquals(company, reviewService.saveCompany(company));
        verify(reviewRepository, times(1)).findById(any());
        verify(reviewRepository, times(1)).findByCompanyName(any());
        verify(reviewRepository, times(1)).save(any());
    }
    @Test
    public void givenCompanyToSaveReturnFailureFirstCase() {
        when(reviewRepository.findById(company.getCin())).thenReturn(Optional.ofNullable(company));

        assertThrows(CompanyAlreadyExistsException.class, () -> reviewService.saveCompany(company));
        verify(reviewRepository, times(1)).findById(any());
        verify(reviewRepository, times(0)).findByCompanyName(any());
        verify(reviewRepository, times(0)).save(any());
    }
    @Test
    public void givenCompanyToSaveReturnFailureSecondCase() {
        when(reviewRepository.findById(company.getCin())).thenReturn(Optional.ofNullable(null));
        when(reviewRepository.findByCompanyName(company.getCompanyName())).thenReturn(company);

        assertThrows(CompanyAlreadyExistsException.class, () -> reviewService.saveCompany(company));
        verify(reviewRepository, times(1)).findById(any());
        verify(reviewRepository, times(1)).findByCompanyName(any());
        verify(reviewRepository, times(0)).save(any());
    }

    @Test
    public void givenCompanyNameReturnCompany() throws CompanyNotFoundException {
        when(reviewRepository.findByCompanyName(company.getCompanyName())).thenReturn(company);

        assertEquals(company, reviewService.getCompanyByCompanyName(company.getCompanyName()));
        verify(reviewRepository, times(1)).findByCompanyName(any());
    }
    @Test
    public void givenCompanyNameReturnFailure() {
        when(reviewRepository.findByCompanyName(company.getCompanyName())).thenReturn(null);

        assertThrows(CompanyNotFoundException.class,()->reviewService.getCompanyByCompanyName(company.getCompanyName()));
        verify(reviewRepository, times(1)).findByCompanyName(any());
    }
    @Test
    public void givenListOfCompaniesReturnAllCompanies() throws CompanyNotFoundException {
        when(reviewRepository.findAllCompanyDetails()).thenReturn(List.of(company));

        assertEquals(List.of(company), reviewService.getAllCompanies());
        verify(reviewRepository, times(1)).findAllCompanyDetails();
    }
    @Test
    public void givenListOfCompaniesReturnFailure() {
        when(reviewRepository.findAllCompanyDetails()).thenReturn(List.of());

        assertThrows(CompanyNotFoundException.class, ()->reviewService.getAllCompanies());
        verify(reviewRepository, times(1)).findAllCompanyDetails();
    }
    @Test
    public void givenCompanyDetailsToUpdateReturnUpdatedCompany() throws CompanyNotFoundException {
        Company updatedCompany = company;
        updatedCompany.setCompanyName("updatedName");
        when(reviewRepository.findById(company.getCin())).thenReturn(Optional.ofNullable(company));
        when(reviewRepository.save(any())).thenReturn(updatedCompany);

        assertEquals(updatedCompany, reviewService.updateCompanyExceptReviews(updatedCompany));
        verify(reviewRepository,times(1)).findById(any());
        verify(reviewRepository, times(1)).save(any());
    }
    @Test
    public void givenCompanyDetailsToUpdateReturnFailure() {
        when(reviewRepository.findById(company.getCin())).thenReturn(Optional.ofNullable(null));

        assertThrows(CompanyNotFoundException.class, ()->reviewService.updateCompanyExceptReviews(company));
        verify(reviewRepository,times(1)).findById(any());
        verify(reviewRepository, times(0)).save(any());
    }
    @Test
    public void givenCompanyNameToDeleteReturnShouldDeleteSuccess() throws CompanyNotFoundException {
        when(reviewRepository.findByCompanyName(company.getCompanyName())).thenReturn(company);

        assertTrue(reviewService.deleteCompanyByCompanyName(company.getCompanyName()));
        verify(reviewRepository,times(1)).findByCompanyName(any());
        verify(reviewRepository,times(1)).deleteById(any());
    }
    @Test
    public void givenCompanyNameToDeleteReturnShouldDeleteFailure(){
        when(reviewRepository.findByCompanyName(company.getCompanyName())).thenReturn(null);

        assertThrows(CompanyNotFoundException.class ,()->reviewService.deleteCompanyByCompanyName(company.getCompanyName()));
        verify(reviewRepository,times(1)).findByCompanyName(any());
        verify(reviewRepository,times(0)).deleteById(any());
    }

    //***Review Test Cases***
    @Test
    public void givenCompanyNameReturnListOfReviewsOfCompany() throws CompanyNotFoundException, ReviewNotFoundException {
        when(reviewRepository.findByCompanyName(company.getCompanyName())).thenReturn(company);

        assertEquals(company.getReviews(),reviewService.getAllReviewsByCompanyName(company.getCompanyName()));
        verify(reviewRepository, times(1)).findByCompanyName(any());
    }
    @Test
    public void givenCompanyNameReturnCompanyNotFundException() {
        when(reviewRepository.findByCompanyName(company.getCompanyName())).thenReturn(null);

        assertThrows(CompanyNotFoundException.class,()->reviewService.getAllReviewsByCompanyName(company.getCompanyName()));
        verify(reviewRepository, times(1)).findByCompanyName(any());
    }
    @Test
    public void givenCompanyNameReturnReviewNotFundException() {
        company.setReviews(null);
        when(reviewRepository.findByCompanyName(company.getCompanyName())).thenReturn(company);

        assertThrows(ReviewNotFoundException.class,()->reviewService.getAllReviewsByCompanyName(company.getCompanyName()));
        verify(reviewRepository, times(1)).findByCompanyName(any());
    }
   @Test
    public void givenReviewAndCompanyNameToSaveReturnSavedReview() throws ReviewAlreadyExistsException, CompanyNotFoundException {
        Review review1 = new Review();
        review1.setReviewId(5);
        review1.setProsMessage("likeMessage");

        List<Review> newList = new ArrayList<>();
        newList.add(review);
        newList.add(review1);

        Company updatedCompany = new Company();
        updatedCompany.setCin(company.getCin());
        updatedCompany.setCompanyName(company.getCompanyName());
        updatedCompany.setReviews(newList);

        when(reviewRepository.findByCompanyName(company.getCompanyName())).thenReturn(company);
        when(reviewRepository.findReviewByCompanyNameAndReviews_ReviewId(company.getCompanyName(),review1.getReviewId())).thenReturn(new ArrayList<Company>());
        when(reviewRepository.save(any())).thenReturn(updatedCompany);

        assertEquals(review1, reviewService.saveReview(review1, company.getCompanyName()));
        verify(reviewRepository, times(1)).findByCompanyName(any());
        verify(reviewRepository,times(1)).findReviewByCompanyNameAndReviews_ReviewId(any(),anyInt());
        verify(reviewRepository,times(1)).save(any());
    }
    @Test
    public void givenReviewAndCompanyNameToSaveReturnReviewAlreadyExistsException() throws CompanyNotFoundException {
        Review review1 = new Review();
        review1.setReviewId(5);
        review1.setProsMessage("likeMessage");

        List<Review> newList = new ArrayList<>();
        newList.add(review1);

        Company newCompany = new Company();
        newCompany.setReviews(newList);

        List<Company> companyList = new ArrayList<>();
        companyList.add(newCompany);

        when(reviewRepository.findByCompanyName(company.getCompanyName())).thenReturn(company);
        when(reviewRepository.findReviewByCompanyNameAndReviews_ReviewId(company.getCompanyName(),review1.getReviewId())).thenReturn(companyList);

        assertThrows(ReviewAlreadyExistsException.class,()-> reviewService.saveReview(review1, company.getCompanyName()));
        verify(reviewRepository, times(1)).findByCompanyName(any());
        verify(reviewRepository,times(1)).findReviewByCompanyNameAndReviews_ReviewId(any(),anyInt());
        verify(reviewRepository,times(0)).save(any());
    }
    @Test
    public void givenReviewAndCompanyNameToSaveReturnCompanyNotFoundException(){
        Review review1 = new Review();
        review1.setReviewId(5);
        review1.setProsMessage("likeMessage");

        when(reviewRepository.findByCompanyName(company.getCompanyName())).thenReturn(null);

        assertThrows(CompanyNotFoundException.class,()-> reviewService.saveReview(review1, company.getCompanyName()));
        verify(reviewRepository, times(1)).findByCompanyName(any());
        verify(reviewRepository,times(0)).findReviewByCompanyNameAndReviews_ReviewId(any(),anyInt());
        verify(reviewRepository,times(0)).save(any());
    }

    @Test
    public void givenReviewIdAndCompanyNameToDeleteReviewReturnDeleteSuccess() throws ReviewNotFoundException, CompanyNotFoundException {

        Company deletedReview = new Company();
        deletedReview.setCin(company.getCin());
        deletedReview.setCompanyName(company.getCompanyName());

        when(reviewRepository.findByCompanyName(company.getCompanyName())).thenReturn(company);
        when(reviewRepository.save(any())).thenReturn(deletedReview);

        assertTrue(reviewService.deleteReviewByCompanyNameAndReviewId(company.getCompanyName(), review.getReviewId()));
        verify(reviewRepository, times(1)).findByCompanyName(any());
        verify(reviewRepository,times(1)).save(any());
    }
    @Test
    public void givenReviewIdAndCompanyNameToDeleteReviewReturnDeleteReviewNotFoundException()  {
        Review review1 = new Review();
        review1.setReviewId(80);
        List<Review> list = new ArrayList<>();
        list.add(review1);
        company.setReviews(list);

        when(reviewRepository.findByCompanyName(company.getCompanyName())).thenReturn(company);

        assertThrows(ReviewNotFoundException.class ,()->reviewService.deleteReviewByCompanyNameAndReviewId(company.getCompanyName(), review.getReviewId()));
        verify(reviewRepository, times(1)).findByCompanyName(any());
        verify(reviewRepository,times(0)).save(any());
    }
    @Test
    public void givenReviewIdAndCompanyNameToDeleteReviewReturnDeleteCompanyNotFoundException() {

        when(reviewRepository.findByCompanyName(company.getCompanyName())).thenReturn(null);

        assertThrows(CompanyNotFoundException.class ,()->reviewService.deleteReviewByCompanyNameAndReviewId(company.getCompanyName(), review.getReviewId()));
        verify(reviewRepository, times(1)).findByCompanyName(any());
        verify(reviewRepository,times(0)).save(any());
    }

    @Test
    public void givenTrackReturnAllDetails() throws CompanyNotFoundException {
        List<Company> companyList = new ArrayList<>();
        companyList.add(company);
        when(reviewRepository.findAll()).thenReturn(companyList);

        assertEquals(companyList.size(), reviewService.findAllDetails().size());
        verify(reviewRepository, times(1)).findAll();
    }
    @Test
    public void givenTrackReturnFailure() {
        when(reviewRepository.findAll()).thenReturn(null);

        assertThrows(CompanyNotFoundException.class,()-> reviewService.findAllDetails());
        verify(reviewRepository, times(1)).findAll();
    }
}
