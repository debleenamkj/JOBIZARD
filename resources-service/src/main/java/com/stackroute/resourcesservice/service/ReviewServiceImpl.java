package com.stackroute.resourcesservice.service;

import com.stackroute.resourcesservice.domain.Company;
import com.stackroute.resourcesservice.domain.Review;
import com.stackroute.resourcesservice.exception.*;
import com.stackroute.resourcesservice.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        log.info("Autowiring-ReviewRepository");
        this.reviewRepository = reviewRepository;
    }


    //***Company Methods***
    @Override
    public Company saveCompany(Company company) throws CompanyAlreadyExistsException {
        Company company1;
        if(reviewRepository.findById(company.getCin()).isPresent() || reviewRepository.findByCompanyName(company.getCompanyName()) != null)
            throw new CompanyAlreadyExistsException();
        else{
            company1 = reviewRepository.save(company);
        }

        return company1;
    }

    @Override
    public Company getCompanyByCompanyName(String companyName) throws CompanyNotFoundException {
        Company company;
        company = reviewRepository.findByCompanyName(companyName);
        if(company == null)
            throw new CompanyNotFoundException();

            byte[] logo = company.getCompanyLogo();
            if ( logo != null){
                company.setCompanyLogo(decompressBytes(logo));
            }

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
        Optional<Company> optionalCompanyObj;
        Company companyFound;

        optionalCompanyObj = reviewRepository.findById(company.getCin());

        if(optionalCompanyObj.isEmpty())
            throw new CompanyNotFoundException();
        else {
            companyFound = optionalCompanyObj.get();
        }

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

        Company company;
        company = reviewRepository.findByCompanyName(companyName);
        if (company==null)
            throw new CompanyNotFoundException();

        if(reviewRepository.findReviewByCompanyNameAndReviews_ReviewId(companyName, review.getReviewId()).size()!=0){
            throw new ReviewAlreadyExistsException();
        }
        if(company.getReviews() == null){
            company.setReviews(List.of(review));
        }
        else {
            company.getReviews().add(review);
        }
        reviewRepository.save(company);

        return review;
    }

  /*  @Override
    public Review updateReviewByCompanyName(String companyName, Review review) throws CompanyNotFoundException, ReviewNotFoundException {
        List<Review> list;
        Company company;

        company = getCompanyByCompanyName(companyName);

        list = company.getReviews();

        if(list.stream().noneMatch((obj)->review.getReviewId() == obj.getReviewId()))
            throw new ReviewNotFoundException();

        list.removeIf(obj->review.getReviewId() == obj.getReviewId());


        list.add(review);
        company.setReviews(list);
        reviewRepository.save(company);

        return review;
    }*/

    @Override
    public boolean deleteReviewByCompanyNameAndReviewId(String companyName, int reviewId) throws CompanyNotFoundException, ReviewNotFoundException {
        List<Review> list;
        Company company;

        company = reviewRepository.findByCompanyName(companyName);
        if(company==null)
            throw new CompanyNotFoundException();

        list = company.getReviews();

        if(list == null || list.stream().noneMatch((obj)->reviewId == obj.getReviewId()))
            throw new ReviewNotFoundException();

        list.removeIf(obj->reviewId == obj.getReviewId());

        company.setReviews(list);

        reviewRepository.save(company);

        return true;
    }

    @Override
    public List<Company> findAllDetails() throws CompanyNotFoundException {
        List<Company> companyList;

        companyList = reviewRepository.findAll();

        if (companyList==null||companyList.isEmpty())
            throw new CompanyNotFoundException();
        companyList.forEach((obj)->{
            byte[] logo = obj.getCompanyLogo();
            if ( logo != null){
                obj.setCompanyLogo(decompressBytes(logo));
            }
        });

        return companyList;
    }


    public static byte[] decompressBytes(byte[] image) {

        Inflater inflater = new Inflater();
        inflater.setInput(image);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(image.length);
        byte[] buffer = new byte[1024];

        try{
            log.debug("ReviewServiceImpl - decompressBytes");
            while (!inflater.finished()){
                int count = inflater.inflate(buffer);

                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (DataFormatException e) {
            log.error("ReviewServiceImpl - decompressBytes");
            System.out.println(e.toString());
        } catch (IOException e) {
            log.error("ReviewServiceImpl - decompressBytes");
            System.out.println(e.toString());
        }
        log.info("ReviewServiceImpl - decompressBytes- DecompressedByteSizeInfo: "+outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

}
