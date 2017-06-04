package com.calogardev.pizzarella.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calogardev.pizzarella.dao.ProductDao;
import com.calogardev.pizzarella.exception.CustomValidationException;
import com.calogardev.pizzarella.exception.ProductNotFoundException;
import com.calogardev.pizzarella.model.Product;
import com.calogardev.pizzarella.model.ProductFamily;

import lombok.extern.slf4j.Slf4j;

/**
 * ProductService implementation.
 * 
 * @author calogar
 *
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    @Transactional(readOnly = true)
    public Product findOne(Long id) {
	log.info("Finding product wit id: " + id);
	return productDao.findOne(id);
    }

    @Override
    public List<Product> findAllIngredients() {
	log.info("Finding all ingredients");
	return productDao.findAllIngredients();
    }

    @Override
    public List<Product> findAllSellableFromFamily(ProductFamily pf) {
	log.info("Finding all products from a family that can be sold");
	return productDao.findAllSellableFromFamily(pf);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
	log.info("Finding all products");
	return productDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAllSellable() {
	log.info("Finding all products that can be sold");
	return productDao.findAllSellable();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAllIngredientsExcept(Product product) {
	if (product == null || product.getId() == null) {
	    log.info("Finding all products that are ingredients");
	    return productDao.findAllIngredients();
	} else {
	    log.info("Finding all products that are ingredients except the one with id " + product.getId());
	    return productDao.findAllIngredientsExcept(product.getId());
	}
    }

    // TODO needs revision
    @Override
    @Transactional(readOnly = true)
    public List<Product> findAllExceptOne(Product product) {
	log.info("Finding all products except the one with id " + product.getId());
	return productDao.findAllExceptOne(product.getId());
    }

    @Override
    public Product save(Product product) throws CustomValidationException {

	if (product.getId() == null) {
	    // Perform create
	    if (productDao.existsByName(product.getName())) {
		throw new CustomValidationException("A product already has this name");
	    }
	}

	// We allow ingredients composed of other ingredients
	// We allow products that aren't composed but can be sold (water)
	if (product.getFamily() == null) {
	    throw new CustomValidationException("A product can't exist without a product family");
	}

	// Check that product doesn't have itself as ingredient
	if (product.getIngredients() != null) {
	    for (Product ingredient : product.getIngredients()) {
		if (product.equals(ingredient)) {
		    throw new CustomValidationException("A product can't contain itself as ingredient");
		}
	    }
	}

	product = productDao.save(product);
	log.info("Saved product: " + product);
	return product;
    }

    @Override
    public void delete(Product product) throws ProductNotFoundException {

	if (product.getId() == null) {
	    // If it hasn't got id, it's not persisted
	    throw new ProductNotFoundException();
	}
	String name = product.getName();
	productDao.delete(product);
	log.info("Deleted product with name: " + name);
    }
}
