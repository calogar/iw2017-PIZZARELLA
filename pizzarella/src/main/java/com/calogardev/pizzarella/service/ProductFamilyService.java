package com.calogardev.pizzarella.service;

import java.util.List;

import com.calogardev.pizzarella.dto.ProductFamilyDto;
import com.calogardev.pizzarella.exception.EmptyAttributeException;

public interface ProductFamilyService {

    public List<ProductFamilyDto> findAll();

    /**
     * 
     * @param productFamilyDto
     *            The dto with the required data
     * @throws EmptyAttributeException
     */
    public void save(ProductFamilyDto dto) throws EmptyAttributeException;
}
