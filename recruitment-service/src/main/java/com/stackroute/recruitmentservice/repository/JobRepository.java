package com.stackroute.recruitmentservice.repository;

import com.stackroute.recruitmentservice.model.JobPosting;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository  extends MongoRepository<JobPosting,String> {

    List<JobPosting> findByJobDetailsList(String skill);


    Optional<JobPosting>  findByCompanyName(String companyName);
}
