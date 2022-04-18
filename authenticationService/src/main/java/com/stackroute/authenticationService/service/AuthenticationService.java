package com.stackroute.authenticationService.service;

import com.stackroute.authenticationService.domain.UserLogIn;
import com.stackroute.authenticationService.exception.UserAlreadyExistException;
import com.stackroute.authenticationService.exception.UserNotFoundException;

public interface AuthenticationService
{
    UserLogIn saveUserDetails(UserLogIn userLogIn) throws UserAlreadyExistException;
    //role
//    ,String role
    UserLogIn findByEmailIdAndPassword(String emailId,String password) throws UserNotFoundException;

    UserLogIn findByEmailId(String emailId) throws UserNotFoundException;

//    findByEmailId
}
