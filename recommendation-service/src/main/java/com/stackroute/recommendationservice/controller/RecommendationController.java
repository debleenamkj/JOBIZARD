package com.stackroute.recommendationservice.controller;

import com.stackroute.recommendationservice.model.JobPosting;
import com.stackroute.recommendationservice.model.User;
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
    public ResponseEntity<?> saveUser(@RequestBody User user){
      return new ResponseEntity<>(recommendationService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/job")
    public ResponseEntity<?> savejob(@RequestBody JobPosting job){
        return new ResponseEntity<>(recommendationService.savejob(job), HttpStatus.CREATED);
    }

    @PostMapping("/match")
    public ResponseEntity<?> matchjobs(@RequestBody User user){
        return new ResponseEntity<>(recommendationService.getMatchingJobs(user),HttpStatus.OK);
    }


}
