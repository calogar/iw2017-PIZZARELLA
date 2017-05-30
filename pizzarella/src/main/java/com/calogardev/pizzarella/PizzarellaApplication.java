package com.calogardev.pizzarella;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.calogardev.pizzarella.dao.OrderDao;
import com.calogardev.pizzarella.dao.ProductDao;
import com.calogardev.pizzarella.dao.ProductFamilyDao;
import com.calogardev.pizzarella.dao.ProductService;
import com.calogardev.pizzarella.dto.ProductDto;
import com.calogardev.pizzarella.dto.ProductFamilyDto;
import com.calogardev.pizzarella.dto.RoleDto;
import com.calogardev.pizzarella.dto.UserDto;
import com.calogardev.pizzarella.service.ProductFamilyService;
import com.calogardev.pizzarella.service.RoleService;
import com.calogardev.pizzarella.service.UserService;

@SpringBootApplication
public class PizzarellaApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(PizzarellaApplication.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductFamilyService productFamilyService;

    @Autowired
    private ProductService productService;

    @Autowired
    ProductFamilyDao pfDao;

    @Autowired
    ProductDao pDao;

    @Autowired
    OrderDao oDao;

    public static void main(String[] args) {
	SpringApplication.run(PizzarellaApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... arg0) throws Exception {

	/* Create roles */
	RoleDto roleAdmin = new RoleDto("ROLE_MANAGER");
	RoleDto roleAtt = new RoleDto("ROLE_ATTENDANT");
	RoleDto roleWaiter = new RoleDto("ROLE_WAITER");

	roleAdmin = roleService.save(roleAdmin);
	roleAtt = roleService.save(roleAtt);
	roleWaiter = roleService.save(roleWaiter);

	/* Create users */
	List<UserDto> users = new ArrayList<UserDto>();
	users.add(new UserDto("Carlos", "López García", "12345678M", "carlos", "123456", Arrays.asList(roleAdmin)));
	users.add(new UserDto("Alejandro", "Tosso Bustelo", "87654321A", "aleco", "123456", Arrays.asList(roleAtt)));
	users.add(new UserDto("Adrián", "Porras González", "12346978E", "adrian", "123456", Arrays.asList(roleWaiter)));
	users.add(new UserDto("Sara", "Rodríguez Vega", "42347569W", "sara", "123456", Arrays.asList(roleAdmin)));

	for (UserDto u : users) {
	    userService.save(u);
	}

	/* Create product families */
	ProductFamilyDto drinks = productFamilyService.save(new ProductFamilyDto("Drinks", "drinks"));
	ProductFamilyDto pizza = productFamilyService.save(new ProductFamilyDto("Pizza", "pizza"));
	ProductFamilyDto hamburguer = productFamilyService.save(new ProductFamilyDto("Hamburguer", "Hamburguer"));

	ProductDto cocacola = new ProductDto("Coca Cola", 1.5f, 1.2f, 50, false, drinks);
	ProductDto water = new ProductDto("Mineral Water", 1f, 1.2f, 50, false, drinks);
	ProductDto carbonnaraSauce = new ProductDto("Carbonnara sauce", 0.5f, 1.2f, 50, true, pizza);
	ProductDto mushrooms = new ProductDto("Mushrooms", 0.3f, 1.1f, 80, true, pizza);
	ProductDto carbonnaraPizza = new ProductDto("Carbonnara Pizza", 6.5f, 1.2f, 15, false, pizza);

	water = productService.save(water);
	carbonnaraSauce = productService.save(carbonnaraSauce);
	mushrooms = productService.save(mushrooms);

	Set<ProductDto> ingredients = new HashSet<ProductDto>();
	ingredients.add(carbonnaraSauce);
	ingredients.add(mushrooms);

	carbonnaraPizza.setIngredients(ingredients);
	productService.save(carbonnaraPizza);
	cocacola = productService.save(cocacola);

	// Product product = pDao.findOne(cocacola.getId());
	// Order order = new Order(OrderType.TO_TAKE_AWAY, 1f, new Date(),
	// OrderStatus.SENT_TO_KITCHEN, Status.ACTIVE,
	// null, "111111111");
	//
	// ProductLine pl = new ProductLine(product, order, 1f, 2,
	// Status.ACTIVE);
	//
	// order.setProductLines(Arrays.asList(pl));
	// pl.setOrder(order);
	//
	// order = oDao.save(order);
	// System.out.println(order);

    }
}
