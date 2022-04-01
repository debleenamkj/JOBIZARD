package com.stackroute.resourcesservice.service;

import com.stackroute.resourcesservice.domain.Suggestion;
import com.stackroute.resourcesservice.exception.SuggestionAlreadyExistsException;
import com.stackroute.resourcesservice.exception.SuggestionNotFoundException;
import com.stackroute.resourcesservice.AggregateDTO.SkillAggregate;
import com.stackroute.resourcesservice.AggregateDTO.SourceUrlAggregate;

import java.util.List;

public interface SuggestionService {

    Suggestion saveSuggestion(Suggestion suggestion) throws SuggestionAlreadyExistsException;
    List<Suggestion> getSuggestionBySkillType(String skillType ) throws SuggestionNotFoundException;
    Suggestion updateSuggestion(Suggestion suggestion) throws SuggestionNotFoundException;
    boolean deleteSuggestion (int suggestionId) throws SuggestionNotFoundException;
    List<SourceUrlAggregate> getUrlBySkills() throws SuggestionNotFoundException;
    Suggestion getSuggestionBySuggestionId(int suggestionId) throws SuggestionNotFoundException;
    List<SkillAggregate> getSkillTypesByCategory() throws SuggestionNotFoundException;
}
