package com.calogardev.pizzarella.view.productfamily;

import org.springframework.beans.factory.annotation.Autowired;

import com.calogardev.pizzarella.dto.ProductFamilyDto;
import com.calogardev.pizzarella.service.ProductFamilyService;
import com.calogardev.pizzarella.view.Form;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.FormLayout;

/**
 * Uses the custom Form api to build a form for Product Families
 * 
 * @author calogar
 *
 */
@SpringComponent
@ViewScope
public class ProductFamilyForm extends FormLayout {

    private static final long serialVersionUID = 8034667055098606837L;

    @Autowired
    private Form<ProductFamilyDto> form;

    @Autowired
    private ProductFamilyService familyService;

    // Can't be autowired because it's from DB when editing
    private ProductFamilyDto dto;

    ProductFamilyForm() {
    }

    public void build(ProductFamilyDto dto) {
	this.dto = dto;

	form.configure(dto, ProductFamilyDto.class, familyService);
	form.addTextField("name");
	form.addTextField("code");
	form.build();

	addComponent(form);
    }
}
