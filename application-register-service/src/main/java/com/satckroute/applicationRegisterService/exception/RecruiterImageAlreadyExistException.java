package com.satckroute.applicationRegisterService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "Job Seeker image already exist.")
public class RecruiterImageAlreadyExistException extends Exception{
}
