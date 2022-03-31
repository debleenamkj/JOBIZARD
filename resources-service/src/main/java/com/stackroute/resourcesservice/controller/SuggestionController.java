package com.stackroute.resourcesservice.controller;

import com.stackroute.resourcesservice.domain.Suggestion;
import com.stackroute.resourcesservice.exception.SuggestionAlreadyExistsException;
import com.stackroute.resourcesservice.exception.SuggestionNotFoundException;
import com.stackroute.resourcesservice.service.SuggestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api/v1/resources/suggestions/")
public class SuggestionController {

    private SuggestionServiceImpl suggestionService;

    @Autowired
    public SuggestionController(SuggestionServiceImpl suggestionService) {
        this.suggestionService = suggestionService;
    }

    private ResponseEntity responseEntity;

    @PostMapping("saveSuggestion")
    public ResponseEntity<?> saveSuggestion(@RequestBody Suggestion suggestion) throws SuggestionAlreadyExistsException {
        try{
            responseEntity = new ResponseEntity<>(suggestionService.saveSuggestion(suggestion), HttpStatus.OK);
        } catch (SuggestionAlreadyExistsException e) {
            throw new SuggestionAlreadyExistsException();
        } catch (Exception e){
            responseEntity = new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("getSuggestion")
    public ResponseEntity<?> getSuggestions(@RequestParam("skillType") String skillType) throws SuggestionNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(suggestionService.getSuggestionBySkillType(skillType), HttpStatus.OK);
        } catch (SuggestionNotFoundException e){
            throw new SuggestionNotFoundException();
        } catch (Exception e){
            responseEntity = new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("updateSuggestion")
    public ResponseEntity<?> getSuggestions(@RequestBody Suggestion suggestion) throws SuggestionNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(suggestionService.updateSuggestion(suggestion), HttpStatus.OK);
        } catch (SuggestionNotFoundException e){
            throw new SuggestionNotFoundException();
        } catch (Exception e){
            responseEntity = new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("deleteSuggestion")
    public ResponseEntity<?> deleteSuggestions(@RequestParam("suggestionId") int suggestionId) throws SuggestionNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(suggestionService.deleteSuggestion(suggestionId), HttpStatus.OK);
        } catch (SuggestionNotFoundException e){
            throw new SuggestionNotFoundException();
        } catch (Exception e){
            responseEntity = new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
