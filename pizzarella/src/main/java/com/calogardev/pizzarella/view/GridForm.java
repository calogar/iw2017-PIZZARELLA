package com.calogardev.pizzarella.view;

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
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class GridForm<T extends Dto> extends CssLayout {

    private static final long serialVersionUID = 1195356467822216624L;

    private BeanValidationBinder<T> binder;
    private final Class<T> klass;
    private T dto;
    private GenericService service;

    // We use a wrapped instance instead of extending the class so we can set
    // the size
    private GridLayout form;

    public GridForm(T dto, Class<T> klass, GenericService service, Integer maxI, Integer maxJ) {
	this.klass = klass;
	binder = new BeanValidationBinder<>(klass);
	this.dto = dto;
	this.service = service;
	form = new GridLayout(maxI, maxJ);

	// setMargin(true);
    }

    public void addFloatField(String attributeName, Integer i, Integer j) {
	TextField field = new TextField(StringUtils.capitalize(attributeName));
	binder.forField(field).withNullRepresentation("0")
		.withConverter(new StringToFloatConverter("Must be a decimal number")).bind(attributeName);
	addToForm(field, i, j);
    }

    public void addIntegerField(String attributeName, Integer i, Integer j) {
	TextField field = new TextField(StringUtils.capitalize(attributeName));
	binder.forField(field).withNullRepresentation("0")
		.withConverter(new StringToIntegerConverter("Must be a number")).bind(attributeName);
	addToForm(field, i, j);
    }

    public TextField addTextField(String attributeName, String caption, Integer i, Integer j) {
	TextField field = new TextField(caption);
	binder.bind(field, attributeName);
	addToForm(field, i, j);
	return field;
    }

    public void addPasswordField(String attributeName, String confirmMessage, Integer iPassword, Integer jPassword,
	    Integer iConfirm, Integer jConfirm) {

	PasswordField password = new PasswordField("Password");
	addToForm(password, iPassword, jPassword);
	PasswordField passwordConfirm = new PasswordField(confirmMessage);
	addToForm(passwordConfirm, iConfirm, jConfirm);
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

    public void addSaveButton(Integer i, Integer j) {

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
	addToForm(saveButton, i, j);
    }

    public void addResetButton(Integer i, Integer j) {

	Button resetButton = new Button("Reset", event -> {
	    binder.readBean(dto);
	});
	addToForm(resetButton, i, j);
    }

    public void addToForm(Component component, Integer i, Integer j) {
	// component.setWidth("100%");
	form.addComponent(component, i, j);
	form.setComponentAlignment(component, Alignment.TOP_CENTER);
    }

    public GridLayout getForm() {
	return form;
    }
}
