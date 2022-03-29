package com.stackroute.authenticationService.service;

import com.stackroute.authenticationService.domain.UserLogIn;
import com.stackroute.authenticationService.exception.UserNotFoundException;

public interface AuthenticationService
{
    UserLogIn saveUserDetails(UserLogIn userLogIn);
    UserLogIn findByEmailIdAndPassword(String emailId,String password) throws UserNotFoundException;
}
