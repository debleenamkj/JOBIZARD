package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface UserRepository extends Neo4jRepository<User,String> {

}
