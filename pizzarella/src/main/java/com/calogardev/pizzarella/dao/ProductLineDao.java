package com.calogardev.pizzarella.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.calogardev.pizzarella.model.ProductLine;

@Repository
public interface ProductLineDao extends CrudRepository<ProductLine, Long> {

}
