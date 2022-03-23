package com.stackroute.recommendationservice.controller;

import com.stackroute.recommendationservice.exception.JobAlreadyPresentException;
import com.stackroute.recommendationservice.exception.UserAlreadyExistsException;
import com.stackroute.recommendationservice.exception.UserNotFoundException;
import com.stackroute.recommendationservice.model.JobDetails;
import com.stackroute.recommendationservice.model.Seeker;
import com.stackroute.recommendationservice.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/recommend")
public class RecommendationController {

    private RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody Seeker seeker) throws UserAlreadyExistsException {
      return new ResponseEntity<>(recommendationService.saveUser(seeker), HttpStatus.CREATED);
    }

    @PostMapping("/job")
    public ResponseEntity<?> savejob(@RequestBody JobDetails job) throws JobAlreadyPresentException {
        return new ResponseEntity<>(recommendationService.savejob(job), HttpStatus.CREATED);
    }

    @PostMapping("/match")
    public ResponseEntity<?> matchjobs(@RequestBody Seeker seeker) throws UserNotFoundException {
        return new ResponseEntity<>(recommendationService.getMatchingJobs(seeker),HttpStatus.OK);
    }


}
