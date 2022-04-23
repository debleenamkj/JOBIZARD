package com.stackroute.resourcesservice.controller;

import com.stackroute.resourcesservice.domain.Company;
import com.stackroute.resourcesservice.domain.Review;
import com.stackroute.resourcesservice.exception.CompanyAlreadyExistsException;
import com.stackroute.resourcesservice.exception.CompanyNotFoundException;
import com.stackroute.resourcesservice.exception.ReviewAlreadyExistsException;
import com.stackroute.resourcesservice.exception.ReviewNotFoundException;
import com.stackroute.resourcesservice.service.SequenceServiceImpl;
import com.stackroute.resourcesservice.service.ReviewServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;

//@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api/v1/resources/")
@Slf4j
public class ReviewController {

    private ReviewServiceImpl reviewService;
    private SequenceServiceImpl sequenceServiceImpl;

    @Value("$(com.stackroute.resourcesservice.InternalServerError)")
    private String serverError;

    @Autowired
    public ReviewController(ReviewServiceImpl reviewService, SequenceServiceImpl sequenceServiceImpl) {
        log.info("Autowiring - ReviewServiceImpl");
        this.reviewService = reviewService;
        this.sequenceServiceImpl = sequenceServiceImpl;
    }


    private ResponseEntity responseEntity;

    //***Company Methods***
    @PostMapping("saveCompany")
    public ResponseEntity<?> saveCompany(@RequestPart(name = "companyObj") Company company, @RequestPart(name = "imageFile", required = false)MultipartFile image) throws CompanyAlreadyExistsException {

        try{
            log.debug("ReviewController - saveCompany");
            if(image != null){
                company.setCompanyLogo(compressBytes(image.getBytes()));
            }
            responseEntity = new ResponseEntity<>(reviewService.saveCompany(company), HttpStatus.CREATED);
        } catch (CompanyAlreadyExistsException e) {
            log.error("ReviewController - saveCompany");
            throw new CompanyAlreadyExistsException();
        }catch (Exception e){
            log.error("ReviewController - saveCompany");
            responseEntity = new ResponseEntity<>(serverError, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @GetMapping("get_company")
    public ResponseEntity<?> getCompanyByCompanyName(@RequestParam("companyName") String companyName) throws CompanyNotFoundException {
        try{
            log.debug("ReviewController - getCompanyByCompanyName");
            responseEntity = new ResponseEntity<>(reviewService.getCompanyByCompanyName(companyName), HttpStatus.OK);
        } catch (CompanyNotFoundException e) {
            log.error("ReviewController - getCompanyByCompanyName");
            throw new CompanyNotFoundException();
        }catch (Exception e){
            log.error("ReviewController - getCompanyByCompanyName");
            responseEntity = new ResponseEntity<>(serverError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("get_all_companies")
    public ResponseEntity getAllCompanies() throws CompanyNotFoundException {
        try{
            log.debug("ReviewController - getAllCompanies");
            responseEntity = new ResponseEntity<>(reviewService.getAllCompanies(), HttpStatus.OK);
        } catch (CompanyNotFoundException e) {
            log.error("ReviewController - getAllCompanies");
            throw new CompanyNotFoundException();
        } catch (Exception e){
            log.error("ReviewController - getAllCompanies");
            System.out.println(e.toString());
            responseEntity = new ResponseEntity<>(serverError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("delete_company")
    public ResponseEntity<?> deleteCompanyByCompanyName(@RequestParam("companyName") String companyName) throws CompanyNotFoundException {
        try{
            log.debug("ReviewController - deleteCompanyByCompanyName");
            responseEntity = new ResponseEntity<>(reviewService.deleteCompanyByCompanyName(companyName), HttpStatus.OK);
        } catch (CompanyNotFoundException e) {
            log.error("ReviewController - deleteCompanyByCompanyName");
            throw new CompanyNotFoundException();
        } catch (Exception e){
            log.error("ReviewController - deleteCompanyByCompanyName");
            responseEntity = new ResponseEntity<>(serverError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @PutMapping("update_company")
    public ResponseEntity<?> updateCompany(@RequestBody Company company) throws CompanyNotFoundException {
        try{
            log.debug("ReviewController - updateCompany");
            responseEntity = new ResponseEntity<>(reviewService.updateCompanyExceptReviews(company), HttpStatus.OK);
        } catch (CompanyNotFoundException e) {
            log.error("ReviewController - updateCompany");
            throw new CompanyNotFoundException();
        } catch (Exception e){
            log.error("ReviewController - updateCompany");
            responseEntity = new ResponseEntity<>(serverError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    //***Review Methods***

    @PostMapping("saveReview")
    public ResponseEntity<?> saveReviewByCompanyNameAndReviewId(@RequestBody Review review, @RequestParam("companyName") String companyName) throws ReviewAlreadyExistsException, CompanyNotFoundException {
        try{
            log.debug("ReviewController - saveReviewByCompanyNameAndReviewId");
            review.setReviewId(sequenceServiceImpl.getSequenceNumber(Review.sequenceName));
            responseEntity = new ResponseEntity<>(reviewService.saveReview(review, companyName), HttpStatus.CREATED);
        } catch (ReviewAlreadyExistsException ex) {
            log.error("ReviewController - saveReviewByCompanyNameAndReviewId");
            throw new ReviewAlreadyExistsException();
        } catch (CompanyNotFoundException ex) {
            log.error("ReviewController - saveReviewByCompanyNameAndReviewId");
            throw new CompanyNotFoundException();
        }catch (Exception e){
            log.error("ReviewController - saveReviewByCompanyNameAndReviewId");
            System.out.println(e.toString());
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(serverError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /*@PutMapping("updateReview")
    public ResponseEntity<?> updateReviewByCompanyNameAndReviewId(@RequestBody Review review, @RequestParam("companyName") String companyName) throws CompanyNotFoundException, ReviewNotFoundException {
        try{
        log.debug("ReviewController - updateReviewByCompanyNameAndReviewId");
            responseEntity = new ResponseEntity<>(reviewService.updateReviewByCompanyName(companyName, review), HttpStatus.OK);
        } catch (ReviewNotFoundException exception) {
        log.error("ReviewController - updateReviewByCompanyNameAndReviewId");
            throw new ReviewNotFoundException();
        } catch (CompanyNotFoundException ex) {
        log.error("ReviewController - updateReviewByCompanyNameAndReviewId");
            throw new CompanyNotFoundException();
        }catch (Exception e){
        log.error("ReviewController - updateReviewByCompanyNameAndReviewId");
            responseEntity = new ResponseEntity<>(serverError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }*/

    @DeleteMapping("deleteReview")
    public ResponseEntity<?> deleteReviewByCompanyNameAndReviewId(@RequestParam("reviewId") int reviewId, @RequestParam("companyName") String companyName) throws CompanyNotFoundException, ReviewNotFoundException {
        try {
            log.debug("ReviewController - deleteReviewByCompanyNameAndReviewId");
            responseEntity = new ResponseEntity<>(reviewService.deleteReviewByCompanyNameAndReviewId(companyName, reviewId), HttpStatus.OK);
        } catch (ReviewNotFoundException exception) {
            log.error("ReviewController - deleteReviewByCompanyNameAndReviewId");
            throw new ReviewNotFoundException();
        } catch (CompanyNotFoundException ex) {
            log.error("ReviewController - deleteReviewByCompanyNameAndReviewId");
            throw new CompanyNotFoundException();
        }catch (Exception e){
            log.error("ReviewController - deleteReviewByCompanyNameAndReviewId");
            responseEntity = new ResponseEntity<>(serverError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("get_reviews")
    public ResponseEntity<?> getReviewsByCompanyName(@RequestParam("companyName") String companyName) throws ReviewNotFoundException, CompanyNotFoundException {
        try {
            log.debug("ReviewController - getReviewsByCompanyName");
            responseEntity = new ResponseEntity<>(reviewService.getAllReviewsByCompanyName(companyName), HttpStatus.OK);
        } catch (ReviewNotFoundException exception) {
            log.error("ReviewController - getReviewsByCompanyName");
            throw new ReviewNotFoundException();
        } catch (CompanyNotFoundException ex) {
            log.error("ReviewController - getReviewsByCompanyName");
            throw new CompanyNotFoundException();
        }catch (Exception e){
            log.error("ReviewController - getReviewsByCompanyName");
            responseEntity = new ResponseEntity<>(serverError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("getAllDetails")
    public ResponseEntity<?> get() throws CompanyNotFoundException{
        try {
            log.debug("ReviewController - get");
            responseEntity = new ResponseEntity<>(reviewService.findAllDetails(), HttpStatus.OK);
        }catch (CompanyNotFoundException ex) {
            log.error("ReviewController - get");
            throw new CompanyNotFoundException();
        }catch (Exception e){
            log.error("ReviewController - get");
            responseEntity = new ResponseEntity<>(serverError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    public static byte[] compressBytes(byte[] image){

        Deflater deflater = new Deflater();
        deflater.setInput(image);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(image.length);
        byte[] buffer = new byte[1024];
        while(!deflater.finished()){
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try{
            log.debug("ReviewController - compressBytes");
            outputStream.close();
        } catch (IOException e) {
            log.error("ReviewController - compressBytes");
            System.out.println(e.toString());
        }
        log.info("ReviewController - compressBytes-Compressed Byte Size: "+outputStream.toByteArray().length );
        return outputStream.toByteArray();
    }


}
