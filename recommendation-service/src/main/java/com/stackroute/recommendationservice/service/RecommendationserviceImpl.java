package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.exception.JobAlreadyPresentException;
import com.stackroute.recommendationservice.exception.UserAlreadyExistsException;
import com.stackroute.recommendationservice.exception.UserNotFoundException;
import com.stackroute.recommendationservice.model.JobDetails;
import com.stackroute.recommendationservice.model.Seeker;
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
    public JobDetails savejob(JobDetails job) throws JobAlreadyPresentException
    {
        try{
            if(jobRepository.findById(job.getJobId()).isPresent())
            {
                throw new JobAlreadyPresentException();
            }
            else {
                jobRepository.save(job);
            }
        }catch (JobAlreadyPresentException e){
            System.out.println(e.toString());
        }

        return job;

    }

    @Override
    public Seeker saveUser(Seeker seeker) throws UserAlreadyExistsException{
        try {
            if(userRepository.findById(seeker.getEmail()).isPresent())
            {
                throw new UserAlreadyExistsException();
            }
            else {
                userRepository.save(seeker);
            }
        }catch (UserAlreadyExistsException e)
        {
            System.out.println(e.toString());
        }

        return seeker;
    }

    @Override
    public Set<Long> getMatchingJobs(Seeker seeker) throws UserNotFoundException {
        Set<Long> matchingJobIds = new HashSet<>();

        try{
            if(userRepository.findById(seeker.getEmail()).isEmpty())
            {
                throw new UserNotFoundException();
            }
            else{
                ArrayList<String> skills = seeker.getSkillSet();
                ArrayList<String> preferences = seeker.getJobPreferences();
                if(!skills.isEmpty())
                {
                    for(String userSkils:skills) {
                        List<JobDetails> job1 = jobRepository.findBySkills(userSkils);
                        System.out.println(job1);
                        if(job1!=null){
                            for (JobDetails jobs:job1) {
                                matchingJobIds.add(jobs.getJobId());
                            }
                        }
                    }
                }
                if(!preferences.isEmpty())
                {
                    for (String jobRoles:preferences) {
                        List<JobDetails> job1 = jobRepository.findByJobRole(jobRoles);
                        if(job1!=null){
                            for (JobDetails jobs:job1) {
                                matchingJobIds.add(jobs.getJobId());
                            }
                        }
                    }
                }
                if(!matchingJobIds.isEmpty()){
                    createRelationships(seeker.getEmail(),matchingJobIds);
                }
            }
        }catch (UserNotFoundException e){
            System.out.println(e.toString());
        }
        return matchingJobIds;
    }

    @Override
    public Set<String> getMatchingJobSeeker(JobDetails job) throws UserNotFoundException {
        Set<String> matchingJobSeekers = new HashSet<>();
        try{
            if(jobRepository.findById(job.getJobId()).isEmpty())
            {
                throw new UserNotFoundException();
            }
            else{
                ArrayList<String> skills = job.getSkillsRequired();
                String jobRole = job.getJobRole();
                if(!skills.isEmpty())
                {
                    for(String requiredSkills:skills) {
                        List<Seeker> seeker1 = userRepository.findBySkillSet(requiredSkills);
                        System.out.println(seeker1);
                        if(seeker1!=null){
                            for (Seeker seeker:seeker1 ) {
                                matchingJobSeekers.add(seeker.getEmail());
                            }
                        }
                    }
                }

                if(!matchingJobSeekers.isEmpty()){
                    createRelationships1(job.getJobId(),matchingJobSeekers);
                }
            }
        }catch (UserNotFoundException e){
            System.out.println(e.toString());
        }
        return matchingJobSeekers;
    }

    public void createRelationships1(Long jobId,Set<String> matchingSeekers){
        for (String seeker:matchingSeekers) {
//            userRepository.createRelation(userEmail,jobId);
            userRepository.createRelation(seeker,jobId);
        }
    }
    public void createRelationships(String userEmail,Set<Long> matchingJobs){
        for (Long jobId:matchingJobs) {
            userRepository.createRelation(userEmail,jobId);
        }
    }
}
