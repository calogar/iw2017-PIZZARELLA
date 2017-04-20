package com.calogardev.pizzarella;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.calogardev.pizzarella.dto.UserDto;
import com.calogardev.pizzarella.service.UserService;

@SpringBootApplication
public class PizzarellaApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(PizzarellaApplication.class);

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(PizzarellaApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

		userService.save(new UserDto("Carlos Isidoro", "López García", "12345678M", "carlos", "123456"));
		userService.save(new UserDto("Alejandro", "Tosso Bustelo", "87654321A", "aleco", "789012"));
		userService.save(new UserDto("Adrián", "Porras González", "12346978E", "adrian", "166576"));
		userService.save(new UserDto("Sara", "Rodríguez Vega", "42347569W", "sara", "111221"));
	}
}
