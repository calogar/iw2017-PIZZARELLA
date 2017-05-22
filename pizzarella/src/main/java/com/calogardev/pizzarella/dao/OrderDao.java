package com.calogardev.pizzarella.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.calogardev.pizzarella.model.FoodOrder;

public interface OrderDao extends CrudRepository<FoodOrder, Long> {

    @Override
    @Query("SELECT o FROM FoodOrder o WHERE o.id = :id AND o.status = 'ACTIVE'")
    public FoodOrder findOne(@Param("id") Long id);

    @Override
    @Query("SELECT o FROM FoodOrder o WHERE o.status = 'ACTIVE'")
    public List<FoodOrder> findAll();

}
