package com.stackroute.trendlabservice.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v2")
//@CrossOrigin
public class TechNewsController {
    @GetMapping("/getTechNews")
    public ResponseEntity<String> getTechNews(){
        String uri = "https://tech-info.p.rapidapi.com/news";
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Host", "tech-info.p.rapidapi.com");
        headers.add("X-RapidAPI-Key", "e6f916a48bmsh5d85cf972abfec9p155bcajsn9988020bee77");
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET,entity,String.class);
        return response;

    }
}
