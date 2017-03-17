package com.calogardev.pizzarella;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzarellaApplication {

	private static final Logger log = LoggerFactory.getLogger(PizzarellaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PizzarellaApplication.class, args);
	}
}
