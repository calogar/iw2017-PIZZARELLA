package com.calogardev.pizzarella.vaadin.view;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

//@SpringView(name = ErrorView.VIEW_ROUTE)
@Component
@UIScope
public class ErrorView extends CssLayout implements View {

	private static final long serialVersionUID = 4364648376796064319L;
	public static final String VIEW_NAME = "Error";
	public static final String VIEW_ROUTE = "Error";

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
