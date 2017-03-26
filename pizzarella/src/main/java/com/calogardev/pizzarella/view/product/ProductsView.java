package com.calogardev.pizzarella.view.product;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = ProductsView.VIEW_ROUTE)
@UIScope
public class ProductsView extends VerticalLayout implements View {

    private static final long serialVersionUID = -8090435519281970500L;

    public static final String VIEW_NAME = "Products";
    public static final String VIEW_ROUTE = "products";

    @PostConstruct
    public void init() {
	commonsSettings();

	setSizeFull();

	// Create the view options, like creating new products

	HorizontalLayout options = new HorizontalLayout();
	options.setWidth("100%");

	addComponent(options);

	Button createButton = new Button("Create new Product", new ClickListener() {
	    @Override
	    public void buttonClick(ClickEvent event) {
		UI.getCurrent().getNavigator().navigateTo(CreateProductView.VIEW_ROUTE);
	    }
	});
	// Using an empty expanding label to align the button to the right
	Label spacer = new Label();
	options.addComponent(spacer);
	options.setExpandRatio(spacer, 1f);
	options.addComponent(createButton);
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

    @Override
    public void enter(ViewChangeEvent event) {
	// TODO Auto-generated method stub

    }
}
