package com.stackroute.resourcesservice.service;

import com.stackroute.resourcesservice.domain.Company;
import com.stackroute.resourcesservice.domain.Review;
import com.stackroute.resourcesservice.exception.*;

import java.util.List;

public interface ReviewService {
    public Company saveCompany(Company company) throws CompanyAlreadyExistsException;
    public Company getCompanyByCompanyName(String companyName) throws CompanyNotFoundException;
    public List<Company> getAllCompanies() throws CompanyNotFoundException;
    public Company updateCompanyExceptReviews(Company company) throws CompanyNotFoundException;
    public boolean deleteCompanyByCompanyName(String companyName) throws CompanyNotFoundException;

    public List<Review> getAllReviewsByCompanyName(String companyName) throws ReviewNotFoundException, CompanyNotFoundException;
    public Review saveReview(Review review, String companyName) throws CompanyNotFoundException, ReviewAlreadyExistsException;
    public Review updateReviewByCompanyName(String companyName, Review review) throws CompanyNotFoundException, ReviewNotFoundException;
    public boolean deleteReviewByCompanyNameAndReviewId(String companyName, int reviewId) throws CompanyNotFoundException, ReviewNotFoundException;

    //public List<Review> getAllReviewsByUserEmail(String userEmail)throws UserNotFoundException;
}
