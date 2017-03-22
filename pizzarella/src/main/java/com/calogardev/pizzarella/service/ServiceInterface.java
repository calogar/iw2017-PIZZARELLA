package com.calogardev.pizzarella.service;

import com.calogardev.pizzarella.dto.Dto;
import com.calogardev.pizzarella.exception.DuplicatedUniqueAttributeException;
import com.calogardev.pizzarella.exception.EmptyAttributeException;
import com.calogardev.pizzarella.exception.IngredientWithProductsException;
import com.calogardev.pizzarella.exception.ProductWithoutFamilyException;
import com.calogardev.pizzarella.exception.ShortAttributeException;

public interface ServiceInterface {
    public void save(Dto dto) throws EmptyAttributeException, ShortAttributeException,
	    DuplicatedUniqueAttributeException, ProductWithoutFamilyException, IngredientWithProductsException;
}
