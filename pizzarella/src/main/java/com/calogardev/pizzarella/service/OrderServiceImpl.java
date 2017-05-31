package com.calogardev.pizzarella.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calogardev.pizzarella.dao.OrderDao;
import com.calogardev.pizzarella.dao.ProductDao;
import com.calogardev.pizzarella.dao.ProductService;
import com.calogardev.pizzarella.dto.OrderDto;
import com.calogardev.pizzarella.dto.ProductLineDto;
import com.calogardev.pizzarella.enums.OrderPlace;
import com.calogardev.pizzarella.enums.OrderStatus;
import com.calogardev.pizzarella.enums.OrderType;
import com.calogardev.pizzarella.enums.Status;
import com.calogardev.pizzarella.exception.CustomValidationException;
import com.calogardev.pizzarella.exception.OrderNotFoundException;
import com.calogardev.pizzarella.model.Order;
import com.calogardev.pizzarella.model.Product;
import com.calogardev.pizzarella.model.ProductLine;

/**
 * OrderService implementation.
 * 
 * @author calogar
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductLineService productLineService;

	@Autowired
	private UtilsService utilsService;

	@Override
	@Transactional
	public void save(OrderDto dto) throws CustomValidationException {

		if (dto.getId() == null) {
			// Perform create
		} else {
			// Perform update
			if (orderDao.findOne(dto.getId()).getStatus() != Status.ACTIVE) {
				throw new CustomValidationException("That Order is deleted and cannot be updated");
			}
		}

		if (dto.getProductLines() == null) {
			throw new CustomValidationException("An Order must have at least one Product");
		}
		if (dto.getType() == OrderType.LOCAL) {
			if (dto.getPlace() == null) {
				throw new CustomValidationException("Place for a local Order can't be empty");
			} else if (dto.getTableNumber() == null) {
				throw new CustomValidationException("table number for a local Order can't be empty");
			}
		} else {
			dto.setPlace(null);
			dto.setTableNumber(null);
			if (dto.getTelephone() == null) {
				throw new CustomValidationException("Telephone for a non local Order can't be empty");
			}
		}

		// Calculate total price
		Float totalPrice = 0f;
		for (ProductLineDto plDto : dto.getProductLines()) {
			totalPrice = totalPrice + plDto.getPrice();
		}

		// Due to possible persistence problems with dozer, we will create the
		// associations between order, product lines and product from scratch
		// This will be corrected in future implementations
		// Order order = utilsService.transform(dto, Order.class);

		// Creating new Order without mapping
		Order order = new Order();
		if (dto.getId() != null) {
			order = orderDao.findOne(dto.getId());
		}

		if (dto.getOrderedAt() == null) {
			order.setOrderedAt(new Date());
		} else {
			order.setOrderedAt(dto.getOrderedAt());
		}

		order.setTotalPrice(totalPrice);

		if (dto.getOrderStatus() == null) {
			order.setOrderStatus(OrderStatus.OPEN);
		} else {
			order.setOrderStatus(dto.getOrderStatus());
		}

		order.setStatus(Status.ACTIVE);
		order.setTelephone(dto.getTelephone());

		List<ProductLine> pls = new ArrayList<>();
		for (ProductLineDto plDto : dto.getProductLines()) {
			// Getting it back from the DB (so it has persistence context)
			Product product = productDao.findOne(plDto.getProduct().getId());

			// Creating product line without mapping
			ProductLine pl = new ProductLine(product, order, plDto.getPrice(), plDto.getAmount(), Status.ACTIVE);
			pls.add(pl);
		}
		order.setProductLines(pls);
		order = orderDao.save(order);
		log.info("Saved Order: " + order);
	}

	@Override
	@Transactional(readOnly = true)
	public OrderDto findOne(Long id) throws OrderNotFoundException {
		final Order order = orderDao.findOne(id);
		if (order == null) {
			throw new OrderNotFoundException();
		}
		log.info("Finding Order: " + order);
		return utilsService.transform(order, OrderDto.class);
	}

	@Override
	public List<OrderDto> findAll() {
		log.info("Finding all active Orders");
		final List<Order> orders = orderDao.findAll();
		List<OrderDto> orderDtos = utilsService.transform(orders, OrderDto.class);
		return orderDtos;
	}

	@Override
	public List<OrderDto> findAllWithStatus(OrderStatus status) {
		final List<Order> orders = orderDao.findAllWithStatus(status);
		List<OrderDto> orderDtos = utilsService.transform(orders, OrderDto.class);
		return orderDtos;
	}

	//
	// public String getFormattedLocation(OrderDto o) {
	//
	// StringBuilder location = new StringBuilder();
	// location.append("From restnaurant '");
	// // location.append(o.getRestaurant().getName());
	// if (o.getTableNumber() != null) {
	// // The order was taken locally
	// location.append(" in " + formatPlace(o.getOrderPlace()) + " at table " +
	// o.getTableNumber());
	// } else {
	// // The order was taken remotely (it has an associated client)
	// // Rework client, make it exclusively external and add telephone
	// location.append(" by client ");
	// }
	// return location;
	// }

	@Override
	public Float getTotalIncomes() {
		Float total = 0f;
		for (OrderDto orderDto : findAll()) {
			total = total + orderDto.getTotalPrice();
		}
		return total;
	}

	private String formatPlace(OrderPlace place) {
		if (place == OrderPlace.HALL) {
			return "the hall";
		} else if (place == OrderPlace.BAR) {
			return "the bar";
		} else {
			return "the terrace";
		}
	}

	@Override
	public void delete(OrderDto orderDto) {
		if (orderDto.getId() == null) {
			return;
		}
		Order order = orderDao.findOne(orderDto.getId());

		// Delete all product lines
		for (ProductLine pl : order.getProductLines()) {
			productLineService.delete(utilsService.transform(pl, ProductLineDto.class));

		}
		order.setStatus(Status.DELETED);
		orderDao.save(order);
	}

	@Override
	public OrderDto updateStatus(Long id, OrderStatus status) {
		Order order = orderDao.findOne(id);
		order.setOrderStatus(status);
		return utilsService.transform(orderDao.save(order), OrderDto.class);
	}

	@Override
	public Float calculateTotalIncomes() {
		Float total = 0f;
		for (OrderDto o : findAll()) {
			total = total + o.getTotalPrice();
		}
		return total;
	}
}
