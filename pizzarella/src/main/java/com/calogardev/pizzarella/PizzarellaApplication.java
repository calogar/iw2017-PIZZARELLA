package com.calogardev.pizzarella;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.calogardev.pizzarella.dto.UserDto;
import com.calogardev.pizzarella.exception.UserNotFoundException;
import com.calogardev.pizzarella.service.PrivilegeService;
import com.calogardev.pizzarella.service.RoleService;
import com.calogardev.pizzarella.service.UserService;
import com.calogardev.pizzarella.service.UtilsService;

@SpringBootApplication
public class PizzarellaApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(PizzarellaApplication.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PrivilegeService privilegeService;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UtilsService utilsService;

    public static void main(String[] args) {
	SpringApplication.run(PizzarellaApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... arg0) throws Exception {

	/***** Was transactional before *****/

	/*
	 * PrivilegeDto canManageOrders = new PrivilegeDto("CAN_MANAGE_ORDERS");
	 * PrivilegeDto canManageUsers = new PrivilegeDto("CAN_MANAGE_USERS");
	 * PrivilegeDto canManageNone = new PrivilegeDto("ALLOW_NONE");
	 * 
	 * privilegeService.save(canManageOrders);
	 * privilegeService.save(canManageUsers);
	 * privilegeService.save(canManageNone);
	 * 
	 * roleManager.setPrivileges(Arrays.asList(canManageOrders,
	 * canManageUsers));
	 * roleEmpty.setPrivileges(Arrays.asList(canManageNone));
	 * canManageOrders.setRoles(Arrays.asList(roleManager));
	 * canManageUsers.setRoles(Arrays.asList(roleManager));
	 * canManageNone.setRoles(Arrays.asList(roleEmpty));
	 * 
	 * 
	 */
	// RoleDto roleEmpty = new RoleDto("ROLE_EMPTY");
	// RoleDto roleManager = new RoleDto("ROLE_MANAGER");
	//
	// roleService.save(roleEmpty);
	// roleService.save(roleManager);

	/*
	 * canManageOrders.setRoles(Arrays.asList(roleManager));
	 * canManageUsers.setRoles(Arrays.asList(roleManager));
	 * canManageNone.setRoles(Arrays.asList(roleEmpty));
	 */
	// Save updated privileges again
	/*
	 * privilegeService.save(canManageOrders);
	 * privilegeService.save(canManageUsers);
	 * privilegeService.save(canManageNone);
	 */

	// createUser(new UserDto("Carlos", "López García", "12345678M",
	// "carlos", "123456", Arrays.asList(roleManager)));
	// createUser(new UserDto("Alejandro", "Tosso Bustelo", "87654321A",
	// "aleco", "123456", Arrays.asList(roleEmpty)));
	// createUser(new UserDto("Adrián", "Porras González", "12346978E",
	// "adrian", "123456", Arrays.asList(roleEmpty)));
	// createUser(new UserDto("Sara", "Rodríguez Vega", "42347569W", "sara",
	// "123456", Arrays.asList(roleEmpty)));

    }

    private UserDto createUser(UserDto userDto) throws Exception {
	try {
	    UserDto dto = userService.findByUsername(userDto.getNickname());
	} catch (UserNotFoundException e) {
	    userService.save(userDto);
	}
	return userDto;
    }
}
