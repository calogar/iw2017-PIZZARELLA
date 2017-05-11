package com.calogardev.pizzarella.service;

import java.util.List;

import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;

/**
 * Main interface to handle the Order entity. Contains the business logic.
 * 
 * @author calogar
 *
 */
public interface OrderService {

	/**
	 * Saves an Order by its OrderDto
	 */
	public void save(OrderDto order);

	/**
	 * Finds an Order by its id and returns an OrderDto
	 * 
	 * @param id
	 * @return orderDto
	 */
	public OrderDto findOne(Long id);

	/**
	 * Finds al active Orders and return them like Dtos
	 * 
	 * @param id
	 * @return List<OrderDto>
	 */
	public List<OrderDto> findAll();
}
