package com.stackroute.trendlabservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ExternalApiCallerImpl implements ExternalApiCaller{

    @Value("${com.stackroute.trendlabservice.X-RapidAPI-Host}")
    private String hostName;

    @Value("${com.stackroute.trendlabservice.X-RapidAPI-Key}")
    private String hostKey;

    @Value("${com.stackroute.trendlabservice.externalUrl}")
    private String externalUrl;

    @Override
    public ResponseEntity<String> getStringResponseEntity(String jobTitle) {
        log.debug("Inside ExternalApiCallerImpl - getStringResponseEntity");
        String url = externalUrl + jobTitle;
        HttpHeaders headers = getHttpHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
        return response;
    }

    private HttpHeaders getHttpHeaders() {
        log.debug("Inside ExternalApiCallerImpl - getHttpHeaders");
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Host", hostName);
        headers.add("X-RapidAPI-Key", hostKey);
        return headers;
    }
}
