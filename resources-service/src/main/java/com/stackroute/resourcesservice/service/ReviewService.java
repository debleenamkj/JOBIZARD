package com.stackroute.resourcesservice.service;

import com.stackroute.resourcesservice.domain.Company;
import com.stackroute.resourcesservice.domain.Review;
import com.stackroute.resourcesservice.exception.*;

import java.util.List;

public interface ReviewService {
    Company saveCompany(Company company) throws CompanyAlreadyExistsException;
    Company getCompanyByCompanyName(String companyName) throws CompanyNotFoundException;
    List<Company> getAllCompanies() throws CompanyNotFoundException;
    Company updateCompanyExceptReviews(Company company) throws CompanyNotFoundException;
    boolean deleteCompanyByCompanyName(String companyName) throws CompanyNotFoundException;

    List<Review> getAllReviewsByCompanyName(String companyName) throws ReviewNotFoundException, CompanyNotFoundException;
    Review saveReview(Review review, String companyName) throws CompanyNotFoundException, ReviewAlreadyExistsException;
   // Review updateReviewByCompanyName(String companyName, Review review) throws CompanyNotFoundException, ReviewNotFoundException;
    boolean deleteReviewByCompanyNameAndReviewId(String companyName, int reviewId) throws CompanyNotFoundException, ReviewNotFoundException;
    //Review getReviewByCompanyNameAndReviewId(String companyName, int reviewId) throws CompanyNotFoundException, ReviewNotFoundException;

    //List<Review> getAllReviewsByUserEmail(String userEmail)throws UserNotFoundException;
    List<Company> findAllDetails() throws CompanyNotFoundException;
}
