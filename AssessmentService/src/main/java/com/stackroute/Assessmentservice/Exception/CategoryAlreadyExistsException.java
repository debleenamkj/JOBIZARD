package com.stackroute.Assessmentservice.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "Category Details Already Exists.")
public class CategoryAlreadyExistsException extends Exception{
}
