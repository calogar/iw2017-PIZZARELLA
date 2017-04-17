package com.calogardev.pizzarella.view.product;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.calogardev.pizzarella.dto.ProductDto;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * View used to create Products.
 * 
 * @author calogar
 *
 */
@SpringView(name = CreateProductView.VIEW_ROUTE)
@UIScope
public class CreateProductView extends VerticalLayout implements View {

    private static final long serialVersionUID = -7719274049830538566L;
    private static final Logger log = LoggerFactory.getLogger(CreateProductView.class);

    public static final String VIEW_NAME = "New Product";
    public static final String VIEW_ROUTE = "createProduct";

    @Autowired
    private ProductForm productForm;

    @PostConstruct
    public void init() {
	commonSettings();

	ProductDto dto = new ProductDto();
	productForm.build(dto);
	addComponent(productForm);
    }

    /**
     * Apply common setting to the page. this will be refactored in the future.
     */
    private void commonSettings() {
	Page.getCurrent().setTitle(VIEW_NAME);
	Label title = new Label(VIEW_NAME);
	title.addStyleName(ValoTheme.LABEL_H1);
	addComponent(title);
    }

    @Override
    public void enter(ViewChangeEvent event) {
	// TODO Auto-generated method stub

    }

}
