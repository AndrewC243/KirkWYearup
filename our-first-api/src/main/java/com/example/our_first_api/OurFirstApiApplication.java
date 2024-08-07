package com.example.our_first_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OurFirstApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OurFirstApiApplication.class, args);
	}

}
