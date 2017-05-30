package com.calogardev.pizzarella.view.order;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.calogardev.pizzarella.dto.OrderDto;
import com.calogardev.pizzarella.enums.OrderStatus;
import com.calogardev.pizzarella.service.OrderService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = OrdersView.VIEW_ROUTE)
@UIScope
public class OrdersView extends VerticalLayout implements View {

    private static final long serialVersionUID = -6578065161603628034L;

    public static final String VIEW_NAME = "Orders";
    public static final String VIEW_ROUTE = "orders";

    @Autowired
    OrderService orderService;

    static OrderService staticOrderService;

    @Autowired
    private OrderEditor orderEditor;

    // Contains all the OrdesView screen login so we can set it visible or not
    private HorizontalLayout displayOrders = new HorizontalLayout();
    private static Grid<OrderDto> openOrdersGrid = new Grid<OrderDto>();
    private static Grid<OrderDto> kitchenOrdersGrid = new Grid<OrderDto>();
    private static Grid<OrderDto> closedOrdersGrid = new Grid<OrderDto>();
    private HorizontalLayout menu = new HorizontalLayout();

    private static Label totalIncomesLabel = new Label();

    @PostConstruct
    void init() {
	staticOrderService = orderService;

	commonSettings();

	buildMenu();

	VerticalLayout openLayout = buildGrid(openOrdersGrid, "Open orders");
	VerticalLayout kitchenLayout = buildGrid(kitchenOrdersGrid, "Sent to kitchen");
	VerticalLayout closedLayout = buildGrid(closedOrdersGrid, "Closed orders");
	displayOrders.addComponents(openLayout, kitchenLayout, closedLayout);
	addComponents(menu, displayOrders);

	addComponent(orderEditor);

	openOrdersGrid.setSelectionMode(SelectionMode.SINGLE);
	kitchenOrdersGrid.setSelectionMode(SelectionMode.SINGLE);
	closedOrdersGrid.setSelectionMode(SelectionMode.NONE);

	openOrdersGrid.addItemClickListener(e -> {
	    orderService.updateStatus(e.getItem().getId(), OrderStatus.SENT_TO_KITCHEN);
	    refreshGrids();
	});

	kitchenOrdersGrid.addItemClickListener(e -> {
	    orderService.updateStatus(e.getItem().getId(), OrderStatus.CLOSED);
	    refreshGrids();
	});

	totalIncomesLabel.addStyleName(ValoTheme.LABEL_H2);
    }

    private void createOrder(OrderDto orderDto) {
	// Call the editor (it will display itself)
	orderEditor.editOrder(orderDto);
	// getUI().getNavigator().navigateTo(CreateOrderView.VIEW_ROUTE);
    }

    private VerticalLayout buildGrid(Grid<OrderDto> grid, String titleStr) {

	Label title = new Label(titleStr);
	title.addStyleName(ValoTheme.LABEL_H2);

	// grid.addColumn(OrderDto::formatProducts).setCaption("Products");
	// grid.addColumn(OrderDto::getOrderedAt, new DateRenderer("%1$tB %1$te,
	// %1$tY", Locale.ENGLISH))
	// .setCaption("Order Time");
	// grid.addColumn(OrderDto::getTotalPrice).setCaption("Price");
	// // TODO create a custom getter that displays the place and table if
	// // local user or the client info if not
	// //
	// grid.addColumn(OrderDto::getFormattedLocation).setCaption("Location");
	// grid.addColumn(OrderDto::getNotes).setCaption("Notes");
	// grid.setHeight("250px");
	// grid.setWidth("100%");

	grid.addColumn(OrderDto::formatProducts).setCaption("Products");
	grid.addColumn(OrderDto::getTotalPrice).setCaption("Price");
	grid.setWidth("250px");

	return new VerticalLayout(title, grid);
    }

    private void buildMenu() {

	Button createOrder = new Button("New order", FontAwesome.PLUS);
	createOrder.addClickListener(e -> createOrder(new OrderDto()));
	menu.addComponent(createOrder);
	refreshTotalIncomes();
	totalIncomesLabel.setStyleName(ValoTheme.LABEL_H2);
	menu.addComponent(totalIncomesLabel);
    }

    private void commonSettings() {
	Page.getCurrent().setTitle(VIEW_NAME);
	Label title = new Label(VIEW_NAME);
	title.addStyleName(ValoTheme.LABEL_H1);
	addComponent(title);
    }

    public static void refreshGrids() {
	openOrdersGrid.setItems(staticOrderService.findAllWithStatus(OrderStatus.OPEN));
	kitchenOrdersGrid.setItems(staticOrderService.findAllWithStatus(OrderStatus.SENT_TO_KITCHEN));
	closedOrdersGrid.setItems(staticOrderService.findAllWithStatus(OrderStatus.CLOSED));
    }

    public static void refreshTotalIncomes() {
	Float totalIncomes = staticOrderService.getTotalIncomes();
	totalIncomesLabel.setCaption("Total incomes: " + totalIncomes + "€");
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }
}