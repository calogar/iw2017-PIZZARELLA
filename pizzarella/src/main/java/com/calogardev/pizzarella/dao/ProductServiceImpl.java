package com.calogardev.pizzarella.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calogardev.pizzarella.dto.Dto;
import com.calogardev.pizzarella.dto.ProductDto;
import com.calogardev.pizzarella.enums.Status;
import com.calogardev.pizzarella.exception.DuplicatedUniqueAttributeException;
import com.calogardev.pizzarella.exception.EmptyAttributeException;
import com.calogardev.pizzarella.exception.IngredientWithProductsException;
import com.calogardev.pizzarella.exception.ProductWithoutFamilyException;
import com.calogardev.pizzarella.exception.ShortAttributeException;
import com.calogardev.pizzarella.model.Product;
import com.calogardev.pizzarella.service.UtilsService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private UtilsService utilsService;

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
	}

	/**
	 * For being able to use a generic service interface
	 * 
	 * @param dto
	 *            The saved dto, it must be able to be casted to ProductDto
	 * @throws ProductWithoutFamilyException
	 * @throws IngredientWithProductsException
	 */
	@Override
	public void save(Dto dto) throws EmptyAttributeException, ShortAttributeException,
			DuplicatedUniqueAttributeException, ProductWithoutFamilyException, IngredientWithProductsException {
		save((ProductDto) dto);
	}

	@Override
	public List<ProductDto> findAll() {
		return utilsService.transform(productDao.findAll(), ProductDto.class);
	}

	@Override
	public List<ProductDto> findAllExceptOne(Long id) {
		return utilsService.transform(productDao.findAllExceptOne(id), ProductDto.class);
	}

	public void delete(ProductDto dto) {
		productDao.delete(utilsService.transform(dto, Product.class));
	}
}
