package com.calogardev.pizzarella.vaadin.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.calogardev.pizzarella.dto.ProductDto;
import com.calogardev.pizzarella.dto.ProductFamilyDto;
import com.calogardev.pizzarella.exception.CustomValidationException;
import com.calogardev.pizzarella.exception.ProductNotFoundException;
import com.calogardev.pizzarella.service.ProductFamilyService;
import com.calogardev.pizzarella.service.ProductService;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.converter.StringToFloatConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringComponent
@UIScope
public class ProductEditor extends VerticalLayout {

    private static final long serialVersionUID = -5536943756854501185L;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductFamilyService productFamilyService;

    private ProductDto productDto;

    private BeanValidationBinder<ProductDto> binder = new BeanValidationBinder(ProductDto.class);

    /* Action buttons */
    Button save = new Button("Save", FontAwesome.SAVE);
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", FontAwesome.TRASH_O);
    CssLayout actions = new CssLayout(save, cancel, delete);

    public ProductEditor() {
    }

    @PostConstruct
    void init() {

	addTextField("name", "Name");

	ComboBox<ProductFamilyDto> comboBox = new ComboBox<>();
	comboBox.setItems(productFamilyService.findAll());
	comboBox.setItemCaptionGenerator(ProductFamilyDto::getName);
	binder.bind(comboBox, "family");
	addComponent(comboBox);

	addFloatField("price", "Price");
	addFloatField("vat", "VAT");
	addIntegerField("amount", "Amount");

	CheckBox isIngredient = new CheckBox("Is ingredient?");
	binder.bind(isIngredient, "isIngredient");
	addComponent(isIngredient);

	TwinColSelect<ProductDto> selectIngredients = new TwinColSelect<>("Ingredients");
	selectIngredients.setItems(productService.findAllIngredients());
	selectIngredients.setItemCaptionGenerator(ProductDto::getName);
	binder.bind(selectIngredients, "ingredients");
	addComponent(selectIngredients);

	addComponents(actions);

	// Configure and style components
	setSpacing(true);
	actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
	save.setStyleName(ValoTheme.BUTTON_PRIMARY);
	save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

	// wire action buttons to save, delete and reset
	save.addClickListener(e -> saveProduct(productDto));
	delete.addClickListener(e -> deleteProduct(productDto));
	cancel.addClickListener(e -> editProduct(productDto));
	setVisible(false);
    }

    public interface ChangeHandler {
	void onChange();
    }

    public void editProduct(ProductDto dto) {

	if (dto == null) {
	    setVisible(false);
	    return;
	}
	final boolean persisted = dto.getId() != null;
	if (persisted) {
	    // Perform update (needs id)
	    productDto = productService.findOne(dto.getId());
	} else {
	    // Perform create
	    productDto = dto;
	}
	cancel.setVisible(persisted);

	binder.setBean(productDto);

	setVisible(true);
	// A hack to ensure the whole form is visible
	save.focus();
    }

    private void saveProduct(ProductDto productDto) {

	if (binder.validate().isOk()) {
	    try {
		binder.writeBean(productDto);
		productService.save(productDto);
		Notification.show("Record created correctly.");

	    } catch (CustomValidationException | ValidationException e) {
		Notification n = new Notification(e.getMessage(), null, Notification.Type.ERROR_MESSAGE, true);
		n.show(Page.getCurrent());
		return;
	    }
	}
    }

    private void deleteProduct(ProductDto productDto) {

	try {
	    productService.delete(productDto);
	} catch (ProductNotFoundException e) {
	    Notification n = new Notification(e.getMessage(), null, Notification.Type.ERROR_MESSAGE, true);
	    n.show(Page.getCurrent());
	}
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

    public void addFloatField(String attributeName, String caption) {
	TextField field = new TextField(caption);
	binder.forField(field).withNullRepresentation("0")
		.withConverter(new StringToFloatConverter("Must be a decimal number")).bind(attributeName);
	addComponent(field);
    }

    public void addIntegerField(String attributeName, String caption) {
	TextField field = new TextField(caption);
	binder.forField(field).withNullRepresentation("0")
		.withConverter(new StringToIntegerConverter("Must be a number")).bind(attributeName);
	addComponent(field);
    }
}
