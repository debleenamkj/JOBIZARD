package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.model.JobDetails;
import com.stackroute.recommendationservice.model.Seeker;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface UserRepository extends Neo4jRepository<Seeker,String> {


    @Query("MATCH(u1:Seeker{email:$email}),(j:JobDetails{jobId:$job}) CREATE(u1)-[:from]->(j)")
    void createRelation(String email, Long job);

    @Query("MATCH(j:Seeker) WHERE $requiredSkills IN j.skillSet RETURN j")
    List<Seeker> findBySkillSet(String requiredSkills);
}
