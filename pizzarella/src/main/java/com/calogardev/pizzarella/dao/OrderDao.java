package com.calogardev.pizzarella.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.calogardev.pizzarella.enums.OrderStatus;
import com.calogardev.pizzarella.model.Order;

public interface OrderDao extends CrudRepository<Order, Long> {

    @Override
    @Query("SELECT o FROM Order o WHERE o.id = :id AND o.status = 'ACTIVE'")
    public Order findOne(@Param("id") Long id);

    @Override
    @Query("SELECT o FROM Order o WHERE o.status = 'ACTIVE'")
    public List<Order> findAll();

    @Query("SELECT o FROM Order o WHERE o.status = 'ACTIVE' AND o.orderStatus = :orderStatus")
    public List<Order> findAllWithStatus(@Param("orderStatus") OrderStatus status);

}
