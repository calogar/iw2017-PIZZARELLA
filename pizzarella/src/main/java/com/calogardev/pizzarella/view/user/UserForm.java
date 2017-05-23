package com.calogardev.pizzarella.view.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.calogardev.pizzarella.dto.UserDto;
import com.calogardev.pizzarella.service.UserService;
import com.calogardev.pizzarella.view.GridForm;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.CssLayout;

/**
 * Uses the (custom) Form API to build a form for Users
 * 
 * @author calogar
 *
 */
@SpringComponent
@ViewScope
public class UserForm extends CssLayout {

    private static final long serialVersionUID = -2051066380290976328L;

    @Autowired
    private UserService userService;

    private GridForm<UserDto> form;

    public UserForm(UserDto dto) {

	form = new GridForm(dto, UserDto.class, userService, 4, 2);

	form.addTextField("name", "Name", 0, 0);
	form.addTextField("surnames", "Surnames", 1, 0);
	form.addTextField("nickname", "Username", 2, 0);
	form.addTextField("dni", "DNI", 3, 0);
	form.addPasswordField("password", "Confirm password", 0, 1, 1, 1);
	form.addSaveButton(2, 1);

	addComponent(form.getForm());
    }
}
