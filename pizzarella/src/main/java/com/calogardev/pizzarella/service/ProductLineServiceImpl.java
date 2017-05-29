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

    @Override
    public ProductLineDto save(ProductLineDto plDto) {
	ProductLine pl = utilsService.transform(plDto, ProductLine.class);
	pl = productLineDao.save(pl);
	System.out.println("Saved Product Line: " + pl);
	return utilsService.transform(pl, ProductLineDto.class);
    }
}
