package com.calogardev.pizzarella.dao;

import java.util.List;

import com.calogardev.pizzarella.dto.ProductDto;
import com.calogardev.pizzarella.exception.IngredientWithProductsException;
import com.calogardev.pizzarella.exception.ProductWithoutFamilyException;
import com.calogardev.pizzarella.service.ServiceInterface;

public interface ProductService extends ServiceInterface {

    public List<ProductDto> findAll();

    /**
     * Saves a product
     * 
     * @param productDto
     *            The dto with the required data
     * @throws ProductWithoutFamilyException
     * @throws IngredientWithProductsException
     */
    public void save(ProductDto product) throws ProductWithoutFamilyException, IngredientWithProductsException;

    /**
     * Finds all products except the one with the specific id.
     * 
     * @param id
     *            The id of the excluded product
     * @return a list of productDtos
     */
    public List<ProductDto> findAllExceptOne(Long id);
}
