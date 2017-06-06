package com.calogardev.pizzarella.vaadin.view;

import org.springframework.beans.factory.annotation.Autowired;

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
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
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

    /* Editor actions */
    private Button saveButton = new Button("Save", VaadinIcons.CHECK);
    private Button resetButton = new Button("Reset", VaadinIcons.CLOSE);
    private Button deleteButton = new Button("Delete", VaadinIcons.TRASH);
    private CssLayout actions = new CssLayout(saveButton, resetButton, deleteButton);

    private Grid grid;

    @Autowired
    public ProductFamilyEditor() {

	addComponents(name, code, actions);

	// Add behavior to menu buttons
	saveButton.addClickListener(e -> save(productFamily));
	deleteButton.addClickListener(e -> delete(productFamily));
	resetButton.addClickListener(e -> edit(productFamily));

	createBindings();
	styleComponents();
	setDebugIds();
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
	    resetButton.setVisible(false);
	} else {
	    // Perform update (needs id)
	    try {
		productFamily = pfService.findOne(pf.getId());
	    } catch (ProductFamilyNotFoundException e) {
		e.printStackTrace();
	    }
	    resetButton.setVisible(true);
	}

	// Automatically updates bean when fields are changed
	// Must be used for validation instead or writeBean / readBean
	binder.setBean(productFamily);

	setVisible(true);
	// A hack to ensure the whole form is visible
	saveButton.focus();
	name.selectAll();
    }

    private void save(ProductFamily pf) {

	if (binder.validate().isOk()) {
	    try {
		pfService.save(pf);
		new SuccessNotification();

	    } catch (CustomValidationException e) {
		new ErrorNotification(e.getMessage());
		return;
	    }
	} else {
	    new ErrorNotification("There are errors in the form.<br>Please enter all required fields.");
	    return;
	}
    }

    private void delete(ProductFamily pf) {

	Window window = new Window("Delete");
	window.setModal(true);
	window.setDraggable(false);
	window.setResizable(false);
	window.setWidth(300.0f, Unit.PIXELS);

	Label message = new Label("Are you sure?");
	Button deleteButton = new Button("Delete", e -> {
	    try {
		pfService.delete(pf);
		grid.setItems(pfService.findAll());
		window.close();
	    } catch (ProductFamilyNotFoundException e1) {
		window.close();
		new ErrorNotification(e1.getMessage());
	    }
	});
	Button cancelButton = new Button("Cancel", e -> {
	    window.close();
	});

	message.addStyleName(ValoTheme.LABEL_H2);
	message.setWidth("100%");
	deleteButton.addStyleName(ValoTheme.BUTTON_DANGER);
	deleteButton.setWidth("100%");
	cancelButton.setWidth("100%");

	window.setContent(new VerticalLayout(message, deleteButton, cancelButton));
	getUI().getUI().addWindow(window);
    }

    public void setGrid(Grid grid) {
	this.grid = grid;
    }

    private void createBindings() {
	binder.bindInstanceFields(this);

	binder.addStatusChangeListener(event -> {
	    boolean isValid = event.getBinder().isValid();
	    saveButton.setEnabled(isValid);
	});
    }

    private void styleComponents() {
	actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
	saveButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
	saveButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
    }

    private void setDebugIds() {
	saveButton.setId("pf-save");
    }

    public void setChangeHandler(ChangeHandler h) {
	saveButton.addClickListener(e -> h.onChange());
	deleteButton.addClickListener(e -> h.onChange());
    }
}
