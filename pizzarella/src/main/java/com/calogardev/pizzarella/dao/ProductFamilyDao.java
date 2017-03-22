package com.calogardev.pizzarella.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.calogardev.pizzarella.model.ProductFamily;

public interface ProductFamilyDao extends CrudRepository<ProductFamily, Long> {

    @Override
    @Query("SELECT f FROM ProductFamily f WHERE f.status = 'ACTIVE'")
    public List<ProductFamily> findAll();

}
