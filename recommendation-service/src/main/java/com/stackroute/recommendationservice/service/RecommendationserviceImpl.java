package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.exception.JobAlreadyPresentException;
import com.stackroute.recommendationservice.exception.UserAlreadyExistsException;
import com.stackroute.recommendationservice.exception.UserNotFoundException;
import com.stackroute.recommendationservice.model.JobPosting;
import com.stackroute.recommendationservice.model.User;
import com.stackroute.recommendationservice.repository.JobRepository;
import com.stackroute.recommendationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public JobPosting savejob(JobPosting job) throws JobAlreadyPresentException
    {
        if(jobRepository.findById(job.getJobId()).isPresent())
        {
            throw new JobAlreadyPresentException();
        }
        else {
            jobRepository.save(job);
        }
        return job;

    }

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException{
        if(userRepository.findById(user.getEmail()).isPresent())
        {
            throw new UserAlreadyExistsException();
        }
        else {
              userRepository.save(user);
        }
        return user;
    }

    @Override
    public Set<Long> getMatchingJobs(User user) throws UserNotFoundException {
        Set<Long> matchingJobIds = new HashSet<>();

        if(userRepository.findById(user.getEmail()).isEmpty())
        {
            throw new UserNotFoundException();
        }
        else{
            ArrayList<String> skills = user.getSkillSet();
            ArrayList<String> preferences = user.getJobPreferences();
            if(!skills.isEmpty())
            {
                for(String userSkils:skills) {
                    List<JobPosting> job1 = jobRepository.findBySkills(userSkils);
                    System.out.println(job1);
                    if(job1!=null){
                        for (JobPosting jobs:job1) {
                            matchingJobIds.add(jobs.getJobId());
                        }
                    }
                }
            }
            if(!preferences.isEmpty())
            {
                for (String jobRoles:preferences) {
                    List<JobPosting> job1 = jobRepository.findByJobRole(jobRoles);
                    if(job1!=null){
                        for (JobPosting jobs:job1) {
                            matchingJobIds.add(jobs.getJobId());
                        }
                    }
                }
            }
            if(!matchingJobIds.isEmpty()){
                createRelationships(user.getEmail(),matchingJobIds);
            }
        }

       return matchingJobIds;
    }

    public void createRelationships(String userEmail,Set<Long> matchingJobs){
        for (Long jobId:matchingJobs) {
            userRepository.createRelation(userEmail,jobId);
        }
    }
}
