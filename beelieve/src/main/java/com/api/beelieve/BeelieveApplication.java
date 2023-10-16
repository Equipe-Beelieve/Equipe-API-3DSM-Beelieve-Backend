package com.api.beelieve;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.api.beelieve.repositorio")
public class BeelieveApplication {
	

	
	public static void main(String[] args) {
		SpringApplication.run(BeelieveApplication.class, args);
	}

}
