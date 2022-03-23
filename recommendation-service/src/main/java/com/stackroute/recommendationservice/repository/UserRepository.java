package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Set;

public interface UserRepository extends Neo4jRepository<User,String> {


    @Query("MATCH(u:User{email:$email}),(j:JobPosting{jobId:$job}) CREATE(u)-[:from]->(j)")
    void createRelation(String email, Long job);
}
