package com.stackroute.recommendationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Job not found")
public class JobNotFoundException extends Throwable {
}
