package com.calogardev.pizzarella.dao;

import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calogardev.pizzarella.dto.Dto;
import com.calogardev.pizzarella.dto.ProductDto;
import com.calogardev.pizzarella.dto.ProductFamilyDto;
import com.calogardev.pizzarella.enums.Status;
import com.calogardev.pizzarella.exception.CustomValidationException;
import com.calogardev.pizzarella.exception.IngredientWithProductsException;
import com.calogardev.pizzarella.exception.ProductNotFoundException;
import com.calogardev.pizzarella.exception.ProductWithoutFamilyException;
import com.calogardev.pizzarella.model.Product;
import com.calogardev.pizzarella.model.ProductFamily;
import com.calogardev.pizzarella.service.UtilsService;

/**
 * ProductService implementation.
 * 
 * @author calogar
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UtilsService utilsService;

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> findAll() {
	return utilsService.transform(productDao.findAll(), ProductDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> findAllSellable() {
	return utilsService.transform(productDao.findAllSellable(), ProductDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> findAllIngredientsExcept(ProductDto dto) {
	if (dto == null || dto.getId() == null) {
	    return utilsService.transform(productDao.findAllIngredients(), ProductDto.class);
	} else {
	    return utilsService.transform(productDao.findAllIngredientsExcept(dto.getId()), ProductDto.class);
	}
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> findAllExceptOne(Long id) {
	return utilsService.transform(productDao.findAllExceptOne(id), ProductDto.class);
    }

    @Override
    public ProductDto save(ProductDto dto) throws CustomValidationException {

	if (dto.getId() == null) {
	    // Perform create
	    if (productDao.findByName(dto.getName()) != null) {
		throw new CustomValidationException("A Product can't have a repeated name");
	    }
	} else {
	    // Perform update
	    if (productDao.findOne(dto.getId()).getStatus() != Status.ACTIVE) {
		throw new CustomValidationException("That Product is deleted and cannot be updated");
	    }
	}

	// We allow ingredients composed of other ingredients
	// We allow products that aren't composed but can be sold (water)
	if (dto.getFamily() == null) {
	    throw new CustomValidationException("A Product can't exist without a Product Family");
	}

	// Check that product doesn't have itself as ingredient
	if (dto.getIngredients() == null) {
	    dto.setIngredients(new HashSet());
	}

	Product product = utilsService.transform(dto, Product.class);
	product.setStatus(Status.ACTIVE);
	Product persisted = productDao.save(product);

	// // Now we create the reverse associations (if needed)
	// if (dto.getIngredients() != null) {
	//
	// for (ProductDto ingredient : dto.getIngredients()) {
	// ingredient.setComposedProduct(utilsService.transform(persisted,
	// ProductDto.class));
	// save(ingredient);
	// }
	// }

	log.info("Saved Product: " + product);
	return utilsService.transform(persisted, ProductDto.class);
    }

    @Override
    public void delete(ProductDto dto) throws ProductNotFoundException {

	Product product = productDao.findByName(dto.getName());
	if (product == null) {
	    throw new ProductNotFoundException();
	}
	product.setStatus(Status.DELETED);
	productDao.save(product);

	productDao.delete(utilsService.transform(dto, Product.class));
    }

    /**
     * Implementation of the generic Service interface.
     * 
     * @throws CustomValidationException
     * @throws IngredientWithProductsException
     * @throws ProductWithoutFamilyException
     */
    @Override
    public void save(Dto dto)
	    throws CustomValidationException, ProductWithoutFamilyException, IngredientWithProductsException {
	save((ProductDto) dto);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto findOne(Long id) {
	return utilsService.transform(productDao.findOne(id), ProductDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Product findOneNoConversion(Long id) {
	return productDao.findOne(id);
    }

    @Override
    public List<ProductDto> findAllIngredients() {
	return utilsService.transform(productDao.findAllIngredients(), ProductDto.class);

    }

    @Override
    public List<ProductDto> findAllSellableFromFamily(ProductFamilyDto dto) {
	ProductFamily pf = utilsService.transform(dto, ProductFamily.class);
	return utilsService.transform(productDao.findAllSellableFromFamily(pf), ProductDto.class);
    }

}
