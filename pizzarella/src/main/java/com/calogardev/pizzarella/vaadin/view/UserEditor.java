package com.calogardev.pizzarella.vaadin.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.dialogs.ConfirmDialog;

import com.calogardev.pizzarella.exception.CustomValidationException;
import com.calogardev.pizzarella.exception.UserNotFoundException;
import com.calogardev.pizzarella.model.User;
import com.calogardev.pizzarella.service.UserService;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringComponent
@UIScope
public class UserEditor extends VerticalLayout {

    private static final long serialVersionUID = 4094673661154949593L;

    @Autowired
    private UserService userService;

    private User user;

    private BeanValidationBinder<User> binder = new BeanValidationBinder(User.class);

    /* Editor fields */
    private TextField name = new TextField("Name");
    private TextField surnames = new TextField("Surnames");
    private TextField username = new TextField("Username");
    private TextField dni = new TextField("DNI");
    private PasswordField password = new PasswordField("Password");
    private PasswordField passwordConfirm = new PasswordField("Password confirmation");

    /* Action buttons */
    private Button saveButton = new Button("Save", VaadinIcons.CHECK);
    private Button cancelButton = new Button("Cancel", VaadinIcons.CLOSE);
    private Button deleteButton = new Button("Delete", VaadinIcons.TRASH);
    private CssLayout actions = new CssLayout(saveButton, cancelButton, deleteButton);

    @Autowired
    public UserEditor() {

	addComponents(name, surnames, username, dni, password, passwordConfirm, actions);

	// Add behavior to menu buttons
	saveButton.addClickListener(e -> save(user));
	deleteButton.addClickListener(e -> delete(user));
	cancelButton.addClickListener(e -> edit(user));

	createBindings();
	styleComponents();
	setVisible(false);
    }

    public void edit(User u) {

	if (u == null) {
	    setVisible(false);
	    return;
	}
	if (u.getId() == null) {
	    // Perform create
	    user = u;
	    cancelButton.setVisible(false);
	} else {
	    // Perform update
	    try {
		user = userService.findOne(u.getId());
	    } catch (UserNotFoundException e) {
		new ErrorNotification(e.getMessage());
	    }
	    cancelButton.setVisible(true);
	}

	binder.setBean(user);

	setVisible(true);
	saveButton.focus();
    }

    private void save(User user) {

	if (!password.getValue().equals(passwordConfirm.getValue())) {
	    new ErrorNotification("Passwords don't match");
	    return;
	}

	if (binder.validate().isOk()) {
	    try {
		// TODO Should not be necessary
		// binder.writeBean(user);
		userService.save(user);
		new SuccessNotification();

	    } catch (CustomValidationException e) {
		new ErrorNotification(e.getMessage());
		return;
	    }
	}
    }

    private void delete(User user) {

	ConfirmDialog.show(UI.getCurrent(), "Are you sure?", new ConfirmDialog.Listener() {
	    @Override
	    public void onClose(ConfirmDialog dialog) {
		if (dialog.isConfirmed()) {
		    try {
			userService.delete(user);
		    } catch (UserNotFoundException e) {
			new ErrorNotification(e.getMessage());
		    }
		}
	    }
	});
    }

    private void createBindings() {
	binder.bindInstanceFields(user);

	Binder.BindingBuilder<User, String> bBuilder = binder.forField(password).withValidator(
		passwordValue -> passwordValue.equals(passwordConfirm.getValue()), "Password don't match");
	// TODO check it works
	Binder.Binding<User, String> returnBinder = bBuilder.bind("password");
	passwordConfirm.addValueChangeListener(event -> returnBinder.validate());
    }

    private void styleComponents() {
	actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
	saveButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
	saveButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
    }

    public void setChangeHandler(ChangeHandler h) {
	saveButton.addClickListener(e -> h.onChange());
	deleteButton.addClickListener(e -> h.onChange());
    }

    public interface ChangeHandler {
	void onChange();
    }
}