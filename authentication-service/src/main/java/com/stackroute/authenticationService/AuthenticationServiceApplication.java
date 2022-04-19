package com.stackroute.authenticationService;

import com.stackroute.authenticationService.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class AuthenticationServiceApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}

	@Bean
	FilterRegistrationBean jwtFilter()
	{
		FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new JwtFilter());
		filterRegistrationBean.addUrlPatterns("/api/v2/dis");
		return filterRegistrationBean;
	}
}
