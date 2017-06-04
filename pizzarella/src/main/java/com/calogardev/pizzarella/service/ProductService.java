package com.calogardev.pizzarella.service;

import java.util.List;

import com.calogardev.pizzarella.exception.CustomValidationException;
import com.calogardev.pizzarella.exception.ProductNotFoundException;
import com.calogardev.pizzarella.model.Product;
import com.calogardev.pizzarella.model.ProductFamily;

/**
 * The main service used to control Products.
 * 
 * @author calogar
 *
 */
public interface ProductService {

    public Product findOne(Long id);

    public List<Product> findAll();

    public List<Product> findAllSellable();

    public List<Product> findAllIngredients();

    public List<Product> findAllExceptOne(Product product);

    public List<Product> findAllIngredientsExcept(Product product);

    public List<Product> findAllSellableFromFamily(ProductFamily pf);

    public Product save(Product product) throws CustomValidationException;

    public void delete(Product product) throws ProductNotFoundException;

}