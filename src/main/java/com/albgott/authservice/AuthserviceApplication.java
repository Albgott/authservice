package com.albgott.authservice;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.TimeZone;

@SpringBootApplication
@RefreshScope
@RestController
public class AuthserviceApplication {

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
	}

	public static void main(String[] args) {
		SpringApplication.run(AuthserviceApplication.class, args);
	}

	@GetMapping("/")
	public String hello(){
		return "bye bye";
	}
}
