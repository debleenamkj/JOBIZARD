package com.stackroute.resourcesservice.service;

import com.stackroute.resourcesservice.domain.Suggestion;
import com.stackroute.resourcesservice.exception.SuggestionAlreadyExistsException;
import com.stackroute.resourcesservice.exception.SuggestionNotFoundException;
import com.stackroute.resourcesservice.repository.SuggestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestionServiceImpl implements SuggestionService{

    private SuggestionsRepository suggestionsRepository;

    @Autowired
    public SuggestionServiceImpl(SuggestionsRepository suggestionsRepository) {
        this.suggestionsRepository = suggestionsRepository;
    }

    @Override
    public Suggestion saveSuggestion(Suggestion suggestion) throws SuggestionAlreadyExistsException {
        if (suggestionsRepository.findById(suggestion.getSuggestionId()).isPresent())
            throw new SuggestionAlreadyExistsException();
        return suggestionsRepository.save(suggestion);
    }

    @Override
    public List<Suggestion> getSuggestionBySkillType(String skillType) throws SuggestionNotFoundException {
        List<Suggestion> suggestionList;

        suggestionList = suggestionsRepository.findBySkillType(skillType);
        if (suggestionList == null)
            throw new SuggestionNotFoundException();

        return suggestionList;
    }

    @Override
    public Suggestion updateSuggestion(Suggestion suggestion) throws SuggestionNotFoundException {
        if (suggestionsRepository.findById(suggestion.getSuggestionId()).isEmpty())
            throw new SuggestionNotFoundException();

        return suggestionsRepository.save(suggestion);
    }

    @Override
    public boolean deleteSuggestion(int suggestionId) throws SuggestionNotFoundException {
        if (suggestionsRepository.findById(suggestionId).isEmpty())
            throw new SuggestionNotFoundException();
        suggestionsRepository.deleteById(suggestionId);
        return true;
    }
}
