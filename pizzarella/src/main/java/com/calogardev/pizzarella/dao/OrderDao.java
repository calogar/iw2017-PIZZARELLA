package com.calogardev.pizzarella.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.calogardev.pizzarella.enums.OrderStatus;
import com.calogardev.pizzarella.model.Order;

public interface OrderDao extends CrudRepository<Order, Long> {

    @Override
    public Order findOne(Long id);

    @Override
    public List<Order> findAll();

    public List<Order> findByOrderStatus(OrderStatus status);
}
