package com.stackroute.authenticationService.rabbitMq;

import com.stackroute.authenticationService.domain.Role;
import lombok.Data;


@Data
public class UserDTO
{
    private String emailId;
    private String password;
    private String role;
}

