package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.model.JobPosting;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface JobRepository extends Neo4jRepository<JobPosting,Long> {
}
