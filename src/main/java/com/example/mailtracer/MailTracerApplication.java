package com.example.mailtracer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Mail API", version = "v1"))
public class MailTracerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailTracerApplication.class, args);
	}

}
