package com.stackroute.resourcesservice.service;

import com.stackroute.resourcesservice.domain.Suggestion;
import com.stackroute.resourcesservice.exception.SuggestionAlreadyExistsException;
import com.stackroute.resourcesservice.exception.SuggestionNotFoundException;

import java.util.List;

public interface SuggestionService {

    public Suggestion saveSuggestion(Suggestion suggestion) throws SuggestionAlreadyExistsException;
    public List<Suggestion> getSuggestionBySkillType(String skillType ) throws SuggestionNotFoundException;
    public Suggestion updateSuggestion(Suggestion suggestion) throws SuggestionNotFoundException;
    public boolean deleteSuggestion (int suggestionId) throws SuggestionNotFoundException;
}
