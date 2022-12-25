package com.social.media.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	final static Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
	
	@GetMapping("/hello-world")
	public String helloWorld() {
		logger.info("hello world method");
		return "Hello world";
		
		//150
		
		//200
	}
	
	@GetMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		logger.info("helloWorldBean method");
		return new HelloWorldBean("Hello World Bean");
		//100
	}
	
	@GetMapping("/hello-world-bean/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		logger.info("hello world path variable :"+name);
		return new HelloWorldBean(String.format("Hello World, %s",name));
	}
	
}
