package com.calogardev.pizzarella.view.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.calogardev.pizzarella.dto.CreateUserDto;
import com.calogardev.pizzarella.service.UserService;
import com.calogardev.pizzarella.view.Form;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.FormLayout;

/**
 * Uses the (custom) Form API to build a form for Users
 * 
 * @author calogar
 *
 */
@SpringComponent
@ViewScope
public class UserForm extends FormLayout {

    private static final long serialVersionUID = -2051066380290976328L;

    @Autowired
    private Form<CreateUserDto> form;

    @Autowired
    private UserService userService;

    // Can't be autowired because it's from DB when editing
    private CreateUserDto dto;

    public UserForm() {
    }

    public void build(CreateUserDto dto) {
	this.dto = dto;

	form.configure(dto, CreateUserDto.class, userService);
	form.addTextField("name");
	form.addTextField("surnames");
	form.addTextField("nickname");
	form.addTextField("dni");
	form.addPasswordField("password", "Confirm password");
	form.build();

	addComponent(form);
    }
}
