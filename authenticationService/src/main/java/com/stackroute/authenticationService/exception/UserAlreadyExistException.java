package com.stackroute.authenticationService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "User is already Present")
public class UserAlreadyExistException extends Exception
{
}
