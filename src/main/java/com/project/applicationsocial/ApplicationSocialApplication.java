package com.project.applicationsocial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ApplicationSocialApplication{


	public static void main(String[] args) {
		SpringApplication.run(ApplicationSocialApplication.class, args);
	}




}
