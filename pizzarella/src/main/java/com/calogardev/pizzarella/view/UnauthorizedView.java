package com.calogardev.pizzarella.view;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = UnauthorizedView.VIEW_ROUTE)
@UIScope
public class UnauthorizedView extends CssLayout implements View {

	private static final long serialVersionUID = 4364648376796064319L;
	public static final String VIEW_NAME = "Unauthorized";
	public static final String VIEW_ROUTE = "";

	@PostConstruct
	public void init() {

		Page.getCurrent().setTitle(VIEW_NAME);
		Label title = new Label(VIEW_NAME);
		title.addStyleName(ValoTheme.LABEL_FAILURE);
		addComponent(title);
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}
}
