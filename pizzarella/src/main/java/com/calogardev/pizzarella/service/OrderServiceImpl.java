package com.calogardev.pizzarella.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calogardev.pizzarella.dao.OrderDao;
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
	public OrderDto findOne(Long id) {
		final Order order = orderDao.findOne(id);
		// TODO check if the user exists
		log.info("Finding Order: " + order);
		return utilsService.transform(order, OrderDto.class);
	}

	@Override
	public List<OrderDto> findAll() {
		log.info("Finding all active Orders");
		final List<Order> orders = orderDao.findAll();
		return utilsService.transform(orders, OrderDto.class);
	}
}
