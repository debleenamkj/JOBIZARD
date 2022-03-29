package com.stackroute.resourcesservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FOUND, reason = "suggestion already exists.")
public class SuggestionAlreadyExistsException extends Exception{
}
