package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.model.JobPosting;
import com.stackroute.recommendationservice.model.User;

import java.util.Set;

public interface RecommendationService {
    JobPosting savejob(JobPosting job);

    User saveUser(User user);

    Set<Long> getMatchingJobs(User user);
}
