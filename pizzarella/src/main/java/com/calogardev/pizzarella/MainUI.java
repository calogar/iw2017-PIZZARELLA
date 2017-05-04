package com.calogardev.pizzarella;

import org.springframework.beans.factory.annotation.Autowired;

import com.calogardev.pizzarella.view.DashboardLayout;
import com.calogardev.pizzarella.view.LoginView;
import com.calogardev.pizzarella.view.MainView;
import com.calogardev.pizzarella.view.order.OrdersView;
import com.calogardev.pizzarella.view.product.ProductsView;
import com.calogardev.pizzarella.view.productfamily.ProductFamiliesView;
import com.calogardev.pizzarella.view.user.UsersView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

@Theme("valo")
@SpringUI
@SpringViewDisplay
public class MainUI extends UI implements ViewDisplay {

	DashboardLayout root; // Custom dashboard-like layout
	ComponentContainer viewDisplay;
	Navigator navigator;

	// Automatically handles the views for the navigator
	@Autowired
	private SpringViewProvider viewProvider;
	private SecurityService securityService;

	@Override
	protected void init(VaadinRequest request) {
		Responsive.makeResponsive(this);
		root = new DashboardLayout();
		setContent(root);
		getPage().setTitle("Pizzarella");
		root.setWidth("100%"); // TODO: What happens if we use setFullWidth?

		navigator = root.getNavigator();
		navigator.addProvider(viewProvider);
		root.addMenuItem(MainView.VIEW_ROUTE, MainView.VIEW_NAME);
		root.addMenuItem(OrdersView.VIEW_ROUTE, OrdersView.VIEW_NAME);
		root.addMenuItem(UsersView.VIEW_ROUTE, UsersView.VIEW_NAME);
		root.addMenuItem(ProductsView.VIEW_ROUTE, ProductsView.VIEW_NAME);
		root.addMenuItem(ProductFamiliesView.VIEW_ROUTE, ProductFamiliesView.VIEW_NAME);
		root.addMenuItem(LoginView.VIEW_ROUTE, LoginView.VIEW_NAME);

		root.build();
	}

	/**
	 * Method used inside the login view by using @FunctionalInterface. It's on
	 * MainUI so it can render different views depending on the result.
	 * 
	 * @param username
	 * @param password
	 * @return boolean result of the login
	 */
	private boolean login(String username, String password) {
		if (securityService.login(username, password)) {
			renderMainView();
			return true;
		}
		return false;
	}

	}

	private void renderMainView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showView(View view) {
	}
}
