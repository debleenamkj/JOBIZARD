package com.stackroute.service;

public interface MailService {
   boolean sendEmail(String to, String subject, String message,String companyName) throws Exception;
}
