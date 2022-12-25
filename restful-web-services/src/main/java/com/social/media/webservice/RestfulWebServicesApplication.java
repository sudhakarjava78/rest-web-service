package com.social.media.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		System.out.println("Before Application Start");
		SpringApplication.run(RestfulWebServicesApplication.class, args);
		System.out.println("After Application Started");
	}

}
