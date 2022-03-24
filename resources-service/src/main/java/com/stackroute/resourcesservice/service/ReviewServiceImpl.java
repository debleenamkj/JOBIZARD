package com.stackroute.resourcesservice.service;

import com.stackroute.resourcesservice.domain.Company;
import com.stackroute.resourcesservice.domain.Review;
import com.stackroute.resourcesservice.exception.*;
import com.stackroute.resourcesservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Company saveCompany(Company company) throws CompanyAlreadyExistsException {

        if(reviewRepository.findById(company.getCompanyId()).isPresent() || reviewRepository.findByCompanyName(company.getCompanyName()) != null)
            throw new CompanyAlreadyExistsException();

        return reviewRepository.save(company);
    }

    @Override
    public Company getCompanyByCompanyName(String companyName) throws CompanyNotFoundException {
        Company company;
        company = reviewRepository.findByCompanyName(companyName);
        if(company == null)
            throw new CompanyNotFoundException();
        return company;
    }

    @Override
    public List<Company> getAllCompanies() throws CompanyNotFoundException {
        return null;
    }

    @Override
    public List<Review> getAllReviewsByCompanyName(String companyName) throws ReviewNotFoundException {
        return null;
    }

    @Override
    public Review saveReview(Review review, String CompanyName) throws CompanyNotFoundException, ReviewAlreadyExistsException {
        return null;
    }

    @Override
    public Review updateReviewByReviewIdAndCompanyName(String companyName, int reviewId) throws ReviewNotFoundException {
        return null;
    }

    @Override
    public List<Review> getAllReviewsByUserEmail(String userEmail) throws UserNotFoundException {
        return null;
    }

    @Override
    public boolean deleteReviewByUserEmailAndReviewId(String userEmail, int reviewId) {
        return false;
    }
}
