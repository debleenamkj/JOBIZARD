package com.stackroute.authenticationService.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration
{
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConvertor()
    {
        return new Jackson2JsonMessageConverter();
    }

//    @Bean
//    public Queue registerQueue(){
//        return new Queue("user_queue",true);
//    }
}
