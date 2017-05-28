package com.calogardev.pizzarella.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calogardev.pizzarella.dao.ProductLineDao;
import com.calogardev.pizzarella.dto.ProductLineDto;
import com.calogardev.pizzarella.enums.Status;
import com.calogardev.pizzarella.model.ProductLine;

@Service
public class ProductLineServiceImpl implements ProductLineService {

    @Autowired
    private ProductLineDao productLineDao;

    @Autowired
    private UtilsService utilsService;

    @Override
    public void delete(ProductLineDto dto) {
	if (dto.getId() == null) {
	    return;
	}

	ProductLine pl = utilsService.transform(dto, ProductLine.class);
	pl.setStatus(Status.DELETED);
	productLineDao.save(pl);
    }
}
