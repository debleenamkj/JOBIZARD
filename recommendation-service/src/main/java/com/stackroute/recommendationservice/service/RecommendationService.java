package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.exception.JobAlreadyPresentException;
import com.stackroute.recommendationservice.exception.JobNotFoundException;
import com.stackroute.recommendationservice.exception.UserAlreadyExistsException;
import com.stackroute.recommendationservice.exception.UserNotFoundException;
import com.stackroute.recommendationservice.model.JobDetails;
import com.stackroute.recommendationservice.model.Seeker;

import java.util.Set;

public interface RecommendationService {
    JobDetails savejob(JobDetails job) throws JobAlreadyPresentException;

    Seeker saveUser(Seeker seeker) throws UserAlreadyExistsException;

//    Set<Long> getMatchingJobs(Seeker seeker) throws UserNotFoundException;

    Set<String> getMatchingJobSeeker(JobDetails job) throws UserNotFoundException, JobNotFoundException;
}
