package com.calogardev.pizzarella.view;

import java.util.ArrayList;
import java.util.List;

import com.calogardev.pizzarella.dto.ProductFamilyDto;
import com.calogardev.pizzarella.exception.EmptyAttributeException;
import com.calogardev.pizzarella.service.ProductFamilyService;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class ProductFamilyForm extends FormLayout {

    private ProductFamilyDto dto;
    // TODO: autowire this
    private ProductFamilyService productFamilyService;
    private Binder<ProductFamilyDto> binder;
    private List<Component> formFields;

    public ProductFamilyForm(ProductFamilyDto dto, ProductFamilyService productFamilyService) {
	this.dto = dto;
	this.productFamilyService = productFamilyService;

	binder = new Binder(ProductFamilyDto.class);
	formFields = new ArrayList<Component>();

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

    private void buildFields() {
	TextField codeField = new TextField("Unique code");
	binder.bind(codeField, "code");
	formFields.add(codeField);

	TextField nameField = new TextField("Human readable name");
	binder.bind(nameField, "name");
	formFields.add(nameField);
    }

    private void buildButtons() {
	Button saveButton = new Button("Save", event -> {
	    try {
		binder.writeBean(dto);
		productFamilyService.save(dto);

		Notification.show("Record created correctly.");

	    } catch (ValidationException | EmptyAttributeException e) {
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
