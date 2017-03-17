package com.calogardev.pizzarella.view;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.dialogs.ConfirmDialog;

import com.calogardev.pizzarella.dto.UserDto;
import com.calogardev.pizzarella.service.UserService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = UsersView.VIEW_ROUTE)
@UIScope
public class UsersView extends VerticalLayout implements View {

	private static final Logger log = LoggerFactory.getLogger(UsersView.class);
	private static final long serialVersionUID = 7020396938463815254L;
	public static final String VIEW_ROUTE = "users";
	public static final String VIEW_NAME = "Users";

	private Grid<UserDto> grid;

	@Autowired
	private UserService userService;

	@PostConstruct
	void init() {
		Page.getCurrent().setTitle(VIEW_NAME);
		Label title = new Label(VIEW_NAME);
		title.addStyleName(ValoTheme.LABEL_H1);
		addComponent(title);

		grid = new Grid<UserDto>();
		addComponent(grid);
		grid.setWidth("100%");
		grid.setItems(userService.findAll());
		grid.addColumn(UserDto::getName).setCaption("Name");
		grid.addColumn(UserDto::getSurnames).setCaption("Surnames");
		grid.addColumn(UserDto::getNickname).setCaption("Nickname");
		grid.addColumn(UserDto::getDni).setCaption("DNI");

		// Used to render a button in a column
		ButtonRenderer<UserDto> deleteButton = new ButtonRenderer<UserDto>(clickEvent -> {
			ConfirmDialog.show(UI.getCurrent(), "Are you sure?", new ConfirmDialog.Listener() {

				@Override
				public void onClose(ConfirmDialog dialog) {
					if (dialog.isConfirmed()) {
						// Confirmed to continue
						String dni = clickEvent.getItem().getDni();
						userService.deleteByDni(dni);
						grid.setItems(userService.findAll());
					}
				}
			});
		});

		grid.addColumn(userDto -> "Delete", deleteButton);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}
}
