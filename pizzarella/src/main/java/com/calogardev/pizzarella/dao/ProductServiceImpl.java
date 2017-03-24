package com.calogardev.pizzarella.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calogardev.pizzarella.dto.Dto;
import com.calogardev.pizzarella.dto.ProductDto;
import com.calogardev.pizzarella.enums.Status;
import com.calogardev.pizzarella.exception.CustomValidationException;
import com.calogardev.pizzarella.exception.IngredientWithProductsException;
import com.calogardev.pizzarella.exception.ProductWithoutFamilyException;
import com.calogardev.pizzarella.model.Product;
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
    public List<ProductDto> findAll() {
	return utilsService.transform(productDao.findAll(), ProductDto.class);
    }

    @Override
    public List<ProductDto> findAllExceptOne(Long id) {
	return utilsService.transform(productDao.findAllExceptOne(id), ProductDto.class);
    }

    @Override
    public void save(ProductDto productDto) throws ProductWithoutFamilyException, IngredientWithProductsException {
	Product product = utilsService.transform(productDto, Product.class);
	if (product.getFamily() == null) {
	    throw new ProductWithoutFamilyException();
	} else if (product.getFamily().isIngredient() && product.getProducts() == null) {
	    throw new IngredientWithProductsException();
	}

	product.setStatus(Status.ACTIVE);
	productDao.save(product);
	log.info("Saved Product: " + product.toString());
    }

    @Override
    public void delete(ProductDto dto) {
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
}
