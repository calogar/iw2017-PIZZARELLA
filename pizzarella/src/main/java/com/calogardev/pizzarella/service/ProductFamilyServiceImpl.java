package com.calogardev.pizzarella.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calogardev.pizzarella.dao.ProductFamilyDao;
import com.calogardev.pizzarella.exception.CustomValidationException;
import com.calogardev.pizzarella.exception.ProductFamilyNotFoundException;
import com.calogardev.pizzarella.model.ProductFamily;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductFamilyServiceImpl implements ProductFamilyService {

    @Autowired
    private ProductFamilyDao productFamilyDao;

    @Override
    @Transactional(readOnly = true)
    public List<ProductFamily> findAll() {

	log.info("Finding all product families");
	return productFamilyDao.findAll();
    }

    @Override
    public ProductFamily save(ProductFamily pf) throws CustomValidationException {

	if (pf.getId() == null) {
	    // Create instead of update (must do uniqueness check)
	    if (productFamilyDao.existsByCode(pf.getCode())) {
		throw new CustomValidationException("A product family already has this code");
	    }
	}

	ProductFamily persisted = productFamilyDao.save(pf);
	log.info("Saved product: " + persisted);
	return persisted;
    }

    @Override
    public void deleteByCode(String code) {
	productFamilyDao.deleteByCode(code);
	log.info("Deleted product family with code: " + code);
    }

    @Override
    public ProductFamily findOne(Long id) throws ProductFamilyNotFoundException {
	final ProductFamily pf = productFamilyDao.findOne(id);
	if (pf == null) {
	    throw new ProductFamilyNotFoundException();
	}
	log.info("Found product family: " + pf);
	return pf;
    }

    @Override
    public void delete(ProductFamily pf) throws ProductFamilyNotFoundException {
	if (pf.getId() == null) {
	    // If it hasn't got id, it's not persisted
	    throw new ProductFamilyNotFoundException();
	}
	String code = pf.getCode();
	productFamilyDao.delete(pf);
	log.info("Deleted product family with code: " + code);
    }
}
