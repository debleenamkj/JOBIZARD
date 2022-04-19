package com.stackroute.cvgenerationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
=======
import org.springframework.context.annotation.ComponentScan;
>>>>>>> 3a608307c6805e5125934f040ee9d68f7a46791c

@ComponentScan
@SpringBootApplication
@EnableEurekaClient
public class CvGenerationServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(CvGenerationServiceApplication.class, args);
	}

}
