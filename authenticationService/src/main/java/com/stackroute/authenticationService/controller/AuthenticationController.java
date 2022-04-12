package com.stackroute.authenticationService.controller;

import com.stackroute.authenticationService.domain.UserLogIn;
import com.stackroute.authenticationService.exception.UserNotFoundException;
import com.stackroute.authenticationService.service.AuthenticationService;
import com.stackroute.authenticationService.service.SecurityTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("api/v2")
public class AuthenticationController
{
    private AuthenticationService authenticationService;
    SecurityTokenGenerator securityTokenGenerator;

//---------------------------------------------------------------------------------------------------------------------

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, SecurityTokenGenerator securityTokenGenerator)
    {
        this.authenticationService = authenticationService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

//---------------------------------------------------------------------------------------------------------------------

    @PostMapping("/userRegister")
    public ResponseEntity<?> addingUserData(@RequestBody UserLogIn userLogIn)
    {
        return new ResponseEntity<>(authenticationService.saveUserDetails(userLogIn), HttpStatus.CREATED);
    }

//---------------------------------------------------------------------------------------------------------------------

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLogIn userLogIn) throws UserNotFoundException
    {
        Map<String,String> map = null;
        try
        {
            UserLogIn userLogIn1= authenticationService.findByEmailIdAndPassword(userLogIn.getEmailId(),userLogIn.getPassword());
            if(userLogIn1.getEmailId().equals(userLogIn.getEmailId()))
            {
                map = securityTokenGenerator.generateToken(userLogIn);
            }
            return new ResponseEntity<>(map,HttpStatus.OK);
        }
        catch (UserNotFoundException ex)
        {
            throw new UserNotFoundException();
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>("Please try after sometime",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }}