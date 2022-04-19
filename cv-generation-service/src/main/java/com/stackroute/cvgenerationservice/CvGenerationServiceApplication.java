package com.stackroute.cvgenerationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class CvGenerationServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(CvGenerationServiceApplication.class, args);
	}

}
