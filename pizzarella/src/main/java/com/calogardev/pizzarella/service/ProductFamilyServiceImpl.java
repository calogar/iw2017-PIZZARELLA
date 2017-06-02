package com.calogardev.pizzarella.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calogardev.pizzarella.dao.ProductFamilyDao;
import com.calogardev.pizzarella.exception.CustomValidationException;
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

		List<ProductFamily> productFamilies = productFamilyDao.findAll();
		log.info("Finding all product families");
		return productFamilies;
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
}
