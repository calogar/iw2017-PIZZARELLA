package com.calogardev.pizzarella.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.calogardev.pizzarella.dao.ProductService;
import com.calogardev.pizzarella.dto.ProductDto;
import com.calogardev.pizzarella.service.ProductFamilyService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = CreateProductView.VIEW_ROUTE)
@UIScope
public class CreateProductView extends VerticalLayout implements View {

    private static final long serialVersionUID = -3859786965418555283L;

    public static final String VIEW_ROUTE = "/products/new";
    public static final String VIEW_NAME = "Create new Product";

    private ProductForm form;

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductFamilyService familyService;

    @PostConstruct
    public void init() {
	commonSettings();

	form = new ProductForm(new ProductDto(), productService, familyService);
	addComponent(form);
    }

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
