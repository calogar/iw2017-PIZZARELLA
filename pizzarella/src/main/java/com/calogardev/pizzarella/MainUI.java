package com.calogardev.pizzarella;

import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;

import com.calogardev.pizzarella.service.SecurityService;
import com.calogardev.pizzarella.view.ErrorView;
import com.calogardev.pizzarella.view.UnauthorizedView;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;

/**
 * The main UI of the project. Handles Screens, which are not Spring Views in
 * order to integrate them with Spring Security.
 * 
 * @author calogar
 *
 */
@Viewport("user-scalable=no,initial-scale=1.0")
@Theme("valo")
@SpringUI
// @SpringViewDisplay
@PreserveOnRefresh
public class MainUI extends UI {

    private static final long serialVersionUID = -330824958198789601L;

    @Autowired
    private SpringViewProvider viewProvider;

    @Autowired
    private MainScreen mainScreen;

    // TODO Add this in the security service
    // @Autowired
    // private AuthenticationManager authenticationManager;

    @Autowired
    private SecurityService securityService;

    @Override
    protected void init(VaadinRequest request) {

	Responsive.makeResponsive(this);
	getPage().setTitle("Pizzarella");

	getUI().getNavigator().setErrorView(ErrorView.class);
	viewProvider.setAccessDeniedViewClass(UnauthorizedView.class);

	renderMainScreen();

	/*
	 * if (securityService.isLoggedIn()) { renderMainScreen(); } else {
	 * renderLoginScreen(); }
	 */
    }

    /**
     * Method used inside the login view by using @FunctionalInterface. It's on
     * MainUI so it can render different views depending on the result.
     * 
     * @param username
     * @param password
     * @return boolean result of the login
     */
    private boolean renderMainIfLogin(String username, String password) {

	if (securityService.login(username, password)) {
	    renderMainScreen();
	    return true;
	}
	return false;
    }

    private void renderMainScreen() {
	setContent(mainScreen);
    }

    private void renderLoginScreen() {
	setContent(new LoginScreen(this::renderMainIfLogin));
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = true)
    public static class MyUIServlet extends VaadinServlet {
	private static final long serialVersionUID = -742708883191539430L;
    }
}
