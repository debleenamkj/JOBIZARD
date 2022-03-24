package com.stackroute.resourcesservice.controller;

import com.stackroute.resourcesservice.domain.Company;
import com.stackroute.resourcesservice.exception.CompanyAlreadyExistsException;
import com.stackroute.resourcesservice.exception.CompanyNotFoundException;
import com.stackroute.resourcesservice.service.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/resources/")
public class ReviewController {

    ReviewServiceImpl reviewService;

    @Autowired
    public ReviewController(ReviewServiceImpl reviewService) {
        this.reviewService = reviewService;
    }

    ResponseEntity responseEntity;

    @PostMapping("saveCompany")
    public ResponseEntity<?> saveCompany(@RequestBody Company company) throws CompanyAlreadyExistsException {

        try{
            responseEntity = new ResponseEntity<>(reviewService.saveCompany(company), HttpStatus.CREATED);
        } catch (CompanyAlreadyExistsException e) {
            throw new CompanyAlreadyExistsException();
        }catch (Exception e){
            responseEntity = new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @GetMapping("get_company/{companyName}")
    public ResponseEntity<?> getCompanyByCompanyName(@PathVariable String companyName) throws CompanyNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(reviewService.getCompanyByCompanyName(companyName), HttpStatus.FOUND);
        } catch (CompanyNotFoundException e) {
            throw new CompanyNotFoundException();
        }catch (Exception e){
            responseEntity = new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


}
