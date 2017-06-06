package com.calogardev.pizzarella.service;

import java.util.List;

import com.calogardev.pizzarella.exception.CustomValidationException;
import com.calogardev.pizzarella.exception.ProductFamilyNotFoundException;
import com.calogardev.pizzarella.model.ProductFamily;

public interface ProductFamilyService {

    ProductFamily findOne(Long id) throws ProductFamilyNotFoundException;

    List<ProductFamily> findAll();

    ProductFamily save(ProductFamily dto) throws CustomValidationException;

    void delete(ProductFamily pf) throws ProductFamilyNotFoundException;

    void deleteByCode(String code);

    ProductFamily findByCode(String code) throws ProductFamilyNotFoundException;

}
