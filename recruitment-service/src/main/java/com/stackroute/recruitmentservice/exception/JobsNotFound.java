package com.stackroute.recruitmentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No Jobs to show")
public class JobsNotFound extends Exception{
}
