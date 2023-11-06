package com.penguin.esms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class EsmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsmsApplication.class, args);
	}

}
