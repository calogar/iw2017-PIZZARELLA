package com.calogardev.pizzarella.view;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.util.StringUtils;

import com.calogardev.pizzarella.dto.CreateUserDto;
import com.calogardev.pizzarella.exception.DuplicatedUniqueAttributeException;
import com.calogardev.pizzarella.exception.EmptyAttributeException;
import com.calogardev.pizzarella.exception.PasswordMismatchException;
import com.calogardev.pizzarella.exception.ShortAttributeException;
import com.calogardev.pizzarella.service.UserService;
import com.calogardev.pizzarella.service.UserServiceImpl;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

@SpringComponent
@ViewScope
public class UserForm extends FormLayout {

	private static final long serialVersionUID = -8749480097031731350L;

	// We can't autowire it because if we call it from create we will pass an
	// empty object but if we call it from update, we will pass another one.
	private CreateUserDto dto;

	// TODO: We can't autowire here if we keep creating a UserForm with "new".
	// But we can't do it properly unless we know how to instantiate the correct
	// dto.
	private UserService userService = new UserServiceImpl();

	private Binder<CreateUserDto> binder;

	private Map<String, Component> formFields;

	private PasswordField passwordField = null;
	private PasswordField passwordConfirmField = null;

	public UserForm(CreateUserDto dto, UserService userService) {
		this.dto = dto;
		this.userService = userService;
		binder = new Binder(CreateUserDto.class);
		formFields = new LinkedHashMap<String, Component>();

		buildFields();
		// We need to call build explicitly so we can add a password
	}

	public void buildFields() {
		Field[] fields = dto.getDeclaredFields();

		for (Field field : fields) {
			String name = field.getName();
			String capitalizedName = StringUtils.capitalize(name);

			// Passwords should be declared with addPassword
			if (!name.toLowerCase().contains("password")) {
				TextField attributeField = new TextField(capitalizedName);
				formFields.put(name, attributeField);
				binder.bind(attributeField, name);
			}
		}
	}

	public void build() {

		binder.readBean(dto);

		Button saveButton = new Button("Save", event -> {
			try {
				if (hasPassword() && !passwordMatch()) {
					throw new PasswordMismatchException();
				} else {
					binder.writeBean(dto);
					userService.save(dto);

					Notification.show("Record created correctly.");
				}
			} catch (ValidationException e) {
				Notification.show("The data could not be saved, " + "please check the error messages for each field");
			} catch (PasswordMismatchException | EmptyAttributeException | ShortAttributeException
					| DuplicatedUniqueAttributeException e) {
				Notification n = new Notification(e.getMessage(), null, Notification.Type.ERROR_MESSAGE, true);
				n.show(Page.getCurrent());
			}
		});

		Button resetButton = new Button("Reset", event -> {
			binder.readBean(dto);
		});

		formFields.put("saveButton", saveButton);
		formFields.put("resetButton", resetButton);

		// Add all components to the form
		for (Iterator<Entry<String, Component>> it = formFields.entrySet().iterator(); it.hasNext();) {
			Component component = it.next().getValue();
			component.setWidth("100%");
			addComponent(component);
		}
		setMargin(true);
	}

	/**
	 * Add an extra confirm password field and enable password check on save.
	 * This should be called before build, that's why we must explicitly call
	 * build.
	 * 
	 */
	public void addPassword(String passwordFieldName, String confirmMessage) {
		passwordField = new PasswordField(StringUtils.capitalize(passwordFieldName));
		passwordConfirmField = new PasswordField(confirmMessage);
		formFields.put(passwordFieldName, passwordField);
		formFields.put(confirmMessage, passwordConfirmField);
		// We don't bind passwordConfirm because it's not a Dto attr.
		binder.bind(passwordField, passwordFieldName);
	}

	private Boolean hasPassword() {
		return passwordField != null && passwordConfirmField != null;
	}

	private Boolean passwordMatch() {
		if (hasPassword()) {
			String password = passwordField.getValue();
			String passwordConfirm = passwordConfirmField.getValue();
			return password.equals(passwordConfirm);
		}
		return false;
	}

	/**
	 * So we can edit a specific form field
	 * 
	 * @param key
	 * @return
	 */
	public AbstractTextField getField(String fieldName) {
		return (AbstractTextField) formFields.get(fieldName);
	}
}
