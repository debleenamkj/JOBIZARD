package com.stackroute.resourcesservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Company does not exist")
public class CompanyNotFoundException extends Exception{
}
