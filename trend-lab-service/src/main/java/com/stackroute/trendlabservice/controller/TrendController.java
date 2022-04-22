package com.stackroute.trendlabservice.controller;

import com.stackroute.trendlabservice.model.SkillTrend;
import com.stackroute.trendlabservice.service.ExternalApiCaller;
import com.stackroute.trendlabservice.service.SkillTrendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;



@RestController
@RequestMapping("/api/v6")
//@CrossOrigin
@Slf4j
public class TrendController {

    private SkillTrendService skillTrendService;
    private ExternalApiCaller externalApiCaller;

    @Autowired
    public TrendController(SkillTrendService skillTrendService, ExternalApiCaller externalApiCaller) {
        log.info("Autowiring SkillTrendService Done");
        this.skillTrendService = skillTrendService;
        this.externalApiCaller = externalApiCaller;
    }

    @GetMapping("/check")
    public String hello(){
        return "hello the trend lab service is working fine.";
    }

    @GetMapping("/salary/{jobTitle}")
    public ResponseEntity<String> callExternalApiForSalaryTrend(@PathVariable String jobTitle){
        log.debug("Inside TrendController - callExternalApiForSalaryTrend");
        ResponseEntity<String> response = externalApiCaller.getStringResponseEntity(jobTitle);
        return response;
    }

//    @GetMapping("/salarys/{jobTitle}")
//    public ResponseEntity<String> callExternalApiForSalaryTrends(@PathVariable String jobTitle){
//        log.debug("Inside TrendController - callExternalApiForSalaryTrends");
//        String url = "https://infosalary.p.rapidapi.com/?job_title=" + jobTitle;
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("X-RapidAPI-Host", "infosalary.p.rapidapi.com");
//        headers.add("X-RapidAPI-Key", "f7bab1b14emshd729e803f0810d0p16072fjsn9a2c50459692");
//        HttpEntity<Object> entity = new HttpEntity<>(headers);
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
//        return response;
//    }

    ////////////////////////SkillTrends/////////////////////////

    @PostMapping("/skills")
    public SkillTrend postSkills(@RequestBody SkillTrend skillTrend){
        log.debug("Inside TrendController - postSkills");
        return skillTrendService.saveSkill(skillTrend);
    }

    @GetMapping("/getskills")
    public List<SkillTrend> getSkills(){
        log.debug("Inside TrendController - getSkills");
        return skillTrendService.getAllSkills();
    }

    @PutMapping("/updateskill")
    public SkillTrend updateSkills(@RequestBody SkillTrend skillTrend){
        log.debug("Inside TrendController - updateSkills");
        return skillTrendService.updateSkill(skillTrend);
    }

    @DeleteMapping("/deleteskills/{skillId}")
    public SkillTrend deleteSkillTrend(@PathVariable Long skillId){
        log.debug("Inside TrendController - deleteSkillTrend");
        return skillTrendService.deleteSkill(skillId);
    }

    @GetMapping("/getnames")
    public List<SkillTrend> getNames(){
        log.debug("Inside TrendController - getNames");
        return skillTrendService.getNameOfSkills();
    }

}

