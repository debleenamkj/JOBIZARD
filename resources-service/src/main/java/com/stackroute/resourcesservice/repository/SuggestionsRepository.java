package com.stackroute.resourcesservice.repository;

import com.stackroute.resourcesservice.domain.Suggestion;
import com.stackroute.resourcesservice.repository.AggregateDTO.SuggestionAggregate;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuggestionsRepository extends MongoRepository<Suggestion, Integer> {

    List<Suggestion> findBySkillType(String skillType);

    @Aggregation("{$group: {_id: $skillType, source: { $addToSet: $sourceUrl}}}")
    List<SuggestionAggregate> groupBySkillTypeAndSourceUrl();
}
