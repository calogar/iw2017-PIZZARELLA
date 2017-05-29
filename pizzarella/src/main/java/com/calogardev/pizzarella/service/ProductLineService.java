package com.calogardev.pizzarella.service;

import com.calogardev.pizzarella.dto.ProductLineDto;

public interface ProductLineService {

    public void delete(ProductLineDto dto);

    public ProductLineDto save(ProductLineDto plDto);
}
