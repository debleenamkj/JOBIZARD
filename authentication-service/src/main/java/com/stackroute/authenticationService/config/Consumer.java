package com.stackroute.authenticationService.config;


import com.stackroute.authenticationService.domain.UserLogIn;
import com.stackroute.authenticationService.exception.UserAlreadyExistException;
import com.stackroute.authenticationService.exception.UserNotFoundException;
import com.stackroute.authenticationService.rabbitMq.UserDTO;
import com.stackroute.authenticationService.service.AuthenticationServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer
{

    @Autowired
    private AuthenticationServiceImpl authenticationServiceImpl;

//---------------------------------------------------------------------------------------------------------------------

    @RabbitListener(queues="user_queue")
    public void getUserDtoFromRabbitMq(UserDTO userDTO) throws UserNotFoundException, UserAlreadyExistException {
        UserLogIn userLogIn =new UserLogIn();
        userLogIn.setEmailId(userDTO.getEmailId());
        userLogIn.setPassword(userDTO.getPassword());
        //role
        userLogIn.setRole(userDTO.getRole());
        authenticationServiceImpl.saveUserDetails(userLogIn);
    }
}
