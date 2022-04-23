package com.stackroute.recommendationservice.controller;

import com.stackroute.recommendationservice.exception.JobAlreadyPresentException;
import com.stackroute.recommendationservice.exception.JobNotFoundException;
import com.stackroute.recommendationservice.exception.UserAlreadyExistsException;
import com.stackroute.recommendationservice.exception.UserNotFoundException;
import com.stackroute.recommendationservice.model.JobDetails;
import com.stackroute.recommendationservice.model.Seeker;
import com.stackroute.recommendationservice.service.RecommendationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/recommend")
@Slf4j
public class RecommendationController {

    private RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        log.info("Autowiring RecommendationService");
        this.recommendationService = recommendationService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody Seeker seeker) throws UserAlreadyExistsException {
        try{
            log.debug("RecomendationController - saveUser");
            return new ResponseEntity<>(recommendationService.saveUser(seeker), HttpStatus.CREATED);
        }catch (UserAlreadyExistsException exception){
            log.error("RecommendationController - saveUser : "+exception);
            return new ResponseEntity<>(UserAlreadyExistsException.class, HttpStatus.CONFLICT);
        }

    }

    @PostMapping("/job")
    public ResponseEntity<?> savejob(@RequestBody JobDetails job) throws JobAlreadyPresentException {
        try{
            log.debug("RecomendationController - saveJob");
            return new ResponseEntity<>(recommendationService.savejob(job), HttpStatus.CREATED);
        }catch (JobAlreadyPresentException exception){
            log.error("RecommendationController - savejob : "+exception);
            return new ResponseEntity<>(JobAlreadyPresentException.class, HttpStatus.CONFLICT);
        }

    }

    @PostMapping("/match")
    public ResponseEntity<?> matchSeeker(@RequestBody JobDetails job) throws UserNotFoundException {
        try{
            log.debug("RecomendationController - matchSeeker");
            return new ResponseEntity<>(recommendationService.getMatchingJobSeeker(job),HttpStatus.OK);
        }catch (UserNotFoundException | JobNotFoundException exception){
            log.error("RecommendationController - matchSeeker : "+exception);
            return new ResponseEntity<>(UserNotFoundException.class, HttpStatus.NOT_FOUND);
        }

    }


}
