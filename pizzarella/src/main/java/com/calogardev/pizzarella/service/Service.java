package com.calogardev.pizzarella.service;

import com.calogardev.pizzarella.dto.Dto;
import com.calogardev.pizzarella.exception.DuplicatedUniqueAttributeException;
import com.calogardev.pizzarella.exception.EmptyAttributeException;
import com.calogardev.pizzarella.exception.ShortAttributeException;

public interface Service {
	public void save(Dto dto)
			throws EmptyAttributeException, ShortAttributeException, DuplicatedUniqueAttributeException;
}
