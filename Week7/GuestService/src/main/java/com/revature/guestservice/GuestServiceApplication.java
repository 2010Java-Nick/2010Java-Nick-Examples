package com.revature.guestservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class GuestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuestServiceApplication.class, args);
	}

}
