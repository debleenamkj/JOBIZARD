package com.stackroute.recommendationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "User already exists")
public class UserAlreadyExistsException extends Exception{
}
