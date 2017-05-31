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
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.DateRenderer;
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
	private Grid<OrderDto> ordersGrid = new Grid<OrderDto>();
	private HorizontalLayout menu = new HorizontalLayout();
	private HorizontalLayout submenu = new HorizontalLayout();

	Button createOrder = new Button("New order", FontAwesome.PLUS);
	Button calculateIncomes = new Button();

	@PostConstruct
	void init() {
		staticOrderService = orderService;

		commonSettings();
		buildMenu();
		buildTotalIncomes();

		Label subtitle = new Label("Click on the 'Status' tab to rearrange orders");
		subtitle.addStyleName(ValoTheme.LABEL_H2);
		buildGrid();

		addComponents(menu, subtitle, ordersGrid, submenu);

		orderEditor.setViewReference(this);
		addComponent(orderEditor);

		// ordersGrid.addItemClickListener(e -> {
		// orderService.updateStatus(e.getItem().getId(),
		// OrderStatus.SENT_TO_KITCHEN);
		// refreshGrids();
		// });
		//
		// kitchenOrdersGrid.addItemClickListener(e -> {
		// orderService.updateStatus(e.getItem().getId(), OrderStatus.CLOSED);
		// refreshGrids();
		// });

	}

	private void createOrder(OrderDto orderDto) {
		// Call the editor (it will display itself)
		orderEditor.editOrder(orderDto);
		// getUI().getNavigator().navigateTo(CreateOrderView.VIEW_ROUTE);
	}

	private void buildGrid() {
		ordersGrid.addColumn(OrderDto::formatOrderStatus).setCaption("Status");

		ordersGrid.addColumn(OrderDto::formatProducts).setCaption("Products");
		ordersGrid.addColumn(OrderDto::getOrderedAt, new DateRenderer("%1$tB %1$te,%1$tY", Locale.ENGLISH))
				.setCaption("Order Time");
		ordersGrid.addColumn(OrderDto::getTotalPrice).setCaption("Price");
		// ordersGrid.addColumn(OrderDto::getFormattedLocation).setCaption("Location");
		ordersGrid.addColumn(OrderDto::getType).setCaption("Type");
		ordersGrid.addColumn(OrderDto::getPlace).setCaption("Place");
		ordersGrid.addColumn(OrderDto::getTableNumber).setCaption("Table number");
		ordersGrid.addColumn(OrderDto::getTelephone).setCaption("Telephone");

		ordersGrid.setWidth("100%");
		ordersGrid.setSelectionMode(SelectionMode.SINGLE);

		ordersGrid.setItems(orderService.findAll());
		ordersGrid.addItemClickListener(e -> {
			orderEditor.editOrder(e.getItem());
		});
	}

	private void buildMenu() {

		createOrder.addClickListener(e -> createOrder(new OrderDto()));
		calculateIncomes.addClickListener(e -> {
			buildTotalIncomes();
		});
		menu.addComponents(createOrder, calculateIncomes);
	}

	private void buildTotalIncomes() {
		calculateIncomes.setCaption("Calculate total incomes: " + orderService.calculateTotalIncomes());
	}

	private void commonSettings() {
		Page.getCurrent().setTitle(VIEW_NAME);
		Label title = new Label(VIEW_NAME);
		title.addStyleName(ValoTheme.LABEL_H1);
		addComponent(title);
	}

	public void refreshGrid() {
		ordersGrid.setItems(staticOrderService.findAll());
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}
}