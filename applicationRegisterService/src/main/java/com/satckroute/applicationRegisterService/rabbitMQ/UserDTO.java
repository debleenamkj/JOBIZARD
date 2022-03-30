package com.satckroute.applicationRegisterService.rabbitMQ;
import com.satckroute.applicationRegisterService.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// getter & setter / toString()
@AllArgsConstructor
// parameterised Constructor
@NoArgsConstructor
// Default Constructor
public class UserDTO
{
    private String emailId;
    private String password;
    private String role;
}
