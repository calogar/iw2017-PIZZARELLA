package com.calogardev.pizzarella.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Custom layout that provides a dashboard-like display.
 * 
 * @author calogar
 *
 */
public class DashboardLayout extends HorizontalLayout {

    private static final long serialVersionUID = -7501921968953705052L;

    private final static Logger log = LoggerFactory.getLogger(DashboardLayout.class);

    private CssLayout menuArea;
    private VerticalLayout contentArea;
    private Panel contentPanel;
    private CssLayout menu;
    private CssLayout menuItems;

    public DashboardLayout(CssLayout navigationBar, Panel displayPanel) {
	setSizeFull();
	setSpacing(false);

	menuArea = new CssLayout();
	menuArea.setPrimaryStyleName(ValoTheme.MENU_ROOT);
	addComponent(menuArea);
	// setExpandRatio(menuArea, 1.0f);

	contentArea = new VerticalLayout();
	contentArea.setSizeFull();
	addComponent(contentArea);
	setExpandRatio(contentArea, 1.0f);

	contentPanel = displayPanel;
	contentArea.setSizeFull();
	contentPanel.setPrimaryStyleName("valo-content"); // Like an html id
	contentArea.addStyleName("v-scrollable"); // Like an html class
	contentArea.addComponent(contentPanel);
	contentArea.setExpandRatio(contentPanel, 1.0f);

	menu = new CssLayout();
	menuArea.addComponent(menu);

	// Add title to menu
	Layout top = createTopTitle("Pizzarella");
	menu.addComponent(top);

	menuItems = navigationBar;
	menu.addComponent(menuItems);
	menu.removeStyleName("valo-menu-visible");
	menuItems.setPrimaryStyleName("valo-menuitems");
    }

    private Layout createTopTitle(String title) {

	HorizontalLayout top = new HorizontalLayout();
	top.setWidth("100%");
	top.setSpacing(false);
	top.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
	top.addStyleName(ValoTheme.MENU_TITLE);

	Label titleLabel = new Label("<h3><strong>" + title + "</strong></h3>", ContentMode.HTML);
	titleLabel.setSizeUndefined();
	top.addComponent(titleLabel);
	top.setExpandRatio(titleLabel, 1);

	return top;
    }
}
