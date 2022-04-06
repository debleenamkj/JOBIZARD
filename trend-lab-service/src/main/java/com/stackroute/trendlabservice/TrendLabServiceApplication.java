package com.stackroute.trendlabservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TrendLabServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrendLabServiceApplication.class, args);
	}

}
