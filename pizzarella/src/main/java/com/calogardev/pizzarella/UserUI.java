package com.calogardev.pizzarella;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.calogardev.pizzarella.dto.UserDto;
import com.calogardev.pizzarella.service.UserService;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;

// @SpringUI
// @Theme("valo")
public class UserUI {
//
//	private static final long serialVersionUID = 5394044054477412008L;
//
//	private static final Logger log = LoggerFactory.getLogger(UserUI.class);
//	
//	private UserService userService;
//	
//	private Grid<UserDto> grid;
//
//	@Autowired
//	public UserUI(UserService userService) {
//	    this.userService = userService;
//	    this.grid = new Grid<>(UserDto.class);
//	}
//	
//	@Override
//	protected void init(VaadinRequest request) {
//		setContent(grid);
//		listUsers();
//	}
//
//	private void listUsers() {
//		if(grid == null) {
//			grid = new Grid<>(UserDto.class);
//		} else {
//			log.info("Setting data to grid");
//			grid.setItems(userService.findAll());
//		}
//	}
}
