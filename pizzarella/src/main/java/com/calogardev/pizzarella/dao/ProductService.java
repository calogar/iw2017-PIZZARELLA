package com.calogardev.pizzarella.dao;

import java.util.List;

import com.calogardev.pizzarella.dto.ProductDto;
import com.calogardev.pizzarella.exception.IngredientWithProductsException;
import com.calogardev.pizzarella.exception.ProductWithoutFamilyException;
import com.calogardev.pizzarella.service.GenericService;

/**
 * The main service used to control Products.
 * 
 * @author calogar
 *
 */
public interface ProductService extends GenericService {

    /**
     * Finds all available products
     * 
     * @return a list of productDtos
     */
    public List<ProductDto> findAll();

    /**
     * Finds all available products except the one with the specific id.
     * 
     * @param id
     *            The id of the excluded product
     * @return a list of productDtos
     */
    public List<ProductDto> findAllExceptOne(Long id);

    /**
     * Saves a product
     * 
     * @param productDto
     *            The dto with the required data
     * @throws ProductWithoutFamilyException
     * @throws IngredientWithProductsException
     */
    public void save(ProductDto productDto) throws ProductWithoutFamilyException, IngredientWithProductsException;

    /**
     * Deletes a product
     * 
     * @param productDto
     *            The product to delete
     */
    public void delete(ProductDto productDto);
}