package com.stackroute.recruitmentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
@ControllerAdvice
public class JobExceptionHandler {
    @ExceptionHandler(value = {JobNotFoundException.class})
    public ResponseEntity<Object> handleJobNotFoundException(JobNotFoundException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        JobException jobException =new JobException(
                e.getMessage(),
                e,
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(jobException,badRequest );
    }
}
