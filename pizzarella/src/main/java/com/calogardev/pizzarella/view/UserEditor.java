package com.calogardev.pizzarella.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.calogardev.pizzarella.dto.UserDto;
import com.calogardev.pizzarella.exception.CustomValidationException;
import com.calogardev.pizzarella.exception.UserNotFoundException;
import com.calogardev.pizzarella.service.UserService;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringComponent
@UIScope
public class UserEditor extends VerticalLayout {

    private static final long serialVersionUID = 4094673661154949593L;

    @Autowired
    private UserService userService;

    private UserDto userDto;

    private BeanValidationBinder<UserDto> binder = new BeanValidationBinder(UserDto.class);

    /* Action buttons */
    Button save = new Button("Save", FontAwesome.SAVE);
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", FontAwesome.TRASH_O);
    CssLayout actions = new CssLayout(save, cancel, delete);

    private PasswordField password;
    private PasswordField passwordConfirm;

    @Autowired
    public UserEditor() {

	addTextField("name", "Name");
	addTextField("surnames", "Surnames");
	addTextField("nickname", "Username");
	addTextField("dni", "DNI");
	addPasswordField("password", "Confirm password");

	addComponents(actions);

	// Configure and style components
	setSpacing(true);
	actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
	save.setStyleName(ValoTheme.BUTTON_PRIMARY);
	save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

	// wire action buttons to save, delete and reset
	save.addClickListener(e -> saveUser(userDto));
	delete.addClickListener(e -> deleteUser(userDto));
	cancel.addClickListener(e -> editUser(userDto));
	setVisible(false);
    }

    public interface ChangeHandler {
	void onChange();
    }

    public void editUser(UserDto dto) {

	if (dto == null) {
	    setVisible(false);
	    return;
	}
	final boolean persisted = dto.getId() != null;
	if (persisted) {
	    // Perform update (needs id)
	    userDto = userService.findOne(dto.getId());
	} else {
	    // Perform create
	    userDto = dto;
	}
	cancel.setVisible(persisted);

	binder.setBean(userDto);

	setVisible(true);
	// A hack to ensure the whole form is visible
	save.focus();
    }

    private void saveUser(UserDto userDto) {

	// Check password again for safety
	System.out.println(password.getValue());
	System.out.println(passwordConfirm.getValue());

	if (!password.getValue().equals(passwordConfirm.getValue())) {
	    Notification n = new Notification("Passwords don't match", null, Notification.Type.ERROR_MESSAGE, true);
	    n.show(Page.getCurrent());
	    return;
	}

	if (binder.validate().isOk()) {
	    try {
		binder.writeBean(userDto);
		userService.save(userDto);
		Notification.show("Record created correctly.");

	    } catch (CustomValidationException | ValidationException e) {
		Notification n = new Notification(e.getMessage(), null, Notification.Type.ERROR_MESSAGE, true);
		n.show(Page.getCurrent());
		return;
	    }
	}
    }

    private void deleteUser(UserDto userDto) {

	try {
	    userService.delete(userDto);
	} catch (UserNotFoundException e) {
	    Notification n = new Notification(e.getMessage(), null, Notification.Type.ERROR_MESSAGE, true);
	    n.show(Page.getCurrent());
	}

	// ConfirmDialog.show(UI.getCurrent(), "Are you sure?", new
	// ConfirmDialog.Listener() {
	//
	// @Override
	// public void onClose(ConfirmDialog dialog) {
	// if (dialog.isConfirmed()) {
	// // Confirmed to continue
	// try {
	// userService.delete(userDto);
	// } catch (UserNotFoundException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	// });
    }

    public void setChangeHandler(ChangeHandler h) {
	// ChangeHandler is notified when either save or delete
	// is clicked
	save.addClickListener(e -> h.onChange());
	delete.addClickListener(e -> h.onChange());
    }

    public void addTextField(String attributeName, String caption) {
	TextField field = new TextField(caption);
	binder.bind(field, attributeName);
	addComponent(field);
    }

    public void addPasswordField(String attributeName, String confirmMessage) {

	password = new PasswordField("Password");
	addComponent(password);
	passwordConfirm = new PasswordField(confirmMessage);
	addComponent(passwordConfirm);
	// We don't bind it because it doesn't belong to the dto

	Binder.BindingBuilder<UserDto, String> bBuilder = binder.forField(password).withValidator(
		passwordValue -> passwordValue.equals(passwordConfirm.getValue()), "Password don't match");

	Binder.Binding<UserDto, String> returnBinder = bBuilder.bind(attributeName);

	// Revalidate return date when password confirm changes
	passwordConfirm.addValueChangeListener(event -> returnBinder.validate());
    }

}