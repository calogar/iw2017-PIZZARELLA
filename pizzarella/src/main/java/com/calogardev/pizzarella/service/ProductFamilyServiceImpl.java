package com.calogardev.pizzarella.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calogardev.pizzarella.dao.ProductFamilyDao;
import com.calogardev.pizzarella.dto.Dto;
import com.calogardev.pizzarella.dto.ProductFamilyDto;
import com.calogardev.pizzarella.enums.Status;
import com.calogardev.pizzarella.exception.CustomValidationException;
import com.calogardev.pizzarella.model.ProductFamily;

@Service
public class ProductFamilyServiceImpl implements ProductFamilyService {

    private static final Logger log = LoggerFactory.getLogger(ProductFamilyServiceImpl.class);

    @Autowired
    private ProductFamilyDao productFamilyDao;

    @Autowired
    private UtilsService utilsService;

    @Override
    @Transactional(readOnly = true)
    public List<ProductFamilyDto> findAll() {
	List<ProductFamily> productFamilies = productFamilyDao.findAll();
	return utilsService.transform(productFamilies, ProductFamilyDto.class);
    }

    @Override
    public ProductFamilyDto save(ProductFamilyDto dto) throws CustomValidationException {

	if (dto.getId() == null) {
	    // Perform create
	    if (productFamilyDao.existsByCode(dto.getCode())) {
		throw new CustomValidationException("A product family already has this code");
	    }
	} else {
	    // Perform update
	    if (productFamilyDao.findOne(dto.getId()).getStatus() != Status.ACTIVE) {
		throw new CustomValidationException("That Product Family is deleted and cannot be updated");
	    }
	}

	if (isEmpty(dto.getName()) || isEmpty(dto.getCode())) {
	    throw new CustomValidationException("Some fields are empty");
	}

	ProductFamily productFamily = utilsService.transform(dto, ProductFamily.class);
	productFamily.setStatus(Status.ACTIVE);
	ProductFamily persisted = productFamilyDao.save(productFamily);
	log.info("Saved Product: " + persisted);
	return utilsService.transform(persisted, ProductFamilyDto.class);
    }

    @Override
    public void save(Dto dto) throws CustomValidationException {
	save((ProductFamilyDto) dto);
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

    @Override
    public void deleteByCode(String code) {
	productFamilyDao.deleteByCode(code);
    }

}
