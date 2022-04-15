package com.stackroute.authenticationService.service;

import com.stackroute.authenticationService.domain.UserLogIn;
import com.stackroute.authenticationService.exception.UserNotFoundException;
import com.stackroute.authenticationService.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService
{
    private AuthenticationRepository authenticationRepository;

//---------------------------------------------------------------------------------------------------------------------

    @Autowired
    public AuthenticationServiceImpl(AuthenticationRepository authenticationRepository)
    {
        this.authenticationRepository = authenticationRepository;
    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public UserLogIn saveUserDetails(UserLogIn userLogIn)
    {
        return authenticationRepository.save(userLogIn);
    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public UserLogIn findByEmailIdAndPassword(String emailId, String password ) throws UserNotFoundException
    {
        UserLogIn userLogIn= authenticationRepository.findByEmailIdAndPassword(emailId,password);
        if(userLogIn == null)
        {
            throw new UserNotFoundException();
        }
//        System.out.println(userLogIn);
        return userLogIn;
    }

//---------------------------------------------------------------------------------------------------------------------

    @Override
    public UserLogIn findByEmailId(String emailId) throws UserNotFoundException
    {
        UserLogIn userLogIn= authenticationRepository.findByEmailId(emailId);
        if(userLogIn == null)
        {
            throw new UserNotFoundException();
        }
//        System.out.println(userLogIn);
        return userLogIn;
    }
//---------------------------------------------------------------------------------------------------------------------

//    @Override
//    public UserLogIn findByEmailIdAndPassword(String emailId, String password , Strimg role) throws UserNotFoundException
//    {
//        //role , String role / ,role
//        UserLogIn userLogIn= authenticationRepository.findByEmailIdAndPasswordAndRole(emailId,password,role);
//        if(userLogIn == null)
//        {
//            throw new UserNotFoundException();
//        }
//        System.out.println(userLogIn);
//        return userLogIn;
//    }

//---------------------------------------------------------------------------------------------------------------------


}