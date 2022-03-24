package com.stackroute.resourcesservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FOUND, reason = "Review already exists exception.")
public class ReviewAlreadyExistsException extends Exception{
}
