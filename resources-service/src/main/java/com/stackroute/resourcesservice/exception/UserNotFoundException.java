package com.stackroute.resourcesservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User doesn't exists")
public class UserNotFoundException extends Exception{
}
