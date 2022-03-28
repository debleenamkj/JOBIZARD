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
        return "hello the service is working alright";
    }

    @GetMapping("/salary")
    public ResponseEntity<String> callExternalApiForSalaryTrend(@RequestParam("job_title")String job_title){
        log.debug("Inside");
        ResponseEntity<String> response = externalApiCaller.getStringResponseEntity(job_title);
        return response;
    }


    ////////////////////////SkillTrends/////////////////////////

    @PostMapping("/skills")
    public SkillTrend postSkills(@RequestBody SkillTrend skillTrend){
        return skillTrendService.saveSkill(skillTrend);
    }

    @GetMapping("/getskills")
    public List<SkillTrend> getSkills(){
        return skillTrendService.getAllSkills();
    }

    @PostMapping("/updateskill")
    public SkillTrend updateSkills(@RequestBody SkillTrend skillTrend){
        return skillTrendService.updateSkill(skillTrend);
    }

    @DeleteMapping("/deleteskills/{skillId}")
    public SkillTrend deleteSkillTrend(@PathVariable Long skillId){
        return skillTrendService.deleteSkill(skillId);
    }

    @DeleteMapping("/deleteall")
    public Boolean deleteAll(){
        return skillTrendService.deleteEverything();
    }
}

