package com.stackroute.authenticationService.rabbitMq;

import com.stackroute.authenticationService.domain.Role;
import lombok.*;

@Data
public class UserDTO
{
    private String emailId;
    private String password;
//   private Role role;
//    private String role;
}

