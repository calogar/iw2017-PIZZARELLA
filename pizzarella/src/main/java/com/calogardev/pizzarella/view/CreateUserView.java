package com.calogardev.pizzarella.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.calogardev.pizzarella.dto.CreateUserDto;
import com.calogardev.pizzarella.service.UserService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = CreateUserView.VIEW_ROUTE)
@UIScope
public class CreateUserView extends VerticalLayout implements View {

	private static final long serialVersionUID = 7781582716883896923L;

	public static final String VIEW_ROUTE = "users/new";
	public static final String VIEW_NAME = "New User";

	// If we don't specify the Dto we are using, we cannot get its attributes
	private Form form;

	@Autowired
	UserService userService;

	@PostConstruct
	void init() {
		Page.getCurrent().setTitle(VIEW_NAME);
		Label title = new Label(VIEW_NAME);
		title.addStyleName(ValoTheme.LABEL_H1);
		addComponent(title);

		CreateUserDto user = new CreateUserDto();
		form = new Form(user, userService);
		form.addPassword("password", "Confirm Password");
		form.build();
		addComponent(form);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
