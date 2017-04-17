package com.calogardev.pizzarella.view.product;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.dialogs.ConfirmDialog;

import com.calogardev.pizzarella.dao.ProductService;
import com.calogardev.pizzarella.dto.ProductDto;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = ProductsView.VIEW_ROUTE)
@UIScope
public class ProductsView extends VerticalLayout implements View {

    private static final long serialVersionUID = -8090435519281970500L;
    private final static Logger log = LoggerFactory.getLogger(ProductsView.class);

    public static final String VIEW_NAME = "Products";
    public static final String VIEW_ROUTE = "products";

    @Autowired
    private ProductService productService;

    private Grid<ProductDto> grid;

    @PostConstruct
    public void init() {
	commonsSettings();

	setSizeFull();

	// Create the view options, like creating new products

	HorizontalLayout options = new HorizontalLayout();
	options.setWidth("100%");

	addComponent(options);

	Button createButton = new Button("Create new Product", new ClickListener() {
	    @Override
	    public void buttonClick(ClickEvent event) {
		UI.getCurrent().getNavigator().navigateTo(CreateProductView.VIEW_ROUTE);
	    }
	});
	// Using an empty expanding label to align the button to the right
	Label spacer = new Label();
	options.addComponent(spacer);
	options.setExpandRatio(spacer, 1f);
	options.addComponent(createButton);

	// Create the grid

	grid = new Grid<ProductDto>();
	grid.setSizeFull();
	addComponent(grid);
	setExpandRatio(grid, 1);

	List<ProductDto> products = productService.findAll();
	grid.setItems(products);
	buildColumns();

	ButtonRenderer<ProductDto> deleteButton = new ButtonRenderer<ProductDto>(clickEvent -> {
	    ConfirmDialog.show(UI.getCurrent(), "Are you sure?", new ConfirmDialog.Listener() {

		@Override
		public void onClose(ConfirmDialog dialog) {
		    if (dialog.isConfirmed()) {
			// Confirmed to continue
			Long id = clickEvent.getItem().getId();
			log.info("THIS: " + productService.findOne(id));
			productService.delete(productService.findOne(id));
			grid.setItems(productService.findAll());
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
	grid.addColumn(ProductDto::getId).setCaption("Id");
	grid.addColumn(ProductDto::getName).setCaption("Name");

	grid.addColumn(product -> product.getFamily().getName()).setCaption("Family");
	grid.addColumn(ProductDto::getPrice).setCaption("Price");
	grid.addColumn(ProductDto::getVat).setCaption("VAT");
	grid.addColumn(ProductDto::getAmount).setCaption("Amount");
	grid.addColumn(ProductDto::getFormattedIngredients).setCaption("Ingredients");
    }

    @Override
    public void enter(ViewChangeEvent event) {
	// TODO Auto-generated method stub

    }
}
