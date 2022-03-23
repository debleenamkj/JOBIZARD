package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.exception.JobAlreadyPresentException;
import com.stackroute.recommendationservice.exception.UserAlreadyExistsException;
import com.stackroute.recommendationservice.exception.UserNotFoundException;
import com.stackroute.recommendationservice.model.JobPosting;
import com.stackroute.recommendationservice.model.User;

import java.util.Set;

public interface RecommendationService {
    JobPosting savejob(JobPosting job) throws JobAlreadyPresentException;

    User saveUser(User user) throws UserAlreadyExistsException;

    Set<Long> getMatchingJobs(User user) throws UserNotFoundException;
}
