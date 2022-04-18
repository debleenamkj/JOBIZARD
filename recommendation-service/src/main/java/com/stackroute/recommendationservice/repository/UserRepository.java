package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.model.JobDetails;
import com.stackroute.recommendationservice.model.Seeker;
import org.neo4j.ogm.model.Node;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends Neo4jRepository<Seeker,String> {


    @Query("MATCH(u1:Seeker{email:$email}),(j:JobDetails{emailId:$job}) CREATE(u1)-[:from]->(j) RETURN u1")
    Seeker createRelation(String email, String job);

    @Query("MATCH(j:Seeker) WHERE $requiredSkills IN j.skillSet RETURN j")
    List<Seeker> findBySkillSet(String requiredSkills);

    @Query("MATCH(j:JobDetails {emailId: $job}), (u1:Seeker {email:$email}) RETURN EXISTS((u1)-[:from]-(j))")
    boolean checkRelation(String email, String job);

    @Query("MATCH(j:Seeker) WHERE $education IN j.education RETURN j")
    List<Seeker> findByEducation(String education);
}
