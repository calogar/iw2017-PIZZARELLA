package com.calogardev.pizzarella.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.calogardev.pizzarella.enums.OrderPlace;
import com.calogardev.pizzarella.enums.OrderStatus;
import com.calogardev.pizzarella.enums.Status;

public class OrderDto implements Serializable {

	private static final long serialVersionUID = 5423676356922359348L;

	private Long id;

	@NotNull
	private OrderPlace place;

	@NotNull
	@Size(min = 0, max = 10)
	private Integer tableNumber;

	@NotNull
	@Digits(integer = 3, fraction = 2)
	private Float totalPrice;

	@NotNull
	private Date orderedAt;

	@Size(min = 0, max = 255)
	private String notes;

	private OrderStatus orderStatus;

	private Status status;

	private List<ProductDto> products;

	private ClientDto client;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrderPlace getPlace() {
		return place;
	}

	public void setPlace(OrderPlace place) {
		this.place = place;
	}

	public Integer getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(Integer tableNumber) {
		this.tableNumber = tableNumber;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getOrderedAt() {
		return orderedAt;
	}

	public void setOrderedAt(Date orderedAt) {
		this.orderedAt = orderedAt;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<ProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}

	public ClientDto getClient() {
		return client;
	}

	public void setClient(ClientDto client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "OrderDto [id=" + id + ", place=" + place + ", tableNumber=" + tableNumber + ", totalPrice=" + totalPrice
				+ ", orderedAt=" + orderedAt + ", notes=" + notes + ", orderStatus=" + orderStatus + ", status="
				+ status + ", products=" + products + ", client=" + client + "]";
	}

}