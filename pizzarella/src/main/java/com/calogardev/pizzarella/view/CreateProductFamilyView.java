package com.calogardev.pizzarella.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.calogardev.pizzarella.dto.ProductFamilyDto;
import com.calogardev.pizzarella.service.ProductFamilyService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = CreateProductFamilyView.VIEW_ROUTE)
@UIScope
public class CreateProductFamilyView extends VerticalLayout implements View {

    private static final long serialVersionUID = -1487637267713557085L;

    public static final String VIEW_ROUTE = "product_family/new";
    public static final String VIEW_NAME = "Create new Product Family";

    private ProductFamilyForm form;

    @Autowired
    private ProductFamilyService productFamilyService;

    @PostConstruct
    public void init() {
	commonSettings();

	form = new ProductFamilyForm(new ProductFamilyDto(), productFamilyService);
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
