package com.calogardev.pizzarella.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.calogardev.pizzarella.enums.OrderPlace;
import com.calogardev.pizzarella.enums.OrderStatus;
import com.calogardev.pizzarella.enums.OrderType;
import com.calogardev.pizzarella.enums.Status;

public class OrderDto implements Serializable {

	private static final long serialVersionUID = 5423676356922359348L;

	private Long id;

	private OrderPlace place;

	@NotNull
	private OrderType type;

	private Integer tableNumber;

	@Digits(integer = 3, fraction = 2)
	private Float totalPrice;

	private Date orderedAt;

	@Size(min = 0, max = 255)
	private String notes;

	private OrderStatus orderStatus;

	private Status status;

	private List<ProductLineDto> productLines;

	private String telephone;

	public String formatProducts() {
		StringBuilder b = new StringBuilder();
		for (ProductLineDto productLineDto : getProductLines()) {
			b.append(productLineDto.getProduct().getName() + ", ");
		}
		return b.toString();
	}

	public String formatOrderStatus() {
		return orderStatus.name();
	}

	public String toFormattedString() {
		return formatProducts() + " price: " + totalPrice;
	}

	public String getFormattedLocation() {
		if (type == OrderType.LOCAL) {
			return orderStatus.name() + " in " + place.name() + ", table " + tableNumber;
		} else {
			return type.name() + " with telephone " + telephone;
		}
	}

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

	/**
	 * @return the productLines
	 */
	public List<ProductLineDto> getProductLines() {
		return productLines;
	}

	/**
	 * @param productLines
	 *            the productLines to set
	 */
	public void setProductLines(List<ProductLineDto> productLines) {
		this.productLines = productLines;
	}

	/**
	 * @return the type
	 */
	public OrderType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(OrderType type) {
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderDto [id=" + id + ", place=" + place + ", type=" + type + ", tableNumber=" + tableNumber
				+ ", totalPrice=" + totalPrice + ", orderedAt=" + orderedAt + ", notes=" + notes + ", orderStatus="
				+ orderStatus + ", status=" + status + ", productLines=" + productLines + ", telephone=" + telephone
				+ "]";
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
