package com.stackroute.cvgenerationservice;

import com.stackroute.cvgenerationservice.repository.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class CvGenerationServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(CvGenerationServiceApplication.class, args);
	}

}
