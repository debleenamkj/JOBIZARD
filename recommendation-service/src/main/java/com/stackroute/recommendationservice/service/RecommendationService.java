package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.model.JobPosting;
import com.stackroute.recommendationservice.model.User;

public interface RecommendationService {
    JobPosting savejob(JobPosting job);

    User saveUser(User user);
}
