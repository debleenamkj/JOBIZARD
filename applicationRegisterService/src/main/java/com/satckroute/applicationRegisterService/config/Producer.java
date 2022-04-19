package com.satckroute.applicationRegisterService.config;


import com.satckroute.applicationRegisterService.domain.JobSeeker;
import com.satckroute.applicationRegisterService.rabbitMQ.*;
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
        System.out.println("Job seeker");
        //covert the (exchange,routing key,object)
        rabbitTemplate.convertAndSend(exchange.getName(),"jobSeeker_routing",Seeker);
    }
//---------------------------------------------------------------------------------------------------------------------

    public void sendRecruiter(JobDetails jobDetails)
    {
        //covert the (exchange,routing key,object)
        rabbitTemplate.convertAndSend(exchange.getName(),"recruiter_routing",jobDetails);
    }

//---------------------------------------------------------------------------------------------------------------------

    public void posting(User user)
    {
        //covert the (exchange,routing key,object)
        rabbitTemplate.convertAndSend(exchange.getName(),"post_routing",user);
    }

//---------------------------------------------------------------------------------------------------------------------

    public void cvGeneration(JobSeeker jobSeekerDTO)
    {
        //covert the (exchange,routing key,object)
        rabbitTemplate.convertAndSend(exchange.getName(),"cvGeneration_routing",jobSeekerDTO);
    }

//---------------------------------------------------------------------------------------------------------------------


}
