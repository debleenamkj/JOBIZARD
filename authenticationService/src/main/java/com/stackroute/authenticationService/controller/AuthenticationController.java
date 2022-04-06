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

@CrossOrigin
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

    @GetMapping("/login")
    public String login(@RequestBody UserLogIn userLogIn) throws UserNotFoundException {
        Map<String, String> map = null;
        try {
            UserLogIn userLogIn1 = authenticationService.findByEmailIdAndPassword(userLogIn.getEmailId(), userLogIn.getPassword());
            if (userLogIn1.getEmailId().equals(userLogIn.getEmailId())) {
                map = securityTokenGenerator.generateToken(userLogIn);
            }
            System.out.println(map);
            return map.get("token");

//            return new ResponseEntity<>(map,HttpStatus.OK);
        } catch (UserNotFoundException ex) {
            throw new UserNotFoundException();
        } catch (Exception ex) {
            return "try after sometimes";
//            return new ResponseEntity<>("Please try after sometime",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//---------------------------------------------------------------------------------------------------------------------

    @GetMapping("/login2/{emailId}/{password}")
    public ResponseEntity<?> login2(@PathVariable String emailId,@PathVariable String password) throws UserNotFoundException
    {
        System.out.println("controller");
        System.out.println(emailId);
        System.out.println(password);
        Map<String,String> map = null;
        try
        {
            UserLogIn userLogIn1= authenticationService.findByEmailIdAndPassword(emailId,password);
            if(userLogIn1.getEmailId().equals(emailId))
            {
                System.out.println("emaiid ");
                map = securityTokenGenerator.generateToken(emailId);
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
    }


    @PostMapping("/login3")
    public void login3(@RequestParam String emailId,@RequestParam String password) throws UserNotFoundException
    {
        System.out.println("Hello");
    }

}
