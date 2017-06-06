package com.calogardev.pizzarella;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.calogardev.pizzarella.enums.OrderPlace;
import com.calogardev.pizzarella.enums.OrderType;
import com.calogardev.pizzarella.enums.VatType;
import com.calogardev.pizzarella.exception.CustomValidationException;
import com.calogardev.pizzarella.exception.ProductFamilyNotFoundException;
import com.calogardev.pizzarella.exception.ProductNotFoundException;
import com.calogardev.pizzarella.exception.RoleNotFoundException;
import com.calogardev.pizzarella.model.Client;
import com.calogardev.pizzarella.model.Order;
import com.calogardev.pizzarella.model.Product;
import com.calogardev.pizzarella.model.ProductFamily;
import com.calogardev.pizzarella.model.ProductLine;
import com.calogardev.pizzarella.model.Role;
import com.calogardev.pizzarella.model.User;
import com.calogardev.pizzarella.service.ClientService;
import com.calogardev.pizzarella.service.OrderService;
import com.calogardev.pizzarella.service.ProductFamilyService;
import com.calogardev.pizzarella.service.ProductLineService;
import com.calogardev.pizzarella.service.ProductService;
import com.calogardev.pizzarella.service.RoleService;
import com.calogardev.pizzarella.service.UserService;

@Component
public class DataLoader {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductFamilyService productFamilyService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductLineService plService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ClientService clientService;

    /* Entitites */

    private ProductFamily drinks;
    private ProductFamily pizza;
    private ProductFamily hamburguer;
    private ProductFamily ingredient;

    private Product cocacola;
    private Product water;
    private Product fanta;
    private Product carbonnara;
    private Product mushrooms;
    private Product beef;
    private Product bacon;
    private Product tuna;
    private Product onion;
    private Product ketchup;
    private Product bbqSauce;
    private Product lettuce;

    private Product carbonnaraPizza;
    private Product bbqPizza;

    private Product beefBurguer;
    private Product forestBurguer;
    private Product weirdBurguer;

    public DataLoader() {
    }

    public void loadUsersAndRoles() {

	/* Create roles */

	Role roleAdmin = new Role("ROLE_MANAGER");
	Role roleAtt = new Role("ROLE_ATTENDANT");
	Role roleWaiter = new Role("ROLE_WAITER");

	roleAdmin = saveIfPossible(roleAdmin);
	roleAtt = saveIfPossible(roleAtt);
	roleWaiter = saveIfPossible(roleWaiter);

	/* Create users */

	List<User> users = new ArrayList<User>();
	users.add(new User("Carlos", "López García", "12345678M", "carlos", "123456", Arrays.asList(roleAdmin)));
	users.add(new User("Alejandro", "Tosso Bustelo", "87654321A", "aleco", "123456", Arrays.asList(roleAtt)));
	users.add(new User("Adrián", "Porras González", "12346978E", "adrian", "123456", Arrays.asList(roleWaiter)));
	users.add(new User("Sara", "Rodríguez Vega", "42347569W", "sara", "123456", Arrays.asList(roleAdmin)));

	for (User u : users) {
	    saveIfPossible(u);
	}
    }

    public void loadProductsAndFamilies() {

	/* Create product families */

	drinks = saveIfPossible(new ProductFamily("Drinks", "drinks"));
	pizza = saveIfPossible(new ProductFamily("Pizza", "pizza"));
	hamburguer = saveIfPossible(new ProductFamily("Hamburguer", "hamburguer"));
	ingredient = saveIfPossible(new ProductFamily("Ingredient", "ingredient"));

	/* Create basic products */

	cocacola = saveIfPossible(new Product("Coca Cola", 1.5f, VatType.NORMAL, 50, false, drinks));
	water = saveIfPossible(new Product("Mineral Water", 1f, VatType.NORMAL, 50, false, drinks));
	fanta = saveIfPossible(new Product("Fanta", 0.5f, VatType.NORMAL, 50, false, drinks));
	carbonnara = saveIfPossible(new Product("Carbonnara sauce", 0.5f, VatType.NORMAL, 50, true, ingredient));
	mushrooms = saveIfPossible(new Product("Mushrooms", 0.3f, VatType.NORMAL, 80, true, ingredient));
	beef = saveIfPossible(new Product("Beef meat", 0.3f, VatType.NORMAL, 80, true, ingredient));
	bacon = saveIfPossible(new Product("Bacon", 0.3f, VatType.NORMAL, 80, true, ingredient));
	tuna = saveIfPossible(new Product("Tuna", 0.3f, VatType.NORMAL, 80, true, ingredient));
	onion = saveIfPossible(new Product("Onion", 0.3f, VatType.NORMAL, 80, true, ingredient));
	ketchup = saveIfPossible(new Product("Ketchup", 0.3f, VatType.NORMAL, 80, true, ingredient));
	bbqSauce = saveIfPossible(new Product("BBQ Sauce", 0.3f, VatType.NORMAL, 80, true, ingredient));
	lettuce = saveIfPossible(new Product("Lettuce", 0.3f, VatType.NORMAL, 80, true, ingredient));

	/* Create complex products */

	carbonnaraPizza = new Product("Carbonnara Pizza", 6.5f, VatType.NORMAL, 15, false, pizza);
	bbqPizza = new Product("BBQ Pizza", 6.0f, VatType.NORMAL, 15, false, pizza);
	beefBurguer = new Product("Beef burguer", 6.0f, VatType.NORMAL, 15, false, hamburguer);
	forestBurguer = new Product("Forest burguer", 6.0f, VatType.NORMAL, 15, false, hamburguer);
	weirdBurguer = new Product("Weird burguer", 6.0f, VatType.NORMAL, 15, false, hamburguer);

	carbonnaraPizza.setIngredients(new HashSet<Product>(Arrays.asList(carbonnara, mushrooms)));
	bbqPizza.setIngredients(new HashSet<Product>(Arrays.asList(bbqSauce, beef)));
	beefBurguer.setIngredients(new HashSet<Product>(Arrays.asList(tuna, mushrooms)));
	forestBurguer.setIngredients(new HashSet<Product>(Arrays.asList(mushrooms)));
	weirdBurguer.setIngredients(new HashSet<Product>(Arrays.asList(onion)));

	carbonnaraPizza = saveIfPossible(carbonnaraPizza);
	bbqPizza = saveIfPossible(bbqPizza);
	beefBurguer = saveIfPossible(beefBurguer);
	forestBurguer = saveIfPossible(forestBurguer);
	weirdBurguer = saveIfPossible(weirdBurguer);
    }

    public void loadOrders() {

	/* Create first order */

	Order localOrder = new Order(OrderType.LOCAL);
	localOrder.setPlace(OrderPlace.BAR);
	localOrder.setTableNumber(5);
	localOrder.setNotes("Edulcorant instead of sugar");

	ProductLine pl1 = plService.addPrice(new ProductLine(cocacola, localOrder, 2));
	ProductLine pl2 = plService.addPrice(new ProductLine(carbonnaraPizza, localOrder, 1));

	localOrder.setProductLines(Arrays.asList(pl1, pl2));

	try {
	    orderService.save(localOrder);
	} catch (CustomValidationException e) {
	    e.printStackTrace();
	}

	/* Create second order */

	Order deliverOrder = new Order(OrderType.TO_DELIVER);
	Client client = clientService.save(new Client("Pepe", "López", "956129453", "Calle Nueva, 2º A", 11100));
	deliverOrder.setClient(client);

	ProductLine pl3 = plService.addPrice(new ProductLine(water, localOrder, 4));
	ProductLine pl4 = plService.addPrice(new ProductLine(bbqPizza, localOrder, 1));
	ProductLine pl5 = plService.addPrice(new ProductLine(forestBurguer, localOrder, 3));

	deliverOrder.setProductLines(Arrays.asList(pl3, pl4, pl5));

	try {
	    orderService.save(deliverOrder);
	} catch (CustomValidationException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Avoids validation exceptions for unique constraints
     * 
     * @param role
     * @return
     */
    private Role saveIfPossible(Role role) {
	Role r = new Role();
	try {
	    r = roleService.save(role);
	} catch (CustomValidationException e) {
	    try {
		r = roleService.findByName(role.getName());
	    } catch (RoleNotFoundException e1) {
		e1.printStackTrace();
	    }
	}
	return r;
    }

    private User saveIfPossible(User user) {
	User u = new User();
	try {
	    u = userService.save(user);
	} catch (CustomValidationException e) {
	    u = userService.findByUsername(user.getUsername());
	}
	return u;
    }

    private Product saveIfPossible(Product product) {
	Product p = new Product();
	try {
	    p = productService.save(product);
	} catch (CustomValidationException e) {
	    try {
		p = productService.findByName(product.getName());
	    } catch (ProductNotFoundException e1) {
		e1.printStackTrace();
	    }
	}
	return p;
    }

    private ProductFamily saveIfPossible(ProductFamily pf) {
	ProductFamily pf1 = new ProductFamily();
	try {
	    pf1 = productFamilyService.save(pf);
	} catch (CustomValidationException e) {
	    try {
		pf1 = productFamilyService.findByCode(pf.getCode());
	    } catch (ProductFamilyNotFoundException e1) {
		e1.printStackTrace();
	    }
	}
	return pf1;
    }
}
