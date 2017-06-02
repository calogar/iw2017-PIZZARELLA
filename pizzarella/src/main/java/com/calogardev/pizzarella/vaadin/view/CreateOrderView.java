package com.calogardev.pizzarella.vaadin.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.calogardev.pizzarella.dto.OrderDto;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = CreateOrderView.VIEW_ROUTE)
@UIScope
public class CreateOrderView extends VerticalLayout implements View {

    private static final long serialVersionUID = 8742908360017512060L;
    public static final String VIEW_NAME = "New Order";
    public static final String VIEW_ROUTE = "newOrder";

    @Autowired
    private OrderEditor orderEditor;

    @PostConstruct
    void init() {
	Page.getCurrent().setTitle(VIEW_NAME);
	setSizeFull();

	orderEditor.editOrder(new OrderDto());
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }

}
