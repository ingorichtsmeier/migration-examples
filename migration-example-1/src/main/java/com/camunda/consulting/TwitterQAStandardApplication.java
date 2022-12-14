package com.camunda.consulting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;

@SpringBootApplication
@EnableProcessApplication("migration-example-1")
public class TwitterQAStandardApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterQAStandardApplication.class, args);
	}

}
