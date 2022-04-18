package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.model.JobDetails;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends Neo4jRepository<JobDetails,String> {

    @Query("MATCH(j:JobDetails) WHERE $userSkills IN j.skillsRequired RETURN j")
    List<JobDetails> findBySkills(String userSkills);

//    @Query("MATCH(j:JobDetails{jobRole:$jobRoles}) RETURN j")
//    List<JobDetails> findByJobRole(String jobRoles);

//    void createRelation(Long jobId, String seeker);
}
