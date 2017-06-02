package com.calogardev.pizzarella.service;

import java.util.List;

import com.calogardev.pizzarella.exception.CustomValidationException;
import com.calogardev.pizzarella.model.ProductFamily;

public interface ProductFamilyService {

	public List<ProductFamily> findAll();

	public ProductFamily save(ProductFamily dto) throws CustomValidationException;

	public void deleteByCode(String code);
}
