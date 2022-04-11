package com.stackroute.Assessmentservice.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "SubCategory Details Don't Exists.")
public class SubCategoryNotExistsException extends  Exception {
}
