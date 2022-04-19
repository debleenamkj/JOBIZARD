package com.stackroute.Assessmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class assessmentserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(assessmentserviceApplication.class, args);
	}

}
