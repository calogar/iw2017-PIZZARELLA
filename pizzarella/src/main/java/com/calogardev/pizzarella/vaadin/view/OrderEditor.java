package com.calogardev.pizzarella.vaadin.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.calogardev.pizzarella.dto.OrderDto;
import com.calogardev.pizzarella.dto.ProductDto;
import com.calogardev.pizzarella.dto.ProductFamilyDto;
import com.calogardev.pizzarella.dto.ProductLineDto;
import com.calogardev.pizzarella.enums.OrderPlace;
import com.calogardev.pizzarella.enums.OrderStatus;
import com.calogardev.pizzarella.enums.OrderType;
import com.calogardev.pizzarella.exception.CustomValidationException;
import com.calogardev.pizzarella.exception.OrderNotFoundException;
import com.calogardev.pizzarella.service.OrderService;
import com.calogardev.pizzarella.service.ProductFamilyService;
import com.calogardev.pizzarella.service.ProductService;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.ValidationException;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.themes.ValoTheme;

@SpringComponent
@UIScope
public class OrderEditor extends VerticalLayout {

	private static final long serialVersionUID = -5172926696646759383L;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductFamilyService productFamilyService;

	private OrderDto orderDto;

	private BeanValidationBinder<OrderDto> binder = new BeanValidationBinder(OrderDto.class);

	// Grab a reference so we can update the grid
	private OrdersView ordersView;

	private HorizontalLayout selectProductFamily = new HorizontalLayout();
	private VerticalLayout selectProduct = new VerticalLayout();
	private Grid<ProductDto> selectProductGrid = new Grid<ProductDto>();
	private VerticalLayout orderArea = new VerticalLayout();
	private Grid<ProductLineDto> productLinesGrid = new Grid<ProductLineDto>();
	private FormLayout orderForm = new FormLayout();

	/* Action buttons */
	Button save = new Button("Save", FontAwesome.SAVE);
	Button cancel = new Button("Cancel");
	Button delete = new Button("Delete", FontAwesome.TRASH_O);
	CssLayout actions = new CssLayout(save, cancel, delete);

	List<ProductDto> currentProducts = new ArrayList<ProductDto>();

	@Autowired
	public OrderEditor() {
	}

	@PostConstruct
	void init() {

		buildSelectProductFamily();
		buildSelectProduct();
		buildProductLinesGrid();
		buildOrderForm();

		// Configure and style components
		setSpacing(true);
		actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

		// wire action buttons to save, delete and reset
		save.addClickListener(e -> saveOrder(orderDto));
		delete.addClickListener(e -> deleteOrder(orderDto));
		cancel.addClickListener(e -> editOrder(orderDto));
		setVisible(false);

		orderArea.addComponents(orderForm);
		addComponents(selectProduct, orderArea);
		addComponents(actions);
	}

	private void buildSelectProductFamily() {
		selectProductGrid.setItems(productService.findAllSellable());
		for (ProductFamilyDto family : productFamilyService.findAll()) {
			Button select = new Button(family.getName(), e -> {

				selectProductGrid.setItems(productService.findAllSellableFromFamily(family));
			});
			selectProductFamily.addComponent(select);
		}
	}

	private void buildSelectProduct() {
		Label title = new Label("Products");
		title.addStyleName(ValoTheme.LABEL_H2);

		selectProductGrid.addColumn(ProductDto::getName).setCaption("Name");
		selectProductGrid.addColumn(ProductDto::getFormattedIngredients).setCaption("Ingredients");
		selectProductGrid.addColumn(ProductDto::getPrice).setCaption("Price (€)");

		selectProductGrid.addItemClickListener(e -> {

			updateProductLines(productService.findOne(e.getItem().getId()), 1);
		});

		selectProductGrid.setWidth("100%");
		selectProduct.addComponents(title, selectProductFamily, selectProductGrid);
	}

	private void buildProductLinesGrid() {

		Label productLinesTitle = new Label("Product Lines");
		productLinesTitle.setStyleName(ValoTheme.LABEL_H2);
		productLinesGrid.addColumn(ProductLineDto::getProductName).setCaption("Product");
		productLinesGrid.addColumn(ProductLineDto::getAmount).setCaption("Amount");
		productLinesGrid.addColumn(ProductLineDto::getPrice).setCaption("Price");
		productLinesGrid.addColumn(pl -> "Increase", new ButtonRenderer<ProductLineDto>(e -> {
			updateProductLines(e.getItem().getProduct(), 1);
		}));
		productLinesGrid.addColumn(pl -> "Decrease", new ButtonRenderer<ProductLineDto>(e -> {
			updateProductLines(e.getItem().getProduct(), -1);
		}));
		productLinesGrid.addColumn(pl -> "Remove", new ButtonRenderer<ProductLineDto>(e -> {
			deleteProductLine(e.getItem());
			productLinesGrid.setItems(orderDto.getProductLines());
		}));

		productLinesGrid.setWidth("100%");
		orderArea.addComponents(productLinesTitle, productLinesGrid);
	}

	private void buildOrderForm() {

		ComboBox<OrderType> orderType = new ComboBox<>("Order Type");
		orderType.setItems(EnumSet.allOf(OrderType.class));
		binder.bind(orderType, "type");

		// TODO not checking that tables are already taken
		ComboBox<Integer> orderTable = new ComboBox<>("Table Number");
		orderTable.setItems(Arrays.asList(1, 2, 3, 4, 5, 6));
		binder.bind(orderTable, "tableNumber");

		ComboBox<OrderPlace> orderPlace = new ComboBox<>("Place");
		orderPlace.setItems(EnumSet.allOf(OrderPlace.class));
		binder.bind(orderPlace, "place");
		// binder.forField(orderPlace).withValidator(dto -> dto.getTableNumber()
		// < 50 && dto.getTableNumber() > 1, "Table number must be between 1 and
		// 50").bind("place");

		TextField telephone = new TextField("Contact telephone");
		binder.bind(telephone, "telephone");
		telephone.setVisible(false);

		// RichTextArea notes = new RichTextArea("Additional notes");
		// binder.bind(notes, "notes");

		ComboBox<OrderStatus> orderStatus = new ComboBox<>("Order status");
		orderStatus.setItems(EnumSet.allOf(OrderStatus.class));
		binder.bind(orderStatus, "orderStatus");

		orderType.addSelectionListener(e -> {
			if (e.getSelectedItem().get() != OrderType.LOCAL) {
				// Disable table and place if order is to take away
				// TODO manually set to null place and table in save
				orderTable.setVisible(false);
				orderPlace.setVisible(false);
				telephone.setVisible(true);
			} else {
				orderTable.setVisible(true);
				orderPlace.setVisible(true);
				telephone.setVisible(false);
			}
		});

		orderForm.addComponents(orderType, orderTable, orderPlace, telephone, orderStatus);// ,
		// notes);
	}

	/**
	 * Add one line product if product is new or update one if existent
	 */
	private void updateProductLines(ProductDto productDto, Integer amount) {

		Boolean productHasOrderLine = false;
		ProductLineDto setForDelete = null;

		if (orderDto.getProductLines() == null) {
			// Create the first product line of the order
			addNewProductLine(productDto, amount);
		} else {

			for (ProductLineDto plDto : orderDto.getProductLines()) {
				if (plDto.getProduct().getId() == productDto.getId()) {

					// If product already has a product line, update it
					plDto.setAmount(plDto.getAmount() + amount);
					plDto.setPrice(productDto.getPrice() * plDto.getAmount());

					// If amount is zero, delete product line
					// We can't delete it while looping
					if (plDto.getAmount() == 0) {
						setForDelete = plDto;
					}

					productHasOrderLine = true;
				}
			}
			if (!productHasOrderLine) {
				// It's the first order line for this product
				addNewProductLine(productDto, amount);
			}
		}

		if (setForDelete != null) {
			deleteProductLine(setForDelete);
		}

		productLinesGrid.setItems(orderDto.getProductLines());
	}

	private void addNewProductLine(ProductDto productDto, Integer amount) {
		List<ProductLineDto> pls = orderDto.getProductLines();
		if (pls == null) {
			// It's the first product line for this product
			pls = new ArrayList<ProductLineDto>();
		}
		Float price = amount * productDto.getPrice();
		pls.add(new ProductLineDto(productDto, orderDto, amount, price));
		orderDto.setProductLines(pls);

	}

	private void deleteProductLine(ProductLineDto dto) {
		List<ProductLineDto> pls = orderDto.getProductLines();
		pls.remove(dto);
		orderDto.setProductLines(pls);
	}

	public interface ChangeHandler {
		void onChange();
	}

	public void editOrder(OrderDto dto) {

		if (dto == null) {
			setVisible(false);
			return;
		}
		final boolean persisted = dto.getId() != null;
		if (persisted) {
			// Perform update (needs id)
			try {
				orderDto = orderService.findOne(dto.getId());
			} catch (OrderNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			// Perform create
			orderDto = dto;
		}
		cancel.setVisible(persisted);

		binder.setBean(orderDto);
		if (orderDto.getProductLines() != null) {
			productLinesGrid.setItems(orderDto.getProductLines());
		}

		setVisible(true);
		// A hack to ensure the whole form is visible
		save.focus();
	}

	private void saveOrder(OrderDto orderDto) {

		if (binder.validate().isOk()) {
			try {
				binder.writeBean(orderDto);
				orderService.save(orderDto);
				Notification.show("Record saved correctly.");
				ordersView.refreshGrid();

			} catch (CustomValidationException | ValidationException e) {
				Notification n = new Notification(e.getMessage(), null, Notification.Type.ERROR_MESSAGE, true);
				n.show(Page.getCurrent());
				return;
			}
		}
	}

	private void deleteOrder(OrderDto orderDto) {
		orderService.delete(orderDto);
	}

	public void setViewReference(OrdersView view) {
		ordersView = view;
	}

	public void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
		save.addClickListener(e -> h.onChange());
		delete.addClickListener(e -> h.onChange());
	}

}
