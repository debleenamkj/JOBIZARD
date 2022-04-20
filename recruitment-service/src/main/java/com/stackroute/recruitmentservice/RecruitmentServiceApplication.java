package com.stackroute.recruitmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RecruitmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruitmentServiceApplication.class, args);
	}

}
