package ru.croc.coder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ru.croc.coder.repository.UserEventsHandler;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public UserEventsHandler getUserEventsHandler() {
		return new UserEventsHandler();
	}
}
