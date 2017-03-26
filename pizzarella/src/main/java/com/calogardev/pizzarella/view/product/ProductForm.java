package com.calogardev.pizzarella.view.product;

import org.springframework.beans.factory.annotation.Autowired;

import com.calogardev.pizzarella.dao.ProductService;
import com.calogardev.pizzarella.dto.ProductDto;
import com.calogardev.pizzarella.dto.ProductFamilyDto;
import com.calogardev.pizzarella.service.ProductFamilyService;
import com.calogardev.pizzarella.view.Form;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;

/**
 * Uses the (custom) Form API to build a form for Products
 * 
 * @author calogar
 *
 */
@SpringComponent
@ViewScope
public class ProductForm extends FormLayout {

    private static final long serialVersionUID = 5189417294392398723L;

    @Autowired
    private Form<ProductDto> form;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductFamilyService familyService;

    // Can't be autowired because it's from DB when editing
    private ProductDto dto;

    public ProductForm() {

	form.configure(new ProductDto(), ProductDto.class, productService);
	form.addTextField("name");

	// Currently we have to add comboboxes ourselves
	ComboBox<ProductFamilyDto> comboBox = new ComboBox<ProductFamilyDto>();
	comboBox.setItems(familyService.findAll());
	comboBox.setItemCaptionGenerator(ProductFamilyDto::getName);
	form.getBinder().bind(comboBox, "family");
	form.addToForm(comboBox);
    }

    public void addDto(ProductDto dto) {
	this.dto = dto;
    }
}
