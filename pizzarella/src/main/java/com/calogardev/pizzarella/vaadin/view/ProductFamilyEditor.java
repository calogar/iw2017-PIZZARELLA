package com.calogardev.pizzarella.vaadin.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.dialogs.ConfirmDialog;

import com.calogardev.pizzarella.exception.CustomValidationException;
import com.calogardev.pizzarella.exception.ProductFamilyNotFoundException;
import com.calogardev.pizzarella.model.ProductFamily;
import com.calogardev.pizzarella.service.ProductFamilyService;
import com.calogardev.pizzarella.vaadin.view.UserEditor.ChangeHandler;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringComponent
@UIScope
public class ProductFamilyEditor extends VerticalLayout {

    private static final long serialVersionUID = 4642294599803498701L;

    @Autowired
    private ProductFamilyService pfService;

    private ProductFamily productFamily;

    private Binder<ProductFamily> binder = new BeanValidationBinder(ProductFamily.class);

    /* Editor fields */
    private TextField name = new TextField("Name");
    private TextField code = new TextField("Code");
    private Label nameStatus = new Label();
    private Label codeStatus = new Label();

    /* Editor actions */
    Button saveButton = new Button("Save", VaadinIcons.CHECK);
    Button cancelButton = new Button("Cancel", VaadinIcons.CLOSE);
    Button deleteButton = new Button("Delete", VaadinIcons.TRASH);
    private CssLayout actions = new CssLayout(saveButton, cancelButton, deleteButton);

    @Autowired
    public ProductFamilyEditor() {

	addComponents(name, nameStatus, code, codeStatus, actions);

	// Add behavior to menu buttons
	saveButton.addClickListener(e -> save(productFamily));
	deleteButton.addClickListener(e -> delete(productFamily));
	cancelButton.addClickListener(e -> edit(productFamily));

	createBindings();
	styleComponents();
	setVisible(false);
    }

    /**
     * Loads the entity (can be a new one) and opens the editor with it
     * 
     * @param pf
     */
    public void edit(ProductFamily pf) {

	if (pf == null) {
	    setVisible(false);
	    return;
	}
	if (pf.getId() == null) {
	    // Perform create
	    productFamily = pf;
	    cancelButton.setVisible(false);
	} else {
	    // Perform update (needs id)
	    try {
		productFamily = pfService.findOne(pf.getId());
	    } catch (ProductFamilyNotFoundException e) {
		e.printStackTrace();
	    }
	    cancelButton.setVisible(true);
	}

	// Automatically updates bean when fields are changed
	// Must be used for validation instead or writeBean / readBean
	binder.setBean(productFamily);

	setVisible(true);
	// A hack to ensure the whole form is visible
	saveButton.focus();
    }

    private void save(ProductFamily pf) {

	if (binder.validate().isOk()) {
	    try {
		// TODO Should not be necessary
		// binder.writeBean(pf);
		pfService.save(pf);
		new SuccessNotification();

	    } catch (CustomValidationException e) {
		new ErrorNotification(e.getMessage());
		return;
	    }
	}
    }

    private void delete(ProductFamily pf) {

	ConfirmDialog.show(UI.getCurrent(), "Are you sure?", new ConfirmDialog.Listener() {
	    @Override
	    public void onClose(ConfirmDialog dialog) {
		if (dialog.isConfirmed()) {
		    try {
			pfService.delete(pf);
		    } catch (ProductFamilyNotFoundException e) {
			new ErrorNotification(e.getMessage());
		    }
		}
	    }
	});
    }

    private void createBindings() {
	binder.forField(name)
		.withValidator(
			name -> name.length() > ProductFamily.MIN_LENGTH && name.length() < ProductFamily.MAX_LENGTH,
			"The name must be between " + ProductFamily.MIN_LENGTH + " and " + ProductFamily.MAX_LENGTH)
		.withStatusLabel(nameStatus).bind("name");
	binder.forField(code)
		.withValidator(
			code -> code.length() > ProductFamily.MIN_LENGTH && code.length() < ProductFamily.MAX_LENGTH,
			"The code must be between " + ProductFamily.MIN_LENGTH + " and " + ProductFamily.MAX_LENGTH)
		.withStatusLabel(codeStatus).bind("code");
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
}
