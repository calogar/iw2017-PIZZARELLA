package com.calogardev.pizzarella.view.product;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.calogardev.pizzarella.dao.ProductService;
import com.calogardev.pizzarella.dto.ProductDto;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = ProductsView.VIEW_ROUTE)
@UIScope
public class ProductsView extends VerticalLayout implements View {

    private final static Logger log = LoggerFactory.getLogger(ProductsView.class);
    private static final long serialVersionUID = -8090435519281970500L;
    public static final String VIEW_ROUTE = "products";
    public static final String VIEW_NAME = "Products";

    private Grid<ProductDto> grid;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductEditor productEditor;

    private TextField filterByName;

    @PostConstruct
    void init() {
	commonsSettings();
	setSizeFull();

	Button addNewButton = new Button("New product", FontAwesome.PLUS);
	addNewButton.addClickListener(e -> productEditor.editProduct(new ProductDto()));

	buildFilterByName();
	HorizontalLayout menu = new HorizontalLayout(addNewButton, filterByName);
	addComponent(menu);

	// Build the grid
	buildGrid();

	addComponent(productEditor);

	// Populate grid with all the data
	listProducts(null);

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

    private void buildGrid() {
	grid = new Grid<ProductDto>();
	grid.setHeight(300, Unit.PIXELS);
	grid.setSizeFull();
	addComponent(grid);
	setExpandRatio(grid, 1);
	grid.setItems(productService.findAll());
	buildColumns();

	// Connect selected Product to editor or hide if none is selected
	grid.asSingleSelect().addValueChangeListener(e -> {
	    productEditor.editProduct(e.getValue());
	});
    }

    private void buildFilterByName() {
	filterByName = new TextField();
	filterByName.setPlaceholder("Filter by name");
	filterByName.setValueChangeMode(ValueChangeMode.LAZY);
	filterByName.addValueChangeListener(e -> listProducts(e.getValue()));

	// Listen changes made by the editor, refresh data from backend
	productEditor.setChangeHandler(() -> {
	    productEditor.setVisible(false);
	    listProducts(filterByName.getValue());
	});
    }

    // TODO: add querydsl to implement filtering
    private void listProducts(String filterText) {
	// TODO Yet to implement
	grid.setItems(productService.findAll());

    }

    @Override
    public void enter(ViewChangeEvent event) {
	// TODO Auto-generated method stub

    }
}
