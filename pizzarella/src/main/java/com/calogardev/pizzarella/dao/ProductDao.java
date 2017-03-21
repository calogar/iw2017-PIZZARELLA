package com.calogardev.pizzarella.dao;

import org.springframework.data.repository.CrudRepository;

import com.calogardev.pizzarella.model.Product;

public interface ProductDao extends CrudRepository<Product, Long> {

}
