package com.calogardev.pizzarella.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calogardev.pizzarella.dao.OrderDao;
import com.calogardev.pizzarella.dto.OrderDto;
import com.calogardev.pizzarella.enums.OrderPlace;
import com.calogardev.pizzarella.exception.OrderNotFoundException;
import com.calogardev.pizzarella.model.Order;

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
    private UtilsService utilsService;

    @Override
    public void save(OrderDto orderDto) {
	// We send the Dto with OrderStatus to OPEN, which is set by Vaadin
	// TODO Add validation
	Order order = utilsService.transform(orderDto, Order.class);
	orderDao.save(order);
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
}
