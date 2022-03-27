package com.stackroute.trendlabservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Skill Trend Already Exists")
public class SkillTrendAlreadyExistsException extends Exception{
}
