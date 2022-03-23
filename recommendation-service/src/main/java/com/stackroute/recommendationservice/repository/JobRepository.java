package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.model.JobPosting;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface JobRepository extends Neo4jRepository<JobPosting,Long> {
    @Query("MATCH(j:JobPosting) WHERE $userSkills IN j.skillsRequired RETURN j")
    List<JobPosting> findBySkills(String userSkills);

    @Query("MATCH(j:JobPosting{jobRole:$jobRoles}) RETURN j")
    List<JobPosting> findByJobRole(String jobRoles);
}
