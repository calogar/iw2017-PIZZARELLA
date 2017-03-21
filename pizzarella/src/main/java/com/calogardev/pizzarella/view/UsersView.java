package com.calogardev.pizzarella.view;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.dialogs.ConfirmDialog;

import com.calogardev.pizzarella.dto.UserDto;
import com.calogardev.pizzarella.service.UserService;
import com.vaadin.data.Binder;
import com.vaadin.data.Binder.Binding;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
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
	commonsSettings();
	setSizeFull();

	// Create the view options, like creating new users

	HorizontalLayout options = new HorizontalLayout();
	options.setWidth("100%");
	addComponent(options);

	Button createUserButton = new Button("Create new User", new ClickListener() {
	    @Override
	    public void buttonClick(ClickEvent event) {
		UI.getCurrent().getNavigator().navigateTo(CreateUserView.VIEW_ROUTE);
	    }
	});
	// Using an empty expanding label to align the button to the right
	Label spacer = new Label();
	options.addComponent(spacer);
	options.setExpandRatio(spacer, 1f);
	options.addComponent(createUserButton);

	// Create the grid

	grid = new Grid<UserDto>();
	grid.setSizeFull();
	addComponent(grid);
	setExpandRatio(grid, 1);

	List<UserDto> users = userService.findAll();
	grid.setItems(users);
	buildColumns();

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

    /**
     * Apply common setting to the page. this will be refactored in the future.
     */
    private void commonsSettings() {
	Page.getCurrent().setTitle(VIEW_NAME);
	Label title = new Label(VIEW_NAME);
	title.addStyleName(ValoTheme.LABEL_H1);
	addComponent(title);
    }

    /**
     * Declares which columns are going to be displayed.
     */
    private void buildColumns() {
	grid.addColumn(UserDto::getName).setCaption("Name");
	grid.addColumn(UserDto::getSurnames).setCaption("Surnames");
	grid.addColumn(UserDto::getNickname).setCaption("Nickname");
	grid.addColumn(UserDto::getDni).setCaption("DNI");
    }

    /**
     * An alternative version of buildColumns that allows for live editing.
     * However, it currently uses set methods to save attributes, so it hasn't
     * support for validations, with are in the service.
     */
    private void buildEditableColumns() {
	Binder<UserDto> binder = grid.getEditor().getBinder();

	Column<UserDto, String> column;
	Binding<UserDto, String> doneBinding;

	// Name
	doneBinding = binder.bind(new TextField(), UserDto::getName, UserDto::setName);
	column = grid.addColumn(user -> user.getName());
	column.setEditorBinding(doneBinding).setCaption("Name");

	// Surnames
	doneBinding = binder.bind(new TextField(), UserDto::getSurnames, UserDto::setSurnames);
	column = grid.addColumn(user -> user.getSurnames());
	column.setEditorBinding(doneBinding).setCaption("Surnames");

	// Dni
	doneBinding = binder.bind(new TextField(), UserDto::getDni, UserDto::setDni);
	column = grid.addColumn(user -> user.getDni());
	column.setEditorBinding(doneBinding).setCaption("DNI");

	// Nickname
	doneBinding = binder.bind(new TextField(), UserDto::getNickname, UserDto::setNickname);
	column = grid.addColumn(user -> user.getNickname());
	column.setEditorBinding(doneBinding).setCaption("Nickname");

	// Must enable editing for it to work
	grid.getEditor().setEnabled(true);
    }

    @Override
    public void enter(ViewChangeEvent event) {
	// TODO Auto-generated method stub

    }
}
