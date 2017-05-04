package com.calogardev.pizzarella.dao;

import org.springframework.data.repository.CrudRepository;

import com.calogardev.pizzarella.model.Order;

public interface OrderDao extends CrudRepository<Order, Long> {

}
