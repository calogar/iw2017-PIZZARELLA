package com.calogardev.pizzarella.vaadin.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.calogardev.pizzarella.model.ProductFamily;
import com.calogardev.pizzarella.service.ProductFamilyService;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
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

	/* Editor actions */
	private Button save = new Button("Save", FontAwesome.SAVE);
	private Button cancel = new Button("Cancel");
	private Button delete = new Button("Delete", FontAwesome.TRASH_O);
	private CssLayout actions = new CssLayout(save, cancel, delete);

	@Autowired
	public ProductFamilyEditor() {

		addComponents(name, code, actions);

		// Add behavior to menu buttons
		save.addClickListener(e -> performSave(productFamily));
		delete.addClickListener(e -> performDelete(productFamily));

		// TODO ???
		// cancel.addClickListener(e -> editUser(userDto));
		setVisible(false);

	}

	private Object performSave(ProductFamily productFamily2) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object performDelete(ProductFamily productFamily2) {
		// TODO Auto-generated method stub
		return null;
	}

	private void styleComponents() {
		actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
	}
}
