package com.stackroute.trendlabservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Skill Trend Not Found")
public class SkillTrendNotFoundException extends Exception{
}
