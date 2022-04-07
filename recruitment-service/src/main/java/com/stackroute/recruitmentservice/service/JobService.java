package com.stackroute.recruitmentservice.service;

import com.stackroute.recruitmentservice.model.JobPosting;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface JobService     {
    JobPosting getCompany(String companyName);

    JobPosting addJob(JobPosting jobPosting);

    public JobPosting jobPosting(MultipartFile multipartFile, JobPosting jobPosting) throws IOException;

    Boolean deleteJobPost(String companyId);

    Iterable<JobPosting> showAllJobs();

    List<JobPosting> findBySkills(String skill);

    Optional<JobPosting> specificJob(String companyId);
//    public JobPosting deleteJobPost(String companyId);
}
