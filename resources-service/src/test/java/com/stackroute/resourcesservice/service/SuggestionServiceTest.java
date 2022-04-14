package com.stackroute.resourcesservice.service;

import com.stackroute.resourcesservice.AggregateDTO.SkillAggregate;
import com.stackroute.resourcesservice.AggregateDTO.SourceUrlAggregate;
import com.stackroute.resourcesservice.domain.Suggestion;
import com.stackroute.resourcesservice.exception.SuggestionAlreadyExistsException;
import com.stackroute.resourcesservice.exception.SuggestionNotFoundException;
import com.stackroute.resourcesservice.repository.SequenceRepository;
import com.stackroute.resourcesservice.repository.SuggestionsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SuggestionServiceTest {

    @Mock
    SuggestionsRepository suggestionsRepository;
    @InjectMocks
    SuggestionServiceImpl suggestionService;

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
        suggestion = null;
    }

    @Test
    public void givenSuggestionToSaveReturnSuggestion() throws SuggestionAlreadyExistsException {
        when(suggestionsRepository.findById(suggestion.getSuggestionId())).thenReturn(Optional.ofNullable(null));
        when(suggestionsRepository.save(suggestion)).thenReturn(suggestion);

        assertEquals(suggestion,suggestionService.saveSuggestion(suggestion));
        verify(suggestionsRepository,times(1)).findById(anyInt());
        verify(suggestionsRepository,times(1)).save(any());
    }
    @Test
    public void givenSuggestionToSaveReturnFailure(){
        when(suggestionsRepository.findById(suggestion.getSuggestionId())).thenReturn(Optional.ofNullable(suggestion));

        assertThrows(SuggestionAlreadyExistsException.class,()->suggestionService.saveSuggestion(suggestion));
        verify(suggestionsRepository,times(1)).findById(anyInt());
        verify(suggestionsRepository,times(0)).save(any());
    }
    @Test
    public void givenSkillTypeToReturnMatchedSuggestions() throws SuggestionNotFoundException {
        when(suggestionsRepository.findBySkillType(suggestion.getSkillType())).thenReturn(List.of(suggestion));

        assertEquals(List.of(suggestion), suggestionService.getSuggestionBySkillType(suggestion.getSkillType()));
        verify(suggestionsRepository,times(1)).findBySkillType(any());
    }
    @Test
    public void givenSkillTypeToReturnFailure() {
        when(suggestionsRepository.findBySkillType(suggestion.getSkillType())).thenReturn(List.of());

        assertThrows(SuggestionNotFoundException.class,()-> suggestionService.getSuggestionBySkillType(suggestion.getSkillType()));
        verify(suggestionsRepository,times(1)).findBySkillType(any());
    }
    @Test
    public void givenSuggestionToUpdateReturnUpdatedSuggestion() throws SuggestionNotFoundException {
        Suggestion updatedSuggestion = suggestion;
        updatedSuggestion.setSourceUrl("UpdatedUrl");
        updatedSuggestion.setCategory("UpdatedCategory");
        updatedSuggestion.setSkillType("UpdatedSkillType");

        when(suggestionsRepository.findById(updatedSuggestion.getSuggestionId())).thenReturn(Optional.ofNullable(suggestion));
        when(suggestionsRepository.save(any())).thenReturn(updatedSuggestion);

        assertEquals(updatedSuggestion, suggestionService.updateSuggestion(updatedSuggestion));
        verify(suggestionsRepository, times(1)).findById(anyInt());
        verify(suggestionsRepository, times(1)).save(any());
    }
    @Test
    public void givenSuggestionToUpdateReturnFailure() throws SuggestionNotFoundException {
        Suggestion updatedSuggestion = suggestion;
        updatedSuggestion.setSourceUrl("UpdatedUrl");
        updatedSuggestion.setCategory("UpdatedCategory");
        updatedSuggestion.setSkillType("UpdatedSkillType");
        when(suggestionsRepository.findById(updatedSuggestion.getSuggestionId())).thenReturn(Optional.ofNullable(null));

        assertThrows(SuggestionNotFoundException.class,()-> suggestionService.updateSuggestion(updatedSuggestion));
        verify(suggestionsRepository, times(1)).findById(anyInt());
        verify(suggestionsRepository, times(0)).save(any());
    }
    @Test
    public void givenSuggestionIdToDeleteReturnDeleteSuccess() throws SuggestionNotFoundException {
        when(suggestionsRepository.findById(suggestion.getSuggestionId())).thenReturn(Optional.ofNullable(suggestion));

        assertTrue(suggestionService.deleteSuggestion(suggestion.getSuggestionId()));
        verify(suggestionsRepository,times(1)).findById(anyInt());
        verify(suggestionsRepository, times(1)).deleteById(anyInt());
    }
    @Test
    public void givenSuggestionIdToDeleteReturnDeleteFailure() {
        when(suggestionsRepository.findById(suggestion.getSuggestionId())).thenReturn(Optional.ofNullable(null));

        assertThrows(SuggestionNotFoundException.class,()->suggestionService.deleteSuggestion(suggestion.getSuggestionId()));
        verify(suggestionsRepository,times(1)).findById(anyInt());
        verify(suggestionsRepository, times(0)).deleteById(anyInt());
    }

    @Test
    public void givenGroupToFindSuggestionsGroupedBySkillType() throws SuggestionNotFoundException {
        SourceUrlAggregate sourceUrlAggregate = new SourceUrlAggregate();
        sourceUrlAggregate.setSkillType("testSkillType");
        sourceUrlAggregate.setSource(List.of("testSource1","testSource"));

        when(suggestionsRepository.groupBySkillTypeAndSourceUrl()).thenReturn( List.of(sourceUrlAggregate));

        assertEquals(List.of(sourceUrlAggregate), suggestionService.getUrlBySkills());
        verify(suggestionsRepository,times(1)).groupBySkillTypeAndSourceUrl();
    }
    @Test
    public void givenUrlGroupToReturnFailure() {

        when(suggestionsRepository.groupBySkillTypeAndSourceUrl()).thenReturn(List.of());

        assertThrows(SuggestionNotFoundException.class,()-> suggestionService.getUrlBySkills());
        verify(suggestionsRepository,times(1)).groupBySkillTypeAndSourceUrl();
    }
    @Test
    public void givenGroupToFindSuggestionsGroupedByCategory() throws SuggestionNotFoundException {
        SkillAggregate skillAggregate = new SkillAggregate();
        skillAggregate.setCategory("testCategory");
        skillAggregate.setSkillTypes(List.of("testSkill1","testSkill2"));

        when(suggestionsRepository.groupByCategoryAndSkillType()).thenReturn( List.of(skillAggregate));

        assertEquals(List.of(skillAggregate), suggestionService.getSkillTypesByCategory());
        verify(suggestionsRepository,times(1)).groupByCategoryAndSkillType();
    }
    @Test
    public void givenSkillGroupToReturnFailure() {

        when(suggestionsRepository.groupByCategoryAndSkillType()).thenReturn(List.of());

        assertThrows(SuggestionNotFoundException.class,()-> suggestionService.getSkillTypesByCategory());
        verify(suggestionsRepository,times(1)).groupByCategoryAndSkillType();
    }
    @Test
    public void givenSuggestionIdToReturnSuggestion() throws SuggestionNotFoundException {
        when(suggestionsRepository.findById(suggestion.getSuggestionId())).thenReturn(Optional.ofNullable(suggestion));

        assertEquals(suggestion, suggestionService.getSuggestionBySuggestionId(suggestion.getSuggestionId()));
        verify(suggestionsRepository,times(1)).findById(anyInt());
    }
    @Test
    public void givenSuggestionIdToReturnFailure() {

        when(suggestionsRepository.findById(suggestion.getSuggestionId())).thenReturn(Optional.ofNullable(null));

        assertThrows(SuggestionNotFoundException.class, ()->suggestionService.getSuggestionBySuggestionId(suggestion.getSuggestionId()));
        verify(suggestionsRepository,times(1)).findById(anyInt());
    }
}
