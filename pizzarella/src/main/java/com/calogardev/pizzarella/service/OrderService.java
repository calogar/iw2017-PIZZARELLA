package com.calogardev.pizzarella.service;

import java.util.List;

import com.calogardev.pizzarella.dto.OrderDto;
import com.calogardev.pizzarella.exception.OrderNotFoundException;

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
     * @throws OrderNotFoundException
     */
    public OrderDto findOne(Long id) throws OrderNotFoundException;

    /**
     * Finds all active Orders and return them like Dtos
     * 
     * @param id
     * @return List<OrderDto>
     */
    public List<OrderDto> findAll();

    /**
     * Sums all the prices of all the orders
     * 
     * @return the sum of incomes
     */
    public Float getTotalIncomes();
}
