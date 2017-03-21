package com.calogardev.pizzarella.service;

import java.util.List;

import com.calogardev.pizzarella.dto.ProductFamilyDto;

public interface ProductFamilyService {

    public List<ProductFamilyDto> findAll();

    /**
     * 
     * @param productFamilyDto
     *            The dto with the required data
     */
    public void save(ProductFamilyDto dto);
}
