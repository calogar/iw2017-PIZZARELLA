package com.calogardev.pizzarella.view.user;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.calogardev.pizzarella.dto.UserDto;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * View used to create Users.
 * 
 * @author calogar
 *
 */
@SpringView(name = CreateUserView.VIEW_ROUTE)
@UIScope
public class CreateUserView extends VerticalLayout implements View {

    private static final long serialVersionUID = 7781582716883896923L;

    public static final String VIEW_ROUTE = "createUser";
    public static final String VIEW_NAME = "New User";

    @Autowired
    private UserForm userForm;

    @PostConstruct
    public void init() {
	commonSettings();

	addComponent(new Label("En construcción"));
	UserDto dto = new UserDto();
	// Delegates how the form is constructed to UserForm
	// userForm.build(dto);
	addComponent(userForm);
    }

    /**
     * Sets the page title and header
     */
    private void commonSettings() {
	Page.getCurrent().setTitle(VIEW_NAME);
	Label title = new Label(VIEW_NAME);
	title.addStyleName(ValoTheme.LABEL_H1);
	addComponent(title);
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }
}
