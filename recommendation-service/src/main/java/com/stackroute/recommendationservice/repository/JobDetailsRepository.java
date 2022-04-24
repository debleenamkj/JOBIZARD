package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.model.JobDetails;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface JobDetailsRepository extends Neo4jRepository<JobDetails,String> {
}
