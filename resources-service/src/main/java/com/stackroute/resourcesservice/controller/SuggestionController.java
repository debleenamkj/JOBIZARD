package com.stackroute.resourcesservice.controller;

import com.stackroute.resourcesservice.domain.Suggestion;
import com.stackroute.resourcesservice.exception.SuggestionAlreadyExistsException;
import com.stackroute.resourcesservice.exception.SuggestionNotFoundException;
import com.stackroute.resourcesservice.service.SuggestionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api/v1/resources/suggestions/")
@Slf4j
public class SuggestionController {

    private SuggestionServiceImpl suggestionService;

    @Autowired
    public SuggestionController(SuggestionServiceImpl suggestionService) {
        log.info("Autowiring Suggestion Service");
        this.suggestionService = suggestionService;
    }

    private ResponseEntity responseEntity;

    @PostMapping("saveSuggestion")
    public ResponseEntity<?> saveSuggestion(@RequestBody Suggestion suggestion) throws SuggestionAlreadyExistsException {
        try{
            log.debug("SuggestionController - saveSuggestion");
            responseEntity = new ResponseEntity<>(suggestionService.saveSuggestion(suggestion), HttpStatus.CREATED);
        } catch (SuggestionAlreadyExistsException e) {
            log.error("SuggestionController - saveSuggestion");
            throw new SuggestionAlreadyExistsException();
        } catch (Exception e){
            log.error("SuggestionController - saveSuggestion");
            responseEntity = new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("getSuggestion")
    public ResponseEntity<?> getSuggestions(@RequestParam("skillType") String skillType) throws SuggestionNotFoundException {
        try{
            log.debug("SuggestionController - getSuggestions");
            responseEntity = new ResponseEntity<>(suggestionService.getSuggestionBySkillType(skillType), HttpStatus.OK);
        } catch (SuggestionNotFoundException e){
            log.error("SuggestionController - getSuggestions");
            throw new SuggestionNotFoundException();
        } catch (Exception e){
            log.error("SuggestionController - getSuggestions");
            responseEntity = new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("updateSuggestion")
    public ResponseEntity<?> updateSuggestions(@RequestBody Suggestion suggestion) throws SuggestionNotFoundException {
        try{
            log.debug("SuggestionController - updateSuggestions");
            responseEntity = new ResponseEntity<>(suggestionService.updateSuggestion(suggestion), HttpStatus.OK);
        } catch (SuggestionNotFoundException e){
            log.error("SuggestionController - updateSuggestions");
            throw new SuggestionNotFoundException();
        } catch (Exception e){
            log.error("SuggestionController - updateSuggestions");
            responseEntity = new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("deleteSuggestion")
    public ResponseEntity<?> deleteSuggestions(@RequestParam("suggestionId") int suggestionId) throws SuggestionNotFoundException {
        try{
            log.debug("SuggestionController - deleteSuggestions");
            responseEntity = new ResponseEntity<>(suggestionService.deleteSuggestion(suggestionId), HttpStatus.OK);
        } catch (SuggestionNotFoundException e){
            log.error("SuggestionController - deleteSuggestions");
            throw new SuggestionNotFoundException();
        } catch (Exception e){
            log.error("SuggestionController - deleteSuggestions");
            responseEntity = new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("getSourceBySkills")
    public ResponseEntity<?> getSourceBySkills() throws SuggestionNotFoundException {
        try{
            log.debug("SuggestionController - getSourceBySkills");
            responseEntity = new ResponseEntity<>(suggestionService.getUrlBySkills(), HttpStatus.OK);
        }catch (SuggestionNotFoundException e){
            log.error("SuggestionController - getSourceBySkills");
            throw new SuggestionNotFoundException();
        } catch (Exception e){
            log.error("SuggestionController - getSourceBySkills");
            responseEntity = new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("getBySuggestionId")
    public ResponseEntity<?> getSuggestionById(@RequestParam("suggestionId")int suggestionId) throws SuggestionNotFoundException {
        try{
            log.debug("SuggestionController - getSuggestionById");
            responseEntity = new ResponseEntity<>(suggestionService.getSuggestionBySuggestionId(suggestionId), HttpStatus.OK);
        } catch (SuggestionNotFoundException e){
            log.error("SuggestionController - getSuggestionById");
            throw new SuggestionNotFoundException();
        }
        catch (Exception e){
            log.error("SuggestionController - getSuggestionById");
            responseEntity = new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("get_skillTypes_by_category")
    public ResponseEntity<?> getSkillByCategory() throws SuggestionNotFoundException {
        try{
            log.debug("SuggestionController - getSkillByCategory");
            responseEntity = new ResponseEntity<>(suggestionService.getSkillTypesByCategory(), HttpStatus.OK);
        } catch (SuggestionNotFoundException e){
            log.error("SuggestionController - getSkillByCategory");
            throw new SuggestionNotFoundException();
        }
        catch (Exception e){
            log.error("SuggestionController - getSkillByCategory");
            responseEntity = new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
