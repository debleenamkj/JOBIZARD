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
@CrossOrigin
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
        return "hello the trend lab service is working fine";
    }

    @GetMapping("/salary/{jobtitle}")
    public ResponseEntity<String> callExternalApiForSalaryTrend(@PathVariable String jobTitle){
        log.debug("Inside TrendController - callExternalApiForSalaryTrend");
        ResponseEntity<String> response = externalApiCaller.getStringResponseEntity(jobTitle);
        return response;
    }


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

    @PostMapping("/updateskill")
    public SkillTrend updateSkills(@RequestBody SkillTrend skillTrend){
        log.debug("Inside TrendController - updateSkills");
        return skillTrendService.updateSkill(skillTrend);
    }

    @DeleteMapping("/deleteskills/{skillId}")
    public SkillTrend deleteSkillTrend(@PathVariable Long skillId){
        log.debug("Inside TrendController - deleteSkillTrend");
        return skillTrendService.deleteSkill(skillId);
    }

}

