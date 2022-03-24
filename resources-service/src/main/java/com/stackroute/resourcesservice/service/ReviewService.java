package com.stackroute.resourcesservice.service;

import com.stackroute.resourcesservice.domain.Company;
import com.stackroute.resourcesservice.domain.Review;
import com.stackroute.resourcesservice.exception.*;

import java.util.List;

public interface ReviewService {
    public Company saveCompany(Company company) throws CompanyAlreadyExistsException;
    public Company getCompanyByCompanyName(String companyName) throws CompanyNotFoundException;
    public List<Company> getAllCompanies() throws CompanyNotFoundException;
    public List<Review> getAllReviewsByCompanyName(String companyName) throws ReviewNotFoundException;
    public Review saveReview(Review review, String CompanyName) throws CompanyNotFoundException, ReviewAlreadyExistsException;
    public Review updateReviewByReviewIdAndCompanyName(String companyName, int reviewId) throws ReviewNotFoundException;
    public List<Review> getAllReviewsByUserEmail(String userEmail)throws UserNotFoundException;
    public boolean deleteReviewByUserEmailAndReviewId(String userEmail, int reviewId);
}
