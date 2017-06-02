package com.calogardev.pizzarella;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
import com.calogardev.pizzarella.dto.ProductDto;
import com.calogardev.pizzarella.dto.ProductFamilyDto;
import com.calogardev.pizzarella.dto.RoleDto;
import com.calogardev.pizzarella.dto.UserDto;
import com.calogardev.pizzarella.enums.OrderPlace;
import com.calogardev.pizzarella.enums.OrderStatus;
import com.calogardev.pizzarella.enums.OrderType;
import com.calogardev.pizzarella.enums.Status;
import com.calogardev.pizzarella.model.Order;
import com.calogardev.pizzarella.model.Product;
import com.calogardev.pizzarella.model.ProductLine;
import com.calogardev.pizzarella.service.ProductFamilyService;
import com.calogardev.pizzarella.service.ProductService;
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
		ProductFamilyDto hamburguer = productFamilyService.save(new ProductFamilyDto("Hamburguer", "hamburguer"));
		ProductFamilyDto ingredient = productFamilyService.save(new ProductFamilyDto("Ingredient", "ingredient"));

		ProductDto cocacola = new ProductDto("Coca Cola", 1.5f, 1.2f, 50, false, drinks);
		ProductDto water = new ProductDto("Mineral Water", 1f, 1.2f, 50, false, drinks);
		ProductDto fanta = new ProductDto("Fanta", 0.5f, 1.2f, 50, false, drinks);
		ProductDto carbonnaraSauce = new ProductDto("Carbonnara sauce", 0.5f, 1.2f, 50, true, ingredient);
		ProductDto mushrooms = new ProductDto("Mushrooms", 0.3f, 1.1f, 80, true, ingredient);
		ProductDto beef = new ProductDto("Beef meat", 0.3f, 1.1f, 80, true, ingredient);
		ProductDto bacon = new ProductDto("Bacon", 0.3f, 1.1f, 80, true, ingredient);
		ProductDto tuna = new ProductDto("Tuna", 0.3f, 1.1f, 80, true, ingredient);
		ProductDto onion = new ProductDto("Onion", 0.3f, 1.1f, 80, true, ingredient);
		ProductDto ketchup = new ProductDto("Ketchup", 0.3f, 1.1f, 80, true, ingredient);
		ProductDto bbqSauce = new ProductDto("BBQ Sauce", 0.3f, 1.1f, 80, true, ingredient);
		ProductDto lettuce = new ProductDto("Lettuce", 0.3f, 1.1f, 80, true, ingredient);

		ProductDto carbonnaraPizza = new ProductDto("Carbonnara Pizza", 6.5f, 1.2f, 15, false, pizza);
		ProductDto bbqPizza = new ProductDto("BBQ Pizza", 6.0f, 1.1f, 15, false, pizza);

		ProductDto beefBurguer = new ProductDto("Beef burguer", 6.0f, 1.1f, 15, false, hamburguer);
		ProductDto forestBurguer = new ProductDto("Forest burguer", 6.0f, 1.1f, 15, false, hamburguer);
		ProductDto weirdBurguer = new ProductDto("Weird burguer", 6.0f, 1.1f, 15, false, hamburguer);

		cocacola = productService.save(cocacola);
		water = productService.save(water);
		fanta = productService.save(fanta);

		carbonnaraSauce = productService.save(carbonnaraSauce);
		mushrooms = productService.save(mushrooms);
		beef = productService.save(beef);
		bacon = productService.save(bacon);
		tuna = productService.save(tuna);
		onion = productService.save(onion);
		ketchup = productService.save(ketchup);
		bbqSauce = productService.save(bbqSauce);
		lettuce = productService.save(lettuce);

		carbonnaraPizza.setIngredients(Arrays.asList(carbonnaraSauce, mushrooms));
		bbqPizza.setIngredients(Arrays.asList(bbqSauce, beef));
		beefBurguer.setIngredients(Arrays.asList(tuna, mushrooms));
		// forestBurguer.setIngredients(Arrays.asList(mushrooms));
		// weirdBurguer.setIngredients(Arrays.asList(onion));

		Product p1 = productService.saveReturnEntity(carbonnaraPizza);
		Product p2 = productService.saveReturnEntity(bbqPizza);
		Product p3 = productService.saveReturnEntity(beefBurguer);
		// Product p4 = productService.saveReturnEntity(forestBurguer);
		// Product p5 = productService.saveReturnEntity(weirdBurguer);

		Product product = pDao.findOne(cocacola.getId());
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(OrderType.TO_TAKE_AWAY, null, 1f, new Date(), OrderStatus.OPEN, Status.ACTIVE, null,
				"111111111", null));
		orders.add(new Order(OrderType.LOCAL, 5, 2f, new Date(), OrderStatus.SENT_TO_KITCHEN, Status.ACTIVE, null, null,
				OrderPlace.BAR));
		orders.add(new Order(OrderType.TO_DELIVER, null, 3f, new Date(), OrderStatus.CLOSED, Status.ACTIVE, null,
				"956118288", OrderPlace.TERRACE));

		// Create first order
		ProductLine pl1 = new ProductLine(p1, orders.get(0), 1f, 2, Status.ACTIVE);
		ProductLine pl2 = new ProductLine(p3, orders.get(0), 14f, 1, Status.ACTIVE);
		orders.get(0).setProductLines(Arrays.asList(pl1, pl2));

		// Create second
		ProductLine pl3 = new ProductLine(p2, orders.get(1), 13f, 6, Status.ACTIVE);
		// ProductLine pl4 = new ProductLine(p4, orders.get(1), 17f, 4,
		// Status.ACTIVE);
		orders.get(1).setProductLines(Arrays.asList(pl3));

		// Save all orders
		for (Order order : orders) {
			order = oDao.save(order);
			System.out.println("Order created: " + order.getId());
		}
	}
}
