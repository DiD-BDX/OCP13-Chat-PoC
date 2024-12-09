package com.openclassrooms.ycyw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = {"com.openclassrooms.ycyw"})
public class YcywApplication {

	public static void main(String[] args) {
		SpringApplication.run(YcywApplication.class, args);
	}

}
