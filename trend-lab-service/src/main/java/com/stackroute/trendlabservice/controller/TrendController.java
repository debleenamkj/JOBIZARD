package com.stackroute.trendlabservice.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v6")
public class TrendController {

    @GetMapping("/check")
    public String hello(){
        String hi = "hi";
        return "hello";
    }

    @GetMapping("/salary")
    public ResponseEntity<String> callExternalApiForSalaryTrend(@RequestParam("job_title")String job_title){
        String url="https://infosalary.p.rapidapi.com/?job_title="+job_title;
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Host", "infosalary.p.rapidapi.com");
        headers.add("X-RapidAPI-Key", "f7bab1b14emshd729e803f0810d0p16072fjsn9a2c50459692");
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
        return response;
    }
}
