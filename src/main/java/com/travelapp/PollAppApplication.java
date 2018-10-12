package com.travelapp;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PollAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PollAppApplication.class, args);
	}
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		
	}
}
