package com.stackroute.authenticationService.service;


import com.stackroute.authenticationService.domain.UserLogIn;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtSecurityTokenGeneratorImpl implements SecurityTokenGenerator
{
    @Override
    public Map<String, String> generateToken(UserLogIn userLogIn)
    {
        String jwstToken = Jwts.builder().setSubject(userLogIn.getEmailId()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256,"secretekeyid").compact();
        Map<String,String> mapData = new HashMap<>();
        mapData.put("token",jwstToken);
        mapData.put("message","Authentication Successful.");
        return mapData;
    }



// changes
//    @Override
//    public Map<String, String> generateToken(UserLogIn userLogIn)
//    {
//        String jwstToken = Jwts.builder().setSubject(userLogIn.getEmailId()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256,"secretekeyid").compact();
//        Map<String,String> mapData = new HashMap<>();
//        mapData.put("token",jwstToken);
//        mapData.put("message","Authentication Successful.");
//        return mapData;
//    }
//
//    @Override
//    public Map<String, String> generateToken( String emailId )
//    {
//        String jwstToken = Jwts.builder().setSubject(emailId).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256,"secretekeyid").compact();
//        Map<String,String> mapData = new HashMap<>();
//        mapData.put("token",jwstToken);
//        mapData.put("message","Authentication Successful.");
//        System.out.println(mapData);
//        return mapData;
//    }
}