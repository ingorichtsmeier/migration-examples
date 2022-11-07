package com.camunda.consulting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;

@SpringBootApplication
@EnableProcessApplication
public class TwitterQAComplicatedApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterQAComplicatedApplication.class, args);
	}

}
