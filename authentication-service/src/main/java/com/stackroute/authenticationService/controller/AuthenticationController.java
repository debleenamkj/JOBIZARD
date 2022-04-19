package com.stackroute.authenticationService.controller;

import com.stackroute.authenticationService.domain.UserLogIn;
import com.stackroute.authenticationService.exception.UserAlreadyExistException;
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
    public ResponseEntity<?> addingUserData(@RequestBody UserLogIn userLogIn) throws UserAlreadyExistException
    {
        try
        {
            return new ResponseEntity<>(authenticationService.saveUserDetails(userLogIn), HttpStatus.CREATED);
        }
        catch (UserAlreadyExistException userAlreadyExistException)
        {
            throw new UserAlreadyExistException();
        }
        catch (Exception exception)
        {
            return new ResponseEntity<>("try after sometime.",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

//---------------------------------------------------------------------------------------------------------------------

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLogIn userLogIn) throws UserNotFoundException
    {

        Map<String,String> map = null;
        try
        {
            //role / , userLogIn.getRole()
            UserLogIn userLogIn1= authenticationService.findByEmailIdAndPassword(userLogIn.getEmailId(),userLogIn.getPassword());
            if(userLogIn1.getEmailId().equals(userLogIn.getEmailId()))
            {
                System.out.println(userLogIn.getEmailId());
                map = securityTokenGenerator.generateToken(userLogIn);
            }
            return new ResponseEntity<>(map,HttpStatus.OK);
        }
        catch (UserNotFoundException userNotFoundException)
        {
            throw new UserNotFoundException();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return new ResponseEntity<>("Please try after sometime",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @GetMapping("/find/{emailId}")
    public ResponseEntity<?> findWithEmail(@PathVariable String emailId) throws UserNotFoundException
    {
        Map<String,String> map = null;
        try
        {
            //role / , userLogIn.getRole()
            UserLogIn userLogIn1= authenticationService.findByEmailId(emailId);
//            if(userLogIn1.getEmailId().equals(userLogIn.getEmailId()))
//            {
//                map = securityTokenGenerator.generateToken(userLogIn);
//            }
//            return new ResponseEntity<>(map,HttpStatus.OK);
            return new ResponseEntity<>(userLogIn1,HttpStatus.OK);
        }

        catch (UserNotFoundException ex)
        {
            throw new UserNotFoundException();
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>("Please try after sometime",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//---------------------------------------------------------------------------------------------------------------------

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody UserLogIn userLogIn) throws UserNotFoundException
//    {
//        Map<String,String> map = null;
//        try
//        {
//            //role
////            ,userLogIn.getRole()
//            UserLogIn userLogIn1= authenticationService.findByEmailIdAndPassword(userLogIn.getEmailId(),userLogIn.getPassword(),userLogIn.getRole());
//            if(userLogIn1.getEmailId().equals(userLogIn.getEmailId()))
//            {
//                map = securityTokenGenerator.generateToken(userLogIn);
//            }
//            return new ResponseEntity<>(map,HttpStatus.OK);
//        }
//        catch (UserNotFoundException ex)
//        {
//            throw new UserNotFoundException();
//        }
//        catch (Exception ex)
//        {
//            return new ResponseEntity<>("Please try after sometime",HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}