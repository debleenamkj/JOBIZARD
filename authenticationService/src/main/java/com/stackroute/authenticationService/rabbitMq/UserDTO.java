package com.stackroute.authenticationService.rabbitMq;

import lombok.Data;

@Data
public class UserDTO
{
    private String emailId;
    private String password;
//    private String role;
}

