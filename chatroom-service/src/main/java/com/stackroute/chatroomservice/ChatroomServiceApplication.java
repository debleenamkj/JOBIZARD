package com.stackroute.chatroomservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ChatroomServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatroomServiceApplication.class, args);
	}

}
