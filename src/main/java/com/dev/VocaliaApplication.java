package com.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.dev.dao")
//@EntityScan(basePackages = "com.dev.domain")
//@EnableJpaRepositories(basePackages = "com.dev.dao")
public class VocaliaApplication {

	public static void main(String[] args) {
		SpringApplication.run(VocaliaApplication.class, args);
	}

}
