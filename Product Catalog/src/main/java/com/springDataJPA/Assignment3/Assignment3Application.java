package com.springDataJPA.Assignment3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.springDataJPA.Assignment3")
public class Assignment3Application {

	public static void main(String[] args) {
		SpringApplication.run(Assignment3Application.class, args);
	}

}
