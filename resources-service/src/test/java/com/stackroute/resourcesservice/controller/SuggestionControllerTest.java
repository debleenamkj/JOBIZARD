package com.stackroute.resourcesservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.resourcesservice.domain.Suggestion;
import com.stackroute.resourcesservice.service.SuggestionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class SuggestionControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Mock
    SuggestionServiceImpl suggestionService;
    @InjectMocks
    SuggestionController suggestionController;

    private Suggestion suggestion;

    @BeforeEach
    public void setUp(){
        suggestion = new Suggestion();
        suggestion.setSuggestionId(99);
        suggestion.setCategory("testCategory");
        suggestion.setSkillType("testSkill");
        suggestion.setSourceUrl("testSource");
        mockMvc = MockMvcBuilders.standaloneSetup(suggestionController).build();
    }

    @AfterEach
    public void tearDown(){
        suggestion = null;
    }

    private static String jsonToString(final Object o) throws JsonProcessingException {
        String jsonContent;
        try {
            log.debug("SuggestionControllerTest - jsonToString");
            ObjectMapper mapper = new ObjectMapper();
            jsonContent = mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("SuggestionControllerTest - jsonToString");
            jsonContent = "JsonProcessingException";
        }

        return jsonContent;
    }

    @Test
    public void givenSuggestionToSaveReturnSuggestion() throws Exception{
        when(suggestionService.saveSuggestion(suggestion) ).thenReturn(suggestion);

        mockMvc.perform(post("/api/v1/resources/suggestions/saveSuggestion/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(suggestion)))
                .andExpect(status().isCreated())
                .andDo(print());
        verify(suggestionService,times(1)).saveSuggestion(any());
    }

    @Test
    public void givenSuggestionToUpdateReturnUpdatedSuggestion() throws Exception{
        Suggestion suggestion1 = new Suggestion();
        suggestion1.setSuggestionId(suggestion.getSuggestionId());
        suggestion1.setCategory("updatedCategory");
        suggestion1.setSkillType("updateSkillType");
        suggestion1.setSourceUrl("updatedSourceUrl");

        when(suggestionService.updateSuggestion(any())).thenReturn(suggestion1);

        mockMvc.perform(put("/api/v1/resources/suggestions/updateSuggestion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(suggestion1)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(suggestionService, times(1)).updateSuggestion(any());
    }
}
