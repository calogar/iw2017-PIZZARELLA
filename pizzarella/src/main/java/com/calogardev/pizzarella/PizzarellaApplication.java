package com.calogardev.pizzarella;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzarellaApplication implements CommandLineRunner {

    @Autowired
    private DataLoader loader;

    public static void main(String[] args) {
	SpringApplication.run(PizzarellaApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... arg0) throws Exception {

	loader.loadUsersAndRoles();
	loader.loadProductsAndFamilies();

    }
}
