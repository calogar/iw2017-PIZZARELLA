package com.calogardev.pizzarella.view.order;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.calogardev.pizzarella.dto.OrderDto;
import com.calogardev.pizzarella.service.OrderService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.DateRenderer;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = OrdersView.VIEW_ROUTE)
@UIScope
public class AltOrdersView extends VerticalLayout implements View {

    private static final long serialVersionUID = -6578065161603628034L;

    public static final String VIEW_NAME = "Orders";
    public static final String VIEW_ROUTE = "orders";

    @Autowired
    OrderService orderService;

    @Autowired
    private OrderEditor orderEditor;

    // Contains all the OrdesView screen login so we can set it visible or not
    private VerticalLayout displayOrders = new VerticalLayout();
    private Grid<OrderDto> openOrdersGrid = new Grid<OrderDto>();
    private Grid<OrderDto> closedOrdersGrid = new Grid<OrderDto>();
    private HorizontalLayout menu = new HorizontalLayout();

    @PostConstruct
    void init() {

	commonSettings();

	buildMenu();

	// Build open orders
	Label openOrders = new Label("Open orders");
	openOrders.addStyleName(ValoTheme.LABEL_H1);
	buildGrid(openOrdersGrid);

	// Build closed orders
	Label closedOrders = new Label("Closed orders");
	closedOrders.addStyleName(ValoTheme.LABEL_H1);
	buildGrid(closedOrdersGrid);

	displayOrders.addComponents(menu, openOrders, openOrdersGrid, closedOrders, closedOrdersGrid);
	addComponent(displayOrders);
    }

    private void createOrder(OrderDto orderDto) {
	// Sets the displayOrders as not visible
	// Call the editor (it will display itself)
	orderEditor.editOrder(orderDto);
    }

    private void buildGrid(Grid<OrderDto> grid) {

	grid.addColumn(OrderDto::formatProducts).setCaption("Products");
	grid.addColumn(OrderDto::getOrderedAt, new DateRenderer("%1$tB %1$te, %1$tY", Locale.ENGLISH))
		.setCaption("Order Time");
	grid.addColumn(OrderDto::getTotalPrice).setCaption("Price");
	// TODO create a custom getter that displays the place and table if
	// local user or the client info if not
	// grid.addColumn(OrderDto::getFormattedLocation).setCaption("Location");
	grid.addColumn(OrderDto::getNotes).setCaption("Notes");
    }

    private void buildMenu() {

	Button createOrder = new Button("New order", FontAwesome.PLUS);
	createOrder.addClickListener(e -> createOrder(new OrderDto()));
	menu.addComponent(createOrder);

	Float totalIncomes = orderService.getTotalIncomes();
	Label totalIncomesLabel = new Label("Total incomes: " + totalIncomes + "€");
	menu.addComponent(totalIncomesLabel);
    }

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