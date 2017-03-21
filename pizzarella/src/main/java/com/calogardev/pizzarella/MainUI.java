package com.calogardev.pizzarella;

import org.springframework.beans.factory.annotation.Autowired;

import com.calogardev.pizzarella.view.CreateUserView;
import com.calogardev.pizzarella.view.DashboardLayout;
import com.calogardev.pizzarella.view.MainView;
import com.calogardev.pizzarella.view.OrdersView;
import com.calogardev.pizzarella.view.UsersView;
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
		root.addMenuItem(CreateUserView.VIEW_ROUTE, CreateUserView.VIEW_NAME);

		root.build();
	}

	@Override
	public void showView(View view) {
		// TODO Auto-generated method stub

	}

}
