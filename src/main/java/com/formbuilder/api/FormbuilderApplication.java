package com.formbuilder.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FormbuilderApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormbuilderApplication.class, args);
	}

}
