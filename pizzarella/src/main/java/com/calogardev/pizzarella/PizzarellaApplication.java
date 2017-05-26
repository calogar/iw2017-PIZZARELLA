package com.calogardev.pizzarella;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.calogardev.pizzarella.dto.RoleDto;
import com.calogardev.pizzarella.dto.UserDto;
import com.calogardev.pizzarella.service.RoleService;
import com.calogardev.pizzarella.service.UserService;

@SpringBootApplication
public class PizzarellaApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(PizzarellaApplication.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    public static void main(String[] args) {
	SpringApplication.run(PizzarellaApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... arg0) throws Exception {

	RoleDto roleAdmin = new RoleDto("ROLE_MANAGER");
	roleService.save(roleAdmin);
	RoleDto persistedRoleAdmin = roleService.findByName(roleAdmin.getName());
	UserDto admin = new UserDto("Carlos", "López García", "12345678M", "carlos", "123456",
		Arrays.asList(persistedRoleAdmin));
	userService.save(admin);

	// createUser(new UserDto("Carlos", "López García", "12345678M",
	// "carlos", "123456", Arrays.asList(roleManager)));
	// createUser(new UserDto("Alejandro", "Tosso Bustelo", "87654321A",
	// "aleco", "123456", Arrays.asList(roleEmpty)));
	// createUser(new UserDto("Adrián", "Porras González", "12346978E",
	// "adrian", "123456", Arrays.asList(roleEmpty)));
	// createUser(new UserDto("Sara", "Rodríguez Vega", "42347569W", "sara",
	// "123456", Arrays.asList(roleEmpty)));

    }
}
