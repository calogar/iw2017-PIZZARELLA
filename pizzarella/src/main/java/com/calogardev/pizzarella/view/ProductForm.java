package com.calogardev.pizzarella.view;

import java.util.ArrayList;
import java.util.List;

import com.calogardev.pizzarella.dao.ProductService;
import com.calogardev.pizzarella.dto.ProductDto;
import com.calogardev.pizzarella.dto.ProductFamilyDto;
import com.calogardev.pizzarella.exception.IngredientWithProductsException;
import com.calogardev.pizzarella.exception.ProductWithoutFamilyException;
import com.calogardev.pizzarella.service.ProductFamilyService;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;

@SpringComponent
@ViewScope
public class ProductForm extends FormLayout {

    private static final long serialVersionUID = 3784046246477452629L;

    private ProductDto dto;

    // TODO: autowiring this
    private ProductService productService;
    private ProductFamilyService familyService;

    Binder<ProductDto> binder;

    List<Component> formFields;

    public ProductForm(ProductDto dto, ProductService productService, ProductFamilyService familyService) {
	this.dto = dto;
	this.productService = productService;
	this.familyService = familyService;

	binder = new Binder(ProductDto.class);
	formFields = new ArrayList<>();

	buildFields();
	buildButtons();
	binder.readBean(dto);

	// Add all components to the form
	for (Component component : formFields) {
	    component.setWidth("100%");
	    addComponent(component);
	}
	setMargin(true);
    }

    public void buildFields() {
	TextField nameField = new TextField("Name");
	binder.bind(nameField, "name");
	formFields.add(nameField);

	ComboBox<ProductFamilyDto> familyComboBox = new ComboBox<ProductFamilyDto>();
	familyComboBox.setItems(familyService.findAll());
	familyComboBox.setItemCaptionGenerator(ProductFamilyDto::getName);
	binder.bind(familyComboBox, "family");
	formFields.add(familyComboBox);

	TextField priceField = new TextField("Price");
	binder.bind(priceField, "price");
	formFields.add(priceField);

	TextField vatField = new TextField("VAT");
	binder.bind(vatField, "vat");
	formFields.add(vatField);

	TextField amountField = new TextField("Amount");
	binder.bind(amountField, "amount");
	formFields.add(amountField);

	// TODO: if this form is used to create, the id will be null. How will
	// this behave?
	TwinColSelect<ProductDto> productsSelect = new TwinColSelect<>();
	// We don't want to be able to select our own product (on updating)
	productsSelect.setItems(productService.findAllExceptOne(dto.getId()));
	binder.bind(productsSelect, "products");
	formFields.add(productsSelect);
    }

    public void buildButtons() {
	Button saveButton = new Button("Save", event -> {
	    try {
		binder.writeBean(dto);
		productService.save(dto);

		Notification.show("Record created correctly.");

	    } catch (ValidationException | ProductWithoutFamilyException | IngredientWithProductsException e) {
		Notification.show("The data could not be saved, " + "please check the error messages for each field");
		Notification n = new Notification(e.getMessage(), null, Notification.Type.ERROR_MESSAGE, true);
		n.show(Page.getCurrent());
	    }
	});

	Button resetButton = new Button("Reset", event -> {
	    binder.readBean(dto);
	});

	formFields.add(saveButton);
	formFields.add(resetButton);
    }
}
