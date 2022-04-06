package com.stackroute.trendlabservice.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v2")
@CrossOrigin
public class TechNewsController {
    @GetMapping("/getTheVergeNews")
    public ResponseEntity<String> getTheVergeNews(){
        System.out.println("in");
        String uri = "https://tech-news3.p.rapidapi.com/verge";
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Host", "tech-news3.p.rapidapi.com");
        headers.add("X-RapidAPI-Key", "e6f916a48bmsh5d85cf972abfec9p155bcajsn9988020bee77");
        System.out.println("in");
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET,entity,String.class);
        System.out.println("in");
        return response;

    }
}
