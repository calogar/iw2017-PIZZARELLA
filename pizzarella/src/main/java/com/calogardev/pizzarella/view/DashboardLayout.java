package com.calogardev.pizzarella.view;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Custom layout that provides a dashboard-like display.
 * 
 * @author calogar
 *
 */
public class DashboardLayout extends HorizontalLayout {

    private final static Logger log = LoggerFactory.getLogger(DashboardLayout.class);

    private CssLayout menuArea = new CssLayout();
    private CssLayout contentArea = new CssLayout();
    private CssLayout menu = new CssLayout();
    private CssLayout menuItemsLayout = new CssLayout();

    private Navigator navigator;

    private LinkedHashMap<String, String> menuItems = new LinkedHashMap<>();

    public DashboardLayout() {
	setSizeFull();
	setSpacing(false);

	menuArea.setPrimaryStyleName(ValoTheme.MENU_ROOT);
	contentArea.setPrimaryStyleName("valo-content"); // Like an html id
	contentArea.addStyleName("v-scrollable"); // Like an html class
	contentArea.setSizeFull();

	addComponents(menuArea, contentArea);
	setExpandRatio(contentArea, 1);
	menuArea.addComponent(menu);

	navigator = new Navigator(UI.getCurrent(), contentArea);
    }

    public void addMenuItem(String viewRoute, String viewName) {
	menuItems.put(viewRoute, viewName);
    }

    public void build() {
	buildNavigator();
	buildMenu();
    }

    private void buildMenu() {

	HorizontalLayout top = new HorizontalLayout();
	top.setWidth("100%");
	top.setSpacing(false);
	top.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
	top.addStyleName(ValoTheme.MENU_TITLE);
	menu.addComponent(top);
	// menuItemsLayout.addComponent(createThemeSelect());

	Button showMenu = new Button("Menu", new ClickListener() {
	    @Override
	    public void buttonClick(ClickEvent event) {
		if (menuItemsLayout.getStyleName().contains("valo-menu-visible")) {
		    menuItemsLayout.removeStyleName("valo-menu-visible");
		} else {
		    menuItemsLayout.addStyleName("valo-menu-visible");
		}
	    }
	});
	showMenu.addStyleName(ValoTheme.BUTTON_PRIMARY);
	showMenu.addStyleName(ValoTheme.BUTTON_SMALL);
	showMenu.addStyleName("valo-menu-toggle");
	// showMenu.setIcon(FontAwesome.LIST);
	menu.addComponent(showMenu);

	Label title = new Label("<h3>Vaadin <strong>Pizzarella</strong></h3>", ContentMode.HTML);
	title.setSizeUndefined();
	top.addComponent(title);
	top.setExpandRatio(title, 1);

	// TODO: Insert user menu display here

	menuItemsLayout.setPrimaryStyleName("valo-menuitems");
	menu.addComponent(menuItemsLayout);

	Label label = null;
	for (final Entry<String, String> item : menuItems.entrySet()) {
	    Button button = new Button(item.getValue(), new ClickListener() {
		@Override
		public void buttonClick(ClickEvent event) {
		    navigator.navigateTo(item.getKey());
		    log.info("Navigate to " + item.getKey());
		}
	    });
	    button.setCaptionAsHtml(true);
	    button.setPrimaryStyleName(ValoTheme.MENU_ITEM);
	    menuItemsLayout.addComponent(button);
	}
    }

    private void buildNavigator() {
	String f = Page.getCurrent().getUriFragment();
	if (f == null || f.equals("")) {
	    navigator.navigateTo(MainView.VIEW_ROUTE);
	}

	navigator.addViewChangeListener(new ViewChangeListener() {

	    @Override
	    public boolean beforeViewChange(ViewChangeEvent event) {
		return true;
	    }

	    @Override
	    public void afterViewChange(ViewChangeEvent event) {
		// Remove the selected class from all items
		for (Iterator<Component> it = menuItemsLayout.iterator(); it.hasNext();) {
		    it.next().removeStyleName("selected");
		}

		// Find the current selected view and highlight it
		for (Entry<String, String> item : menuItems.entrySet()) {
		    if (event.getViewName().equals(item.getKey())) {
			for (Iterator<Component> it = menuItemsLayout.iterator(); it.hasNext();) {
			    Component c = it.next();
			    if (c.getCaption() != null && c.getCaption().startsWith(item.getValue())) {
				c.addStyleName("selected");
				break;
			    }
			}
			break;
		    }
		}
		menu.removeStyleName("valo-menu-visible");
	    }
	});
    }

    public Navigator getNavigator() {
	return navigator;
    }

}
