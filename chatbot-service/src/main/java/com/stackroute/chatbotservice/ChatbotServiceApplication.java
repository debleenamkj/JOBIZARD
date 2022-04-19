package com.stackroute.chatbotservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ChatbotServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatbotServiceApplication.class, args);
	}

}
