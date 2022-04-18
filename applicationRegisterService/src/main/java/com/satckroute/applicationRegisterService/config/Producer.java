package com.satckroute.applicationRegisterService.config;


import com.satckroute.applicationRegisterService.rabbitMQ.Recruiter;
import com.satckroute.applicationRegisterService.rabbitMQ.Seeker;
import com.satckroute.applicationRegisterService.rabbitMQ.UserDTO;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer
{
    private RabbitTemplate rabbitTemplate;
    private DirectExchange exchange;

    @Autowired
    public Producer(RabbitTemplate rabbitTemplate, DirectExchange exchange)
    {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }
//---------------------------------------------------------------------------------------------------------------------

    public void sendMessage(UserDTO userDTO)
    {
        //covert the (exchange,routing key,object)
        rabbitTemplate.convertAndSend(exchange.getName(),"user_routing",userDTO);
    }

//---------------------------------------------------------------------------------------------------------------------

    public void sendJobSeekerMessage(Seeker Seeker)
    {
        //covert the (exchange,routing key,object)
        rabbitTemplate.convertAndSend(exchange.getName(),"jobSeeker_routing",Seeker);
    }
//---------------------------------------------------------------------------------------------------------------------

    public void sendSDMessage(Recruiter Recruiter)
    {
        //covert the (exchange,routing key,object)
        rabbitTemplate.convertAndSend(exchange.getName(),"recruiter_routing",Recruiter);
    }

//---------------------------------------------------------------------------------------------------------------------

}
