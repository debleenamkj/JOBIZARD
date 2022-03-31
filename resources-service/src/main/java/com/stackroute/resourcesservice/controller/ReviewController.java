package com.stackroute.resourcesservice.controller;

import com.stackroute.resourcesservice.domain.Company;
import com.stackroute.resourcesservice.domain.Review;
import com.stackroute.resourcesservice.exception.CompanyAlreadyExistsException;
import com.stackroute.resourcesservice.exception.CompanyNotFoundException;
import com.stackroute.resourcesservice.exception.ReviewAlreadyExistsException;
import com.stackroute.resourcesservice.exception.ReviewNotFoundException;
import com.stackroute.resourcesservice.service.SequenceService;
import com.stackroute.resourcesservice.service.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api/v1/resources/")
public class ReviewController {

    private ReviewServiceImpl reviewService;
    private SequenceService sequenceService;

    @Autowired
    public ReviewController(ReviewServiceImpl reviewService, SequenceService sequenceService) {
        this.reviewService = reviewService;
        this.sequenceService = sequenceService;
    }


    private ResponseEntity responseEntity;

    //***Company Methods***
    @PostMapping("saveCompany")
    public ResponseEntity<?> saveCompany(@RequestPart(name = "companyObj") Company company, @RequestPart(name = "imageFile", required = false)MultipartFile image) throws CompanyAlreadyExistsException {

        try{
            if(image != null){
                company.setCompanyLogo(compressBytes(image.getBytes()));
            }
            responseEntity = new ResponseEntity<>(reviewService.saveCompany(company), HttpStatus.CREATED);
        } catch (CompanyAlreadyExistsException e) {
            throw new CompanyAlreadyExistsException();
        }catch (Exception e){
            responseEntity = new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @GetMapping("get_company")
    public ResponseEntity<?> getCompanyByCompanyName(@RequestParam("companyName") String companyName) throws CompanyNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(reviewService.getCompanyByCompanyName(companyName), HttpStatus.OK);
        } catch (CompanyNotFoundException e) {
            throw new CompanyNotFoundException();
        }catch (Exception e){
            responseEntity = new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("get_all_companies")
    public ResponseEntity getAllCompanies() throws CompanyNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(reviewService.getAllCompanies(), HttpStatus.OK);
        } catch (CompanyNotFoundException e) {
            throw new CompanyNotFoundException();
        } catch (Exception e){
            System.out.println(e.toString());
            responseEntity = new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("delete_company")
    public ResponseEntity<?> deleteCompanyByCompanyName(@RequestParam("companyName") String companyName) throws CompanyNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(reviewService.deleteCompanyByCompanyName(companyName), HttpStatus.OK);
        } catch (CompanyNotFoundException e) {
            throw new CompanyNotFoundException();
        } catch (Exception e){
            responseEntity = new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @PutMapping("update_company")
    public ResponseEntity<?> updateCompany(@RequestBody Company company) throws CompanyNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(reviewService.updateCompanyExceptReviews(company), HttpStatus.OK);
        } catch (CompanyNotFoundException e) {
            throw new CompanyNotFoundException();
        } catch (Exception e){
            responseEntity = new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    //***Review Methods***

    @PostMapping("saveReview")
    public ResponseEntity<?> saveReviewByCompanyNameAndReviewId(@RequestBody Review review, @RequestParam("companyName") String companyName) throws ReviewAlreadyExistsException, CompanyNotFoundException {
        try{
            review.setReviewId(sequenceService.getSequenceNumber(Review.sequenceName));
            System.out.println(Review.sequenceName);
            responseEntity = new ResponseEntity<>(reviewService.saveReview(review, companyName), HttpStatus.CREATED);
        } catch (ReviewAlreadyExistsException ex) {
            throw new ReviewAlreadyExistsException();
        } catch (CompanyNotFoundException ex) {
            throw new CompanyNotFoundException();
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            responseEntity = new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("updateReview")
    public ResponseEntity<?> updateReviewByCompanyNameAndReviewId(@RequestBody Review review, @RequestParam("companyName") String companyName) throws CompanyNotFoundException, ReviewNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(reviewService.updateReviewByCompanyName(companyName, review), HttpStatus.OK);
        } catch (ReviewNotFoundException exception) {
            throw new ReviewNotFoundException();
        } catch (CompanyNotFoundException ex) {
            throw new CompanyNotFoundException();
        }catch (Exception e){
            responseEntity = new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("deleteReview")
    public ResponseEntity<?> deleteReviewByCompanyNameAndReviewId(@RequestParam("reviewId") int reviewId, @RequestParam("companyName") String companyName) throws CompanyNotFoundException, ReviewNotFoundException {
        try {
            responseEntity = new ResponseEntity<>(reviewService.deleteReviewByCompanyNameAndReviewId(companyName, reviewId), HttpStatus.OK);
        } catch (ReviewNotFoundException exception) {
            throw new ReviewNotFoundException();
        } catch (CompanyNotFoundException ex) {
            throw new CompanyNotFoundException();
        }catch (Exception e){
            responseEntity = new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("get_reviews")
    public ResponseEntity<?> getReviewsByCompanyName(@RequestParam("companyName") String companyName) throws ReviewNotFoundException, CompanyNotFoundException {
        try {
            responseEntity = new ResponseEntity<>(reviewService.getAllReviewsByCompanyName(companyName), HttpStatus.OK);
        } catch (ReviewNotFoundException exception) {
            throw new ReviewNotFoundException();
        } catch (CompanyNotFoundException ex) {
            throw new CompanyNotFoundException();
        }catch (Exception e){
            responseEntity = new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
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
            outputStream.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        //System.out.println("Compressed Byte Size: "+outputStream.toByteArray().length );
        return outputStream.toByteArray();
    }


}
