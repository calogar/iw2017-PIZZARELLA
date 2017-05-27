package com.calogardev.pizzarella;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.calogardev.pizzarella.service.SecurityService;
import com.calogardev.pizzarella.view.DashboardLayout;
import com.calogardev.pizzarella.view.order.AltOrdersView;
import com.calogardev.pizzarella.view.product.ProductsView;
import com.calogardev.pizzarella.view.productfamily.ProductFamiliesView;
import com.calogardev.pizzarella.view.user.UsersView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.ValoTheme;

/**
 * The main Vaadin component (not view) that is used to render the app's view by
 * using ViewDisplay
 * 
 * @author calogar
 *
 */
@SpringViewDisplay
public class MainScreen extends HorizontalLayout implements ViewDisplay {

    private static final long serialVersionUID = -6924831925888760542L;
    public static final String VIEW_ROUTE = "home";
    public static final String VIEW_NAME = "Home";

    @Autowired
    private SecurityService securityService;

    DashboardLayout dashboard; // Custom dashboard-like layout

    // Where all the views are placed
    private Panel viewDisplayPanel;

    @Override
    public void attach() {
	super.attach();
	// Go to root
	this.getUI().getNavigator().navigateTo("home");
    }

    @PostConstruct
    void init() {
	// Create the panel
	viewDisplayPanel = new Panel();
    }

    // TODO for building mainscreen only when attached
    public void build() {
	setSizeFull();
	Responsive.makeResponsive(this);
	// getUI().getPage().setTitle("Pizzarella");

	// Create navigation bar
	final CssLayout navigationBar = new CssLayout();
	navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
	// navigationBar.addComponent(createNavButton(OrdersView.VIEW_NAME,
	// OrdersView.VIEW_ROUTE));
	navigationBar.addComponent(createNavButton(AltOrdersView.VIEW_NAME, AltOrdersView.VIEW_ROUTE));

	if (securityService.currentUserHasRole("ROLE_MANAGER")) {
	    Button managerOptions = new Button("MANAGER OPTIONS");
	    managerOptions.setPrimaryStyleName(ValoTheme.MENU_ITEM);
	    managerOptions.setEnabled(false);
	    navigationBar.addComponent(managerOptions);

	    navigationBar.addComponent(createNavButton(ProductsView.VIEW_NAME, ProductsView.VIEW_ROUTE));
	    navigationBar.addComponent(createNavButton(ProductFamiliesView.VIEW_NAME, ProductFamiliesView.VIEW_ROUTE));
	    navigationBar.addComponent(createNavButton(UsersView.VIEW_NAME, UsersView.VIEW_ROUTE));
	}

	Button logout = new Button("Log out", FontAwesome.SIGN_OUT);
	logout.addClickListener(e -> {
	    logout();
	});
	navigationBar.addComponent(logout);

	// Create the dashboard
	dashboard = new DashboardLayout(navigationBar, viewDisplayPanel);
	dashboard.setWidth("100%");

	addComponent(dashboard);
	// setExpandRatio(dashboard, 1.0f);
    }

    /**
     * Creates a navigation button that can be inserted in the sidebar.
     * 
     * @param caption
     * @param viewName
     * @return the navigation button
     */
    private Button createNavButton(String caption, final String route) {
	Button button = new Button(caption);
	button.setPrimaryStyleName(ValoTheme.MENU_ITEM);
	button.addClickListener(event -> getUI().getNavigator().navigateTo(route));
	return button;
    }

    private void logout() {
	securityService.logout();
	getSession().close();
	// SecurityContextHolder.getContext().setAuthentication(null);
	getUI().getPage().reload();
	// getUI().getNavigator().navigateTo("");

	MainUI ui = (MainUI) getUI();
	ui.renderLoginScreen();
    }

    /**
     * From the ViewDisplay interface. Receives the view that must be displayed
     * and adds it to the panel.
     */
    @Override
    public void showView(View view) {
	viewDisplayPanel.setContent((Component) view);
    }
}
