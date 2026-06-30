package com.example.feedback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;



@SpringBootApplication
public class FeedbackApplication {
	@PostConstruct
public void init() {
    System.out.println("Application started correctly");
}


	public static void main(String[] args) {
		SpringApplication.run(FeedbackApplication.class, args);

	}



}
