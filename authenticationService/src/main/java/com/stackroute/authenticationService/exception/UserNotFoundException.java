package com.stackroute.authenticationService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Data is not Present")
public class UserNotFoundException extends Exception
{
}