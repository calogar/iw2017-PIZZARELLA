package com.calogardev.pizzarella.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calogardev.pizzarella.dao.ProductFamilyDao;
import com.calogardev.pizzarella.dto.ProductFamilyDto;
import com.calogardev.pizzarella.exception.EmptyAttributeException;
import com.calogardev.pizzarella.model.ProductFamily;

@Service
public class ProductFamilyServiceImpl implements ProductFamilyService {

    @Autowired
    private ProductFamilyDao productFamilyDao;

    @Autowired
    private UtilsService utilsService;

    @Override
    public List<ProductFamilyDto> findAll() {
	List<ProductFamily> productFamilies = productFamilyDao.findAll();
	return utilsService.transform(productFamilies, ProductFamilyDto.class);
    }

    @Override
    public void save(ProductFamilyDto dto) throws EmptyAttributeException {
	if (isEmpty(dto.getName()) || isEmpty(dto.getCode())) {
	    throw new EmptyAttributeException();
	}
	productFamilyDao.save(utilsService.transform(dto, ProductFamily.class));
    }

    /**
     * Checks if string is null or "" (blank)
     * 
     * @param str
     *            The string to check
     * @return The response
     */
    private Boolean isEmpty(String str) {
	return StringUtils.isEmpty(str);
    }

}
