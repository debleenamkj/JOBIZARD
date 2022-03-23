package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.model.JobPosting;
import com.stackroute.recommendationservice.model.User;
import com.stackroute.recommendationservice.repository.JobRepository;
import com.stackroute.recommendationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendationserviceImpl implements RecommendationService{

    private JobRepository jobRepository;
    private UserRepository userRepository;

    @Autowired
    public RecommendationserviceImpl(JobRepository jobRepository, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    @Override
    public JobPosting savejob(JobPosting job){
        return jobRepository.save(job);
    }

    @Override
    public User saveUser(User user){
        return userRepository.save(user);
    }
}
