package com.calogardev.pizzarella.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.impl.GridBasedCrudComponent;

import com.calogardev.pizzarella.dao.ProductService;
import com.calogardev.pizzarella.dto.ProductDto;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = ProductCRUDView.VIEW_ROUTE)
@UIScope
public class ProductCRUDView extends VerticalLayout implements View {

	private static final long serialVersionUID = 7572387927545561600L;
	public static final String VIEW_ROUTE = "productsCRUD";
	public static final String VIEW_NAME = "Products";

	@Autowired
	private ProductService productService;

	@Override
	public void enter(ViewChangeEvent event) {

		GridBasedCrudComponent<ProductDto> crud = new GridBasedCrudComponent<>(ProductDto.class);
		addComponent(crud);

		crud.setFindAllOperation(() -> ProductService::findAll);
		// crud.setAddOperation(() -> ProductService::save);
		// crud.setUpdateOperation(() -> ProductService::save);
		// crud.setDeleteOperation(() -> ProductService::delete);
	}

}
