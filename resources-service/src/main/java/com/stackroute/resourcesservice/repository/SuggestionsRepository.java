package com.stackroute.resourcesservice.repository;

import com.stackroute.resourcesservice.domain.Suggestion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuggestionsRepository extends MongoRepository<Suggestion, Integer> {

    List<Suggestion> findBySkillType(String skillType);
}
