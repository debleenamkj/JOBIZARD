package com.stackroute.resourcesservice.repository;

import com.stackroute.resourcesservice.domain.Company;
import com.stackroute.resourcesservice.domain.Suggestion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class SuggestionsRepositoryTest {
    @Autowired
    private SuggestionsRepository suggestionsRepository;

    private Suggestion suggestion;

    @BeforeEach
    public void setUp(){
        suggestion = new Suggestion();
        suggestion.setSuggestionId(99);
        suggestion.setCategory("testCategory");
        suggestion.setSkillType("testSkill");
        suggestion.setSourceUrl("testSource");
    }

    @AfterEach
    public void tearDown(){
        suggestionsRepository.deleteById(suggestion.getSuggestionId());
        suggestion = null;

    }

    @Test
    public void givenCompanyToSaveReturnCompany(){
        suggestionsRepository.insert(suggestion);
        Suggestion suggestion1 = suggestionsRepository.findById(suggestion.getSuggestionId()).get();
        assertNotNull(suggestion1);
        assertEquals(suggestion.getSuggestionId(), suggestion1.getSuggestionId());
    }
    @Test
    public void givenCompanyToDeleteReturnDeleteProduct(){
        suggestionsRepository.insert(suggestion);
        Suggestion suggestion1 = suggestionsRepository.findById(suggestion.getSuggestionId()).get();
        suggestionsRepository.deleteById(suggestion1.getSuggestionId());
        assertEquals(Optional.empty(), suggestionsRepository.findById(suggestion1.getSuggestionId()));
    }
    @Test
    public void givenSkillTypeReturnListOfSuggestions(){
        suggestionsRepository.insert(suggestion);
        Suggestion suggestion1 = suggestionsRepository.findById(suggestion.getSuggestionId()).get();
        List<Suggestion> suggestionList = List.of(suggestion);
        List<Suggestion> suggestionsFound = suggestionsRepository.findBySkillType(suggestion.getSkillType());

        assertEquals(suggestionList, suggestionsFound);
    }
}
