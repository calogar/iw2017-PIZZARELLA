package com.calogardev.pizzarella.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calogardev.pizzarella.dao.OrderDao;
import com.calogardev.pizzarella.dto.OrderDto;
import com.calogardev.pizzarella.dto.ProductLineDto;
import com.calogardev.pizzarella.enums.OrderPlace;
import com.calogardev.pizzarella.enums.OrderStatus;
import com.calogardev.pizzarella.enums.Status;
import com.calogardev.pizzarella.exception.CustomValidationException;
import com.calogardev.pizzarella.exception.OrderNotFoundException;
import com.calogardev.pizzarella.model.Order;
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
    private ProductLineService productLineService;

    @Autowired
    private UtilsService utilsService;

    @Override
    public void save(OrderDto dto) throws CustomValidationException {

	if (dto.getId() == null) {
	    // Perform create
	} else {
	    // Perform update
	    if (orderDao.findOne(dto.getId()).getStatus() != Status.ACTIVE) {
		throw new CustomValidationException("That Order is deleted and cannot be updated");
	    }
	}

	Order order = utilsService.transform(dto, Order.class);
	order.setOrderedAt(new Date());
	order.setStatus(Status.ACTIVE);
	order.setOrderStatus(OrderStatus.SENT_TO_KITCHEN);
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
}
