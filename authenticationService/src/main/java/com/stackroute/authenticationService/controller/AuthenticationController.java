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
@RequestMapping("/api/v2")
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
        System.out.println("in controller");
        return new ResponseEntity<>(authenticationService.saveUserDetails(userLogIn), HttpStatus.CREATED);
    }

    @PostMapping("/register")
    public ResponseEntity<?> UserData(@RequestParam UserLogIn userLogIn)
    {
        System.out.println("in controller");
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

    @PostMapping("/login2")
    public ResponseEntity<?> login2(@RequestBody UserLogIn userLogIn) throws UserNotFoundException
    {
        System.out.println("controller");
        System.out.println(userLogIn.getEmailId());
        System.out.println(userLogIn.getPassword());
        Map<String,String> map = null;
        try
        {
//            UserLogIn userLogIn1= authenticationService.findByEmailIdAndPassword(emailId,password);
//            if(userLogIn1.getEmailId().equals(emailId))
//            {
//                System.out.println("emaiid ");
//                map = securityTokenGenerator.generateToken(emailId);
//            }
            return new ResponseEntity<>(map,HttpStatus.OK);
        }
//        catch (UserNotFoundException ex)
//        {
//            throw new UserNotFoundException();
//        }
        catch (Exception ex)
        {
            return new ResponseEntity<>("Please try after sometime",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/login3",method = RequestMethod.GET)
    public void login3()
    {
        System.out.println("Hello");
    }

//    ----------------------------------------------------------------------------------------
//  Malathi modified the code
    @PostMapping("/loginuser")
    public String loginUser(@RequestBody UserLogIn user) throws UserNotFoundException {
        Map<String,String> map=null;
        try{
            UserLogIn user1 = authenticationService.findByEmailIdAndPassword(user.getEmailId(),user.getPassword());
            System.out.println("controller");
            System.out.println(user1);
            if(user1.getEmailId().equalsIgnoreCase(user.getEmailId())) {
                System.out.println("emaill");
                map= securityTokenGenerator.generateToken(user1);
            }
            //return new ResponseEntity(map,HttpStatus.OK);
            return map.get("token");
        }
        catch (Exception e) {
            //return new ResponseEntity("try after some time",HttpStatus.INTERNAL_SERVER_ERROR);
            System.out.println(e.toString());
            //return new ResponseEntity("try after some time",HttpStatus.INTERNAL_SERVER_ERROR);
            return "try after some time";
        }
    }

}
