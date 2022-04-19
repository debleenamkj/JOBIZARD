package com.stackroute.authenticationService.service;



import com.stackroute.authenticationService.domain.UserLogIn;

import java.util.Map;

public interface SecurityTokenGenerator
{
    Map<String,String> generateToken(UserLogIn userLogIn);

// changes
//    Map<String,String> generateToken(UserLogIn userLogIn);
//    Map<String,String> generateToken( String emailId);
}

