package com.lawfirm.lawfirm_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.lawfirm.model")
@ComponentScan("com.lawfirm")
@EnableJpaRepositories("com.lawfirm.repository")
public class LawfirmBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LawfirmBackendApplication.class, args);
	}

}
