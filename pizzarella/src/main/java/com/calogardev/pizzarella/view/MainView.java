package com.calogardev.pizzarella.view;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = MainView.VIEW_ROUTE)
@UIScope
public class MainView extends VerticalLayout implements View {

	private static final long serialVersionUID = -6924831925888760542L;
	public static final String VIEW_ROUTE = "home";
	public static final String VIEW_NAME = "Home";

	@PostConstruct
	void init() {
		// TODO: do this with AOP?
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
