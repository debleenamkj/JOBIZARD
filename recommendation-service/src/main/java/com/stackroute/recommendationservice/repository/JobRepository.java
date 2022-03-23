package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.model.JobDetails;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface JobRepository extends Neo4jRepository<JobDetails,Long> {
    @Query("MATCH(j:JobPosting) WHERE $userSkills IN j.skillsRequired RETURN j")
    List<JobDetails> findBySkills(String userSkills);

    @Query("MATCH(j:JobPosting{jobRole:$jobRoles}) RETURN j")
    List<JobDetails> findByJobRole(String jobRoles);
}
