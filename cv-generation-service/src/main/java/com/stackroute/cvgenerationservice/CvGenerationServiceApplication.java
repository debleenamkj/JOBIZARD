package com.stackroute.cvgenerationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CvGenerationServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(CvGenerationServiceApplication.class, args);
	}

}
