package com.calogardev.pizzarella.service;

import java.util.List;

import com.calogardev.pizzarella.enums.OrderStatus;
import com.calogardev.pizzarella.exception.CustomValidationException;
import com.calogardev.pizzarella.exception.OrderNotFoundException;
import com.calogardev.pizzarella.model.Order;

/**
 * Main interface to handle the Order entity. Contains the business logic.
 * 
 * @author calogar
 *
 */
public interface OrderService {

    public Order findOne(Long id) throws OrderNotFoundException;

    public List<Order> findAll();

    public List<Order> findAllWithStatus(OrderStatus status);

    public void save(Order order) throws CustomValidationException;

    public void delete(Order order);

    public Order updateStatus(Long id, OrderStatus status);

    public Float getTotalIncomes();

    public Float calculateTotalIncomes();
}
