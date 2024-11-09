package com.sollares;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SollaresApplication {

	public static void main(String[] args) {
		SpringApplication.run(SollaresApplication.class, args);
	}

}
