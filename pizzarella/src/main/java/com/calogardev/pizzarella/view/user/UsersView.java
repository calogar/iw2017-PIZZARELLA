package com.calogardev.pizzarella.view.user;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.calogardev.pizzarella.dto.UserDto;
import com.calogardev.pizzarella.service.UserService;
import com.calogardev.pizzarella.view.UserEditor;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
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

    @Autowired
    private UserEditor userEditor;

    private TextField filterBySurnames;

    @PostConstruct
    void init() {
	commonsSettings();
	setSizeFull();

	Button addNewButton = new Button("New user", FontAwesome.PLUS);
	addNewButton.addClickListener(e -> userEditor.editUser(new UserDto()));

	buildFilterBySurnames();
	HorizontalLayout menu = new HorizontalLayout(addNewButton, filterBySurnames);
	addComponent(menu);

	// Build the grid
	buildGrid();

	addComponent(userEditor);

	// Populate grid with all the data
	listUsers(null);
    }

    /**
     * Apply common setting to the page. this will be refactored in the future.
     */
    private void commonsSettings() {
	Page.getCurrent().setTitle(VIEW_NAME);
	Label title = new Label(VIEW_NAME);
	title.addStyleName(ValoTheme.LABEL_H1);
	addComponent(title);
    }

    private void buildGrid() {
	grid = new Grid<UserDto>();
	grid.setHeight(300, Unit.PIXELS);
	grid.setSizeFull();
	addComponent(grid);
	setExpandRatio(grid, 1);
	grid.setItems(userService.findAll());
	buildColumns();

	// Connect selected User to editor or hide if none is selected
	grid.asSingleSelect().addValueChangeListener(e -> {
	    userEditor.editUser(e.getValue());
	});
    }

    /**
     * Declares which columns are going to be displayed.
     */
    private void buildColumns() {
	grid.addColumn(UserDto::getName).setCaption("Name");
	grid.addColumn(UserDto::getSurnames).setCaption("Surnames");
	grid.addColumn(UserDto::getNickname).setCaption("Username");
	grid.addColumn(UserDto::getDni).setCaption("DNI");
    }

    private void buildFilterBySurnames() {
	filterBySurnames = new TextField();
	filterBySurnames.setPlaceholder("Filter by surname");
	filterBySurnames.setValueChangeMode(ValueChangeMode.LAZY);
	filterBySurnames.addValueChangeListener(e -> listUsers(e.getValue()));

	// Listen changes made by the editor, refresh data from backend
	userEditor.setChangeHandler(() -> {
	    userEditor.setVisible(false);
	    listUsers(filterBySurnames.getValue());
	});
    }

    // TODO: add querydsl to implement filtering
    private void listUsers(String filterText) {
	//
	// if (StringUtils.isEmpty(filterText)) {
	// grid.setItems(userService.findAll());
	// } else {
	// grid.setItems(userService.findByLastNameStartsWithIgnoreCase(filterText));
	// }
	grid.setItems(userService.findAll());

    }

    @Override
    public void enter(ViewChangeEvent event) {
	// TODO Auto-generated method stub

    }
}
