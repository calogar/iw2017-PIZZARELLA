package com.calogardev.pizzarella;

import javax.annotation.PostConstruct;

import com.calogardev.pizzarella.view.order.OrdersView;
import com.calogardev.pizzarella.view.product.ProductsView;
import com.calogardev.pizzarella.view.productfamily.ProductFamiliesView;
import com.calogardev.pizzarella.view.user.UsersView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.Responsive;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * The main Vaadin component (not view) that is used to render the app's view by
 * using ViewDisplay
 * 
 * @author calogar
 *
 */
@SpringViewDisplay
public class MainScreen extends VerticalLayout implements ViewDisplay {

	private static final long serialVersionUID = -6924831925888760542L;
	public static final String VIEW_ROUTE = "home";
	public static final String VIEW_NAME = "Home";

	// DashboardLayout root; // Custom dashboard-like layout

	// Where all the views are placed
	private Panel viewDisplayPanel;

	@Override
	public void attach() {
		super.attach();
		// Go to root
		this.getUI().getNavigator().navigateTo("");
	}

	@PostConstruct
	void init() {

		Responsive.makeResponsive(this);
		// getUI().getPage().setTitle("Pizzarella");

		final VerticalLayout root = new VerticalLayout();
		root.setSizeFull();

		// Create navigation bar
		final CssLayout navigationBar = new CssLayout();
		navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		navigationBar.addComponent(createNavButton(OrdersView.VIEW_NAME, OrdersView.VIEW_ROUTE));
		navigationBar.addComponent(createNavButton(ProductsView.VIEW_NAME, ProductsView.VIEW_ROUTE));
		navigationBar.addComponent(createNavButton(ProductFamiliesView.VIEW_NAME, ProductFamiliesView.VIEW_ROUTE));
		navigationBar.addComponent(createNavButton(UsersView.VIEW_NAME, UsersView.VIEW_ROUTE));

		// TODO add logout button

		root.addComponent(navigationBar);

		// Create the panel
		viewDisplayPanel = new Panel();
		viewDisplayPanel.setSizeFull();
		root.addComponent(viewDisplayPanel);
		root.setExpandRatio(viewDisplayPanel, 1.0f);

		addComponent(root);

		/*
		 * root = new DashboardLayout(); root.setWidth("100%");
		 * addComponent(root);
		 * 
		 * root.addMenuItem(MainScreen.VIEW_ROUTE, MainScreen.VIEW_NAME);
		 * root.addMenuItem(OrdersView.VIEW_ROUTE, OrdersView.VIEW_NAME);
		 * root.addMenuItem(UsersView.VIEW_ROUTE, UsersView.VIEW_NAME);
		 * root.addMenuItem(ProductsView.VIEW_ROUTE, ProductsView.VIEW_NAME);
		 * root.addMenuItem(ProductFamiliesView.VIEW_ROUTE,
		 * ProductFamiliesView.VIEW_NAME);
		 * root.addMenuItem(LoginScreen.VIEW_ROUTE, LoginScreen.VIEW_NAME);
		 * 
		 * root.build();
		 * 
		 */
	}

	/**
	 * Creates a navigation button that can be inserted in the sidebar.
	 * 
	 * @param caption
	 * @param viewName
	 * @return the navigation button
	 */
	private Button createNavButton(String caption, final String route) {
		Button button = new Button(caption);
		button.addStyleName(ValoTheme.BUTTON_SMALL);
		button.addClickListener(event -> getUI().getNavigator().navigateTo(route));
		return button;
	}

	/**
	 * From the ViewDisplay interface. Receives the view that must be displayed
	 * and adds it to the panel.
	 */
	@Override
	public void showView(View view) {
		viewDisplayPanel.setContent((Component) view);
	}
}
