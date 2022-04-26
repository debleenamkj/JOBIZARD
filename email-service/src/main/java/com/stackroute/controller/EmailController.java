package com.stackroute.controller;

import com.stackroute.model.EmailRequest;
import com.stackroute.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/v1")
@RestController
@Slf4j
//@CrossOrigin("*")
public class EmailController {
    private MailService mailService;

    @Autowired
    public EmailController(MailService mailService) {
        log.info("Autowiring Mailservice complete");
        this.mailService = mailService;
    }

    @PostMapping("/sendemail")
    public ResponseEntity<?> send(@RequestBody EmailRequest emailRequest) throws Exception {
        log.debug("Inside Send method");
        boolean result = mailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getMessage(), emailRequest.getCompanyName());
        if (result) {
            return ResponseEntity.ok("Email is sent successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("email sending failed");
        }
    }
}




