package com.stackroute.cvgenerationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT , reason = "Cv already exists")
public class CvAlreadyExistsException extends Exception{
}
