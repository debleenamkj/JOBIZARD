package com.satckroute.applicationRegisterService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT , reason = "Recruiter not exist.")
public class RecruiterNotFoundException extends Exception
{
}
