package com.rubic.cube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class CubeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CubeApplication.class, args);
	}

}
