package com.calogardev.pizzarella.service;

import com.calogardev.pizzarella.dto.Dto;
import com.calogardev.pizzarella.exception.CustomValidationException;
import com.calogardev.pizzarella.exception.IngredientWithProductsException;
import com.calogardev.pizzarella.exception.ProductWithoutFamilyException;

public interface GenericService {

    public void save(Dto dto)
	    throws CustomValidationException, ProductWithoutFamilyException, IngredientWithProductsException;
}
