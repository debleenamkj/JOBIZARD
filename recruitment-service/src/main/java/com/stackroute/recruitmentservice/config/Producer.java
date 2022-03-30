package com.stackroute.recruitmentservice.config;

import com.stackroute.recruitmentservice.rabbitmq.domain.JobPostingDTO;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private RabbitTemplate rabbitTemplate;
    private DirectExchange exchange;

    @Autowired
    public Producer(RabbitTemplate rabbitTemplate, DirectExchange exchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }

    public void sendMessageToRabbitTemplate(JobPostingDTO jobPostingDTO)
    {
        rabbitTemplate.convertAndSend(exchange.getName(),"jobposting_routing",jobPostingDTO);
    }
}
