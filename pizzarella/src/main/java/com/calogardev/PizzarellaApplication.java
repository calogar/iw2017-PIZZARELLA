package com.calogardev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.calogardev.pizzarella.dao.UserDao;
import com.calogardev.pizzarella.enums.UserStatus;
import com.calogardev.pizzarella.model.User;
import com.calogardev.pizzarella.service.UserService;

@SpringBootApplication
public class PizzarellaApplication {

	private static final Logger log = LoggerFactory.getLogger(PizzarellaApplication.class);
	@Autowired
	private UserService userService; 
	
	public static void main(String[] args) {
		SpringApplication.run(PizzarellaApplication.class, args);
	}
	
	// Testing the userDao and loading test data
	@Bean
	public CommandLineRunner loadData(UserDao userDao) {
		return (args) -> {
			userDao.save(new User("José", "Jiménez", "12345678A", "user1", "user1", 
					UserStatus.ACTIVE));
			userDao.save(new User("Pedro", "Villalejos", "12345678B", "user2", "user2", 
					UserStatus.ACTIVE));
			
			log.info("### Get all users ###");
			for(User user : userDao.findAll()) {
				log.info(user.toString());
			}	
		};
	}
}
