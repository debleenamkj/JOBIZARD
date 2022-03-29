package com.stackroute.recruitmentservice.repository;

import com.stackroute.recruitmentservice.model.JobPosting;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository  extends ElasticsearchRepository<JobPosting,String> {

    List<JobPosting> findByJobDetailsList(String skill);
}
