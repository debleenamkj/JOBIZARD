package com.stackroute.resourcesservice.service;

import com.stackroute.resourcesservice.domain.Suggestion;
import com.stackroute.resourcesservice.exception.SuggestionAlreadyExistsException;
import com.stackroute.resourcesservice.exception.SuggestionNotFoundException;
import com.stackroute.resourcesservice.AggregateDTO.SkillAggregate;
import com.stackroute.resourcesservice.AggregateDTO.SourceUrlAggregate;
import com.stackroute.resourcesservice.repository.SuggestionsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SuggestionServiceImpl implements SuggestionService{

    private SuggestionsRepository suggestionsRepository;

    @Autowired
    public SuggestionServiceImpl(SuggestionsRepository suggestionsRepository) {
        log.info("Autowiring suggestion repository");
        this.suggestionsRepository = suggestionsRepository;
    }

    @Override
    public Suggestion saveSuggestion(Suggestion suggestion) throws SuggestionAlreadyExistsException {
        log.debug("inside service.saveSuggestion");
        if (suggestionsRepository.findById(suggestion.getSuggestionId()).isPresent())
            throw new SuggestionAlreadyExistsException();
        return suggestionsRepository.save(suggestion);
    }

    @Override
    public List<Suggestion> getSuggestionBySkillType(String skillType) throws SuggestionNotFoundException {
        List<Suggestion> suggestionList;

        suggestionList = suggestionsRepository.findBySkillType(skillType);
        if (suggestionList == null || suggestionList.size()==0)
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

    @Override
    public List<SourceUrlAggregate> getUrlBySkills() throws SuggestionNotFoundException{
        List<SourceUrlAggregate> list;
        list = suggestionsRepository.groupBySkillTypeAndSourceUrl();
        if (list.isEmpty() || list == null)
            throw new SuggestionNotFoundException();
        return list;
    }

    @Override
    public Suggestion getSuggestionBySuggestionId(int suggestionId) throws SuggestionNotFoundException {
        Optional<Suggestion> suggestionOptional = suggestionsRepository.findById(suggestionId);
        if(suggestionOptional.isEmpty())
            throw new SuggestionNotFoundException();
        return suggestionOptional.get();
    }

    @Override
    public List<SkillAggregate> getSkillTypesByCategory() throws SuggestionNotFoundException {
        List<SkillAggregate> skillAggregateList;
        skillAggregateList = suggestionsRepository.groupByCategoryAndSkillType();
        if (skillAggregateList == null || skillAggregateList.size()==0)
            throw new SuggestionNotFoundException();
        return skillAggregateList;
    }

}
