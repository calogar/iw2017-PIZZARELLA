package com.calogardev.pizzarella.view;

import java.util.List;

import org.springframework.util.StringUtils;

import com.calogardev.pizzarella.dto.Dto;
import com.calogardev.pizzarella.exception.CustomValidationException;
import com.calogardev.pizzarella.exception.IngredientWithProductsException;
import com.calogardev.pizzarella.exception.ProductWithoutFamilyException;
import com.calogardev.pizzarella.service.GenericService;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.converter.StringToFloatConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;

@SpringComponent
@ViewScope
public class Form<T extends Dto> extends FormLayout {

    private static final long serialVersionUID = 1195356467822216624L;

    private BeanValidationBinder<T> binder;
    private final Class<T> klass;
    private T dto;
    private GenericService service;

    public Form() {
	klass = null;
	setMargin(true);
    }

    public void configure(T dto, Class<T> klass, GenericService service) {

	binder = new BeanValidationBinder<>(klass);
	this.dto = dto;
	this.service = service;
    }

    public void addFloatField(String attributeName) {
	TextField field = new TextField(StringUtils.capitalize(attributeName));
	binder.forField(field).withNullRepresentation("0")
		.withConverter(new StringToFloatConverter("Must be a decimal number")).bind(attributeName);
	addToForm(field);
    }

    public void addIntegerField(String attributeName) {
	TextField field = new TextField(StringUtils.capitalize(attributeName));
	binder.forField(field).withNullRepresentation("0")
		.withConverter(new StringToIntegerConverter("Must be a number")).bind(attributeName);
	addToForm(field);
    }

    public TextField addTextField(String attributeName, String caption) {
	TextField field = new TextField(caption);
	binder.bind(field, attributeName);
	addToForm(field);
	return field;
    }

    public TextField addTextField(String attributeName) {
	return addTextField(attributeName, StringUtils.capitalize(attributeName));
    }

    // Not working because we can't pass an ItemCaptionGenerator<Dto>, we must
    // specify which Dto is it
    // public void addComboBox(String attributeName, List<Dto> dtos,
    // ItemCaptionGenerator<Dto> captionGenerator) {
    // ComboBox<Dto> comboBox = new ComboBox<Dto>();
    // comboBox.setItems(dtos);
    // comboBox.setItemCaptionGenerator(captionGenerator);
    // binder.bind(comboBox, attributeName);
    // addToForm(comboBox);
    // }

    /**
     * TODO: Not working because of generic dto
     * 
     * @param attributeName
     * @param dtos
     */
    public void addTwinColSelect(String attributeName, List<Dto> dtos) {
	TwinColSelect<Dto> select = new TwinColSelect<>();
	// We don't want to be able to select our own product (on updating)
	select.setItems(dtos);
	binder.bind(select, attributeName);
	addToForm(select);
    }

    public void addPasswordField(String attributeName, String confirmMessage) {
	PasswordField password = new PasswordField("Password");
	addToForm(password);
	PasswordField passwordConfirm = new PasswordField(confirmMessage);
	addToForm(passwordConfirm);
	// We don't bind it because it doesn't belong to the dto

	Binder.BindingBuilder<T, String> bBuilder = binder.forField(password).withValidator(
		passwordValue -> passwordValue.equals(passwordConfirm.getValue()), "Password don't match");

	Binder.Binding<T, String> returnBinder = bBuilder.bind(attributeName);

	// Revalidate return date when password confirm changes
	passwordConfirm.addValueChangeListener(event -> returnBinder.validate());
    }

    public BeanValidationBinder<T> getBinder() {
	return binder;
    }

    public void build() {

	binder.readBean(dto);

	Button saveButton = new Button("Save", event -> {
	    if (binder.validate().isOk()) {
		try {
		    binder.writeBean(dto);
		    service.save(dto);
		    Notification.show("Record created correctly.");

		} catch (CustomValidationException | ValidationException | ProductWithoutFamilyException
			| IngredientWithProductsException e) {
		    Notification n = new Notification(e.getMessage(), null, Notification.Type.ERROR_MESSAGE, true);
		    n.show(Page.getCurrent());
		}
	    }
	});

	Button resetButton = new Button("Reset", event -> {
	    binder.readBean(dto);
	});

	addToForm(saveButton);
	addToForm(resetButton);
    }

    public void addToForm(Component component) {
	component.setWidth("100%");
	addComponent(component);
    }
}
