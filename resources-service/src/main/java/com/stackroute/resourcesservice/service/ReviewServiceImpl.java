package com.stackroute.resourcesservice.service;

import com.stackroute.resourcesservice.domain.Company;
import com.stackroute.resourcesservice.domain.Review;
import com.stackroute.resourcesservice.exception.*;
import com.stackroute.resourcesservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    //***Company Methods***
    @Override
    public Company saveCompany(Company company) throws CompanyAlreadyExistsException {

        if(reviewRepository.findById(company.getCin()).isPresent() || reviewRepository.findByCompanyName(company.getCompanyName()) != null)
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
        List<Company> companyList = reviewRepository.findAllCompanyDetails();
        if (companyList.isEmpty())
            throw new CompanyNotFoundException();
        companyList.forEach((obj)->{
            byte[] logo = obj.getCompanyLogo();
            if ( logo != null){
                obj.setCompanyLogo(decompressBytes(logo));
            }
        });
        return companyList;
    }

    @Override
    public Company updateCompanyExceptReviews(Company company) throws CompanyNotFoundException {
        Company companyFound = reviewRepository.findById(company.getCin()).get();

        if (company == null)
            throw new CompanyNotFoundException();
        List<Review> reviewList = companyFound.getReviews();
        if(reviewList != null)
            company.setReviews(reviewList);

        return reviewRepository.save(company);
    }

    @Override
    public boolean deleteCompanyByCompanyName(String companyName) throws CompanyNotFoundException {
       Company company = reviewRepository.findByCompanyName(companyName);

        if ( company == null)
            throw new CompanyNotFoundException();
        reviewRepository.deleteById(company.getCin());

        return true;
    }


    //***Review Methods***
    @Override
    public List<Review> getAllReviewsByCompanyName(String companyName) throws ReviewNotFoundException, CompanyNotFoundException {
        List<Review> reviewList;
        Company company;

        company = getCompanyByCompanyName(companyName);
        reviewList = company.getReviews();

        if (reviewList == null)
            throw new ReviewNotFoundException();

        return reviewList;
    }

    @Override
    public Review saveReview(Review review, String companyName) throws CompanyNotFoundException, ReviewAlreadyExistsException {
        List<Review> reviewList;
        Company company;
        company = getCompanyByCompanyName(companyName);

        reviewList = reviewRepository.findReviewByCompanyNameAndReviews_ReviewId(companyName, review.getReviewId());
        if(!reviewList.isEmpty())
            throw new ReviewAlreadyExistsException();
        if(company.getReviews() != null)
            company.getReviews().add(review);
        //Condition for saving first Review.
        else {
            System.out.println("null value");
            company.setReviews(List.of(review));
        }
        reviewRepository.save(company);

        return review;
    }

    @Override
    public Review updateReviewByCompanyName(String companyName, Review review) throws CompanyNotFoundException, ReviewNotFoundException {
        List<Review> list;
        Company company;

        company = getCompanyByCompanyName(companyName);

        list = company.getReviews();

        if(!list.stream().anyMatch((obj)->review.getReviewId() == obj.getReviewId()))
            throw new ReviewNotFoundException();

        list.removeIf(obj->review.getReviewId() == obj.getReviewId());
        list.forEach((obj)-> System.out.println(obj));

        list.add(review);
        company.setReviews(list);
        reviewRepository.save(company);

        return review;
    }

    @Override
    public boolean deleteReviewByCompanyNameAndReviewId(String companyName, int reviewId) throws CompanyNotFoundException, ReviewNotFoundException {
        List<Review> list;
        Company company;

        company = getCompanyByCompanyName(companyName);

         list = company.getReviews();

        if(!list.stream().anyMatch((obj)->reviewId == obj.getReviewId()))
            throw new ReviewNotFoundException();

        list.removeIf(obj->reviewId == obj.getReviewId());

        company.setReviews(list);

        reviewRepository.save(company);

        return true;
    }


    public static byte[] decompressBytes(byte[] image){

        Inflater inflater = new Inflater();
        inflater.setInput(image);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(image.length);
        byte[] buffer = new byte[1024];

        try{
            while (!inflater.finished()){
                int count = inflater.inflate(buffer);

                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (DataFormatException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        return outputStream.toByteArray();
    }

}
