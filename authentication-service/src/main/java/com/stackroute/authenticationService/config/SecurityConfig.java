//package com.stackroute.authenticationService.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter
//{
//    @Override
//    public void configure(HttpSecurity http)throws Exception
//    {
//        http.antMatcher("/**").authorizeRequests()
//                .antMatchers("/api/v2/userRegister","/api/v2/login").permitAll()
//                .anyRequest().authenticated()
//                .and().oauth2Login();
//    }
//}
