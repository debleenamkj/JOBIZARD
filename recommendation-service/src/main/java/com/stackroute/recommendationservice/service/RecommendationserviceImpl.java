package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.exception.JobAlreadyPresentException;
import com.stackroute.recommendationservice.exception.JobNotFoundException;
import com.stackroute.recommendationservice.exception.UserAlreadyExistsException;
import com.stackroute.recommendationservice.exception.UserNotFoundException;
import com.stackroute.recommendationservice.model.JobDetails;
import com.stackroute.recommendationservice.model.Seeker;
import com.stackroute.recommendationservice.repository.JobDetailsRepository;
import com.stackroute.recommendationservice.repository.JobRepository;
import com.stackroute.recommendationservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin("*")
@Service
@Slf4j
public class RecommendationserviceImpl implements RecommendationService{

    private JobRepository jobRepository;
    private JobDetailsRepository jobDetailsRepository;
    private UserRepository userRepository;

    @Autowired
    public RecommendationserviceImpl(JobRepository jobRepository, UserRepository userRepository,JobDetailsRepository jobDetailsRepository) {
        log.info("Autowiring jobRepository");
        this.jobRepository = jobRepository;
        log.info("Autowiring userRepository");
        this.userRepository = userRepository;
        this.jobDetailsRepository = jobDetailsRepository;
    }

    @Override
    public JobDetails savejob(JobDetails job) throws JobAlreadyPresentException
    {
        log.debug("In RecommendationserviceImpl - savejob");
        try{
            if(jobRepository.findById(job.getEmailId()).isPresent())
            {
                  jobRepository.save(job);

//                log.error("RecommendationserviceImpl - savejob : JobAlreadyPresentException");
//                throw new JobAlreadyPresentException();
            }
            else {
                jobRepository.save(job);
            }
        }catch (Exception exception){
            log.error("RecommendationserviceImpl - savejob : "+exception);
            exception.printStackTrace();
        }

        return job;

    }

    @Override
    public Seeker saveUser(Seeker seeker) throws UserAlreadyExistsException{

        log.debug("In RecommendationserviceImpl - saveUser");
        try {
            if(userRepository.findById(seeker.getEmail()).isPresent())
            {
//                log.error("RecommendationserviceImpl - saveUser : UserAlreadyExistsException");
//                throw new UserAlreadyExistsException();
                userRepository.save(seeker);
                log.info(" getting from rabbitmq updating details"+seeker);
            }
            else {
                userRepository.save(seeker);
            }
        }catch (Exception exception)
        {
            log.error("RecommendationserviceImpl - saveUser : "+exception);
            exception.printStackTrace();
        }

        return seeker;
    }


    @Override
    public Set<String> getMatchingJobSeeker(JobDetails job) throws JobNotFoundException {
        log.debug("In RecommendationserviceImpl - getMachingJobSeeker");
        Set<String> matchingJobSeekers = new HashSet<>();
        Set<String> matchingSeeeker = new HashSet<>();
        System.out.println("email id of job seeker \t"+job.getEmailId()+"\n");

//        List<JobDetails> all = jobRepository.findAll();
//        for (JobDetails details:all) {
//            System.out.println(details.getEmailId());
//        }

//        JobDetails jobDetails = jobRepository.findByEmailId(job.getEmailId());
        System.out.println(userRepository.findById("vishnu24@gmail.com"));
        System.out.println(jobRepository.findByEmailId(job.getEmailId()));
        String email = job.getEmailId();
        System.out.println(jobDetailsRepository.findById(email).get());

        try{
            if(jobRepository.findById(job.getEmailId()).isEmpty())
            {
                log.error("RecommendationserviceImpl - getMachingJobSeeker : JobNotFoundException");
                throw new JobNotFoundException();
            }
            else{
                ArrayList<String> skills = job.getSkillsRequired();
                String education = job.getEducation();

                if(!skills.isEmpty())
                {
                    System.out.println("\n \n \n \n \n \n skills"+skills);
                    for(String requiredSkills:skills) {
                        log.info("  required skills"+  requiredSkills);
                        if(requiredSkills!=null){
                            List<Seeker> seeker1 = userRepository.findBySkillSet(requiredSkills);
                            if(seeker1!=null){
                                for (Seeker seeker:seeker1 ) {
                                    matchingJobSeekers.add(seeker.getEmail());
                                }
                            }else{
                                log.error("RecommendationserviceImpl - getMachingJobSeeker : Required skills for a job is empty");
                            }
                        }
                    }
                }
                if(education!=null)
                {
                    List<Seeker> seeker1 = userRepository.findByEducation(education);
                    if(seeker1!=null){
                        for (Seeker seeker2: seeker1){
                            matchingJobSeekers.add(seeker2.getEmail());
                        }
                    }
                }

                if(!matchingJobSeekers.isEmpty()){
                    matchingSeeeker = createRelationships1(job.getEmailId(),matchingJobSeekers);
                }else{
                    log.error("RecommendationserviceImpl - getMachingJobSeeker : User skill set is empty");
                }
            }
        }catch (JobNotFoundException exception){
            log.error("RecommendationserviceImpl - getMachingJobSeeker : "+exception);
            exception.printStackTrace();
        }

        return matchingSeeeker;
    }

    public Set<String> createRelationships1(String jobId,Set<String> matchingSeekers){
        Set<String> matchingSeeker = new HashSet<>();
        log.debug("In RecommendationserviceImpl - createRelationships1");
        try {
            for (String seeker:matchingSeekers) {
                System.out.println(this.userRepository.checkRelation(seeker,jobId));
                if(!(this.userRepository.checkRelation(seeker,jobId))) {
                    log.info("RecommendationserviceImpl - createRelationship : creating RelationShip");
                    Seeker seeker1 = userRepository.createRelation(seeker, jobId);
                    matchingSeeker.add(seeker1.getEmail());
                    System.out.println(matchingSeeker);
                }else{
                    log.info("RecommendationserviceImpl - createRelationship : RelationShip already exists");
                    matchingSeeker.add(seeker);
                }
            }
        }catch (Exception exception){
            log.error("In RecommendationserviceImpl - createRelationships1"+exception);
            exception.printStackTrace();
        }
        return matchingSeeker;

    }

}
