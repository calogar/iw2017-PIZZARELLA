package com.calogardev.pizzarella.service;

import java.util.List;

import com.calogardev.pizzarella.dto.ProductDto;
import com.calogardev.pizzarella.dto.ProductFamilyDto;
import com.calogardev.pizzarella.exception.CustomValidationException;
import com.calogardev.pizzarella.exception.IngredientWithProductsException;
import com.calogardev.pizzarella.exception.ProductNotFoundException;
import com.calogardev.pizzarella.exception.ProductWithoutFamilyException;
import com.calogardev.pizzarella.model.Product;

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
	public List<ProductDto> findAllSellable();

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
	 * @return
	 * @throws ProductWithoutFamilyException
	 * @throws IngredientWithProductsException
	 * @throws CustomValidationException
	 */
	public ProductDto save(ProductDto productDto) throws CustomValidationException;

	/**
	 * Deletes a product
	 * 
	 * @param productDto
	 *            The product to delete
	 * @throws ProductNotFoundException
	 */
	public void delete(ProductDto productDto) throws ProductNotFoundException;

	public ProductDto findOne(Long id);

	List<ProductDto> findAll();

	List<ProductDto> findAllIngredientsExcept(ProductDto dto);

	List<ProductDto> findAllIngredients();

	List<ProductDto> findAllSellableFromFamily(ProductFamilyDto dto);

	Product findOneNoConversion(Long id);

	Product saveReturnEntity(ProductDto dto) throws CustomValidationException;
}