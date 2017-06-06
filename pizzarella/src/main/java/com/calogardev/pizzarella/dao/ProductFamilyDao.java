package com.calogardev.pizzarella.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.calogardev.pizzarella.model.ProductFamily;

@Repository
public interface ProductFamilyDao extends CrudRepository<ProductFamily, Long> {

    @Override
    public List<ProductFamily> findAll();

    public Boolean existsByCode(String code);

    public void deleteByCode(String code);

    public ProductFamily findByCode(String code);
}
