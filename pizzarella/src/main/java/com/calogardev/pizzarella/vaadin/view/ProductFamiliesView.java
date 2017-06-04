package com.calogardev.pizzarella.vaadin.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.calogardev.pizzarella.model.ProductFamily;
import com.calogardev.pizzarella.service.ProductFamilyService;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = ProductFamiliesView.VIEW_ROUTE)
@UIScope
public class ProductFamiliesView extends VerticalLayout implements View {

    private static final long serialVersionUID = 6741359982123207924L;

    public static final String VIEW_ROUTE = "productFamilies";
    public static final String VIEW_NAME = "Product Families";

    @Autowired
    private ProductFamilyService pfService;

    @Autowired
    private ProductFamilyEditor editor;

    private Grid<ProductFamily> grid = new Grid<ProductFamily>();

    @PostConstruct
    public void init() {
	commonSettings();

	Button addNewButton = new Button("New product family", VaadinIcons.PLUS_CIRCLE_O);
	addNewButton.addClickListener(e -> editor.edit(new ProductFamily()));
	HorizontalLayout menu = new HorizontalLayout(addNewButton);

	grid.setItems(pfService.findAll());
	// Connect selected pf to editor
	grid.asSingleSelect().addValueChangeListener(e -> {
	    editor.edit(e.getValue());
	});

	addComponents(menu, grid);
	styleComponents();
    }

    private void styleComponents() {
	setSizeFull();
	grid.setSizeFull();
	setExpandRatio(grid, 1);
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
    }
}
