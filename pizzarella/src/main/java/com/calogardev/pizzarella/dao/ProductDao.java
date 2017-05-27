package com.calogardev.pizzarella.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.calogardev.pizzarella.model.Product;

public interface ProductDao extends CrudRepository<Product, Long> {

    @Override
    @Query("SELECT p FROM Product p WHERE p.status = 'ACTIVE'")
    public List<Product> findAll();

    @Query("SELECT p FROM Product p WHERE p.id != :id")
    public List<Product> findAllExceptOne(@Param("id") Long id);
}
