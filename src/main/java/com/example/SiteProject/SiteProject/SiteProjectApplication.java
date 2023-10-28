package com.example.SiteProject.SiteProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "repositories")
public class SiteProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiteProjectApplication.class, args);
	}

}
