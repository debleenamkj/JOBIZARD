package com.stackroute.trendlabservice.service;

import org.springframework.http.ResponseEntity;

public interface ExternalApiCaller {

    ResponseEntity<String> getStringResponseEntity(String job_title);
}
