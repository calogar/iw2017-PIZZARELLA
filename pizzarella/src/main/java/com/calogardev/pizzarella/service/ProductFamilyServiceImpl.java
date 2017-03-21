package com.calogardev.pizzarella.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.calogardev.pizzarella.dao.ProductFamilyDao;
import com.calogardev.pizzarella.dto.ProductFamilyDto;
import com.calogardev.pizzarella.model.ProductFamily;

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
    public void save(ProductFamilyDto dto) {
	// TODO Auto-generated method stub

    }

}
