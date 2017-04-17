package com.calogardev.pizzarella.service;

import java.util.List;

import com.calogardev.pizzarella.dto.ProductFamilyDto;
import com.calogardev.pizzarella.exception.CustomValidationException;

/**
 * The main service used to control Product Families
 * 
 * @author calogar
 *
 */
public interface ProductFamilyService extends GenericService {

    /**
     * Finds all available product families
     * 
     * @return a list of productFamilyDtos
     */
    public List<ProductFamilyDto> findAll();

    /**
     * Saves a product family
     * 
     * @param productFamilyDto
     *            The dto with the required data
     * @throws CustomValidationException
     */
    public void save(ProductFamilyDto dto) throws CustomValidationException;

    public void deleteByCode(String code);
}
