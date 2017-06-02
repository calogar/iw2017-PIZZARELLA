package com.calogardev.pizzarella.vaadin.view;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.dialogs.ConfirmDialog;

import com.calogardev.pizzarella.dto.ProductFamilyDto;
import com.calogardev.pizzarella.service.ProductFamilyService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = ProductFamiliesView.VIEW_ROUTE)
@UIScope
public class ProductFamiliesView extends VerticalLayout implements View {

    private static final long serialVersionUID = 6741359982123207924L;

    public static final String VIEW_ROUTE = "productFamilies";
    public static final String VIEW_NAME = "Product Families";

    @Autowired
    private ProductFamilyService familyService;

    private Grid<ProductFamilyDto> grid;

    @PostConstruct
    public void init() {
	commonsSettings();

	setSizeFull();

	// Create the view options, like creating new product families

	HorizontalLayout options = new HorizontalLayout();
	options.setWidth("100%");

	addComponent(options);

	Button createButton = new Button("Create new Product Family", new ClickListener() {
	    @Override
	    public void buttonClick(ClickEvent event) {
		UI.getCurrent().getNavigator().navigateTo(CreateProductFamilyView.VIEW_ROUTE);
	    }
	});
	// Using an empty expanding label to align the button to the right
	// Todo: create new custom component
	Label spacer = new Label();
	options.addComponent(spacer);
	options.setExpandRatio(spacer, 1f);
	options.addComponent(createButton);

	// Create the grid

	grid = new Grid<ProductFamilyDto>();
	grid.setSizeFull();
	addComponent(grid);
	setExpandRatio(grid, 1);

	List<ProductFamilyDto> families = familyService.findAll();
	grid.setItems(families);
	buildColumns();

	ButtonRenderer<ProductFamilyDto> deleteButton = new ButtonRenderer<ProductFamilyDto>(clickEvent -> {
	    ConfirmDialog.show(UI.getCurrent(), "Are you sure?", new ConfirmDialog.Listener() {

		@Override
		public void onClose(ConfirmDialog dialog) {
		    if (dialog.isConfirmed()) {
			// Confirmed to continue
			String code = clickEvent.getItem().getCode();
			familyService.deleteByCode(code);
			grid.setItems(familyService.findAll());
		    }
		}
	    });
	});
	grid.addColumn(userDto -> "Delete", deleteButton);

    }

    /**
     * Apply common setting to the page. this will be refactored in the future.
     */
    private void commonsSettings() {
	Page.getCurrent().setTitle(VIEW_NAME);
	Label title = new Label(VIEW_NAME);
	title.addStyleName(ValoTheme.LABEL_H1);
	addComponent(title);
    }

    /**
     * Declares which columns are going to be displayed.
     */
    private void buildColumns() {
	grid.addColumn(ProductFamilyDto::getName).setCaption("Name");
	grid.addColumn(ProductFamilyDto::getCode).setCaption("Code");
    }

    @Override
    public void enter(ViewChangeEvent event) {
	// TODO Auto-generated method stub

    }
}
