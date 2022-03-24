package com.stackroute.resourcesservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FOUND, reason = "Company already exists")
public class CompanyAlreadyExistsException extends Exception {
}
