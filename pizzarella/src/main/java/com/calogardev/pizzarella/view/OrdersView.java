package com.calogardev.pizzarella.view;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = OrdersView.VIEW_ROUTE)
@UIScope
public class OrdersView extends VerticalLayout implements View {

	private static final Logger log = LoggerFactory.getLogger(OrdersView.class);
	private static final long serialVersionUID = 3427287639695107332L;
	public static final String VIEW_ROUTE = "orders";
	public static final String VIEW_NAME = "Orders";

	@PostConstruct
	void init() {
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
