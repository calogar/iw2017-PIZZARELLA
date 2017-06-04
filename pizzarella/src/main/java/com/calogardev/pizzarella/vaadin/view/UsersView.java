package com.calogardev.pizzarella.vaadin.view;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.calogardev.pizzarella.model.User;
import com.calogardev.pizzarella.service.UserService;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
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

    @Autowired
    private UserService userService;

    @Autowired
    private UserEditor userEditor;

    private Grid<User> grid = new Grid<User>();

    /* Action options */
    private TextField filterBySurnames = new TextField();
    private Button newButton = new Button("New user", VaadinIcons.PLUS_CIRCLE_O);
    private HorizontalLayout menu = new HorizontalLayout(newButton, filterBySurnames);

    @PostConstruct
    void init() {
	commonSettings();

	newButton.addClickListener(e -> userEditor.edit(new User()));
	buildFilterBySurnames();
	buildColumns();

	// Connect selected User to editor
	grid.asSingleSelect().addValueChangeListener(e -> {
	    userEditor.edit(e.getValue());
	});

	addComponents(menu, grid, userEditor);

	// Populate grid with all the data
	listUsers(null);
    }

    /**
     * Declares which columns are going to be displayed.
     */
    private void buildColumns() {
	grid.addColumn(User::getName).setCaption("Name");
	grid.addColumn(User::getSurnames).setCaption("Surnames");
	grid.addColumn(User::getUsername).setCaption("Username");
	grid.addColumn(User::getDni).setCaption("DNI");
    }

    private void buildFilterBySurnames() {
	filterBySurnames.setPlaceholder("Filter by surname");
	filterBySurnames.setValueChangeMode(ValueChangeMode.LAZY);
	filterBySurnames.addValueChangeListener(e -> listUsers(e.getValue()));

	// Listen changes made by the editor, refresh data from backend
	userEditor.setChangeHandler(() -> {
	    userEditor.setVisible(false);
	    listUsers(filterBySurnames.getValue());
	});
    }

    private void listUsers(String filterText) {

	if (StringUtils.isEmpty(filterText)) {
	    grid.setItems(userService.findAll());
	} else {
	    grid.setItems(userService.filterBySurnames(filterText));
	}
	grid.setItems(userService.findAll());

    }

    private void styleComponents() {
	setSizeFull();
	grid.setHeight(300, Unit.PIXELS);
	grid.setSizeFull();
	setExpandRatio(grid, 1);
	grid.setItems(userService.findAll());
    }

    /**
     * Apply common setting to the page. this will be refactored in the future.
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
