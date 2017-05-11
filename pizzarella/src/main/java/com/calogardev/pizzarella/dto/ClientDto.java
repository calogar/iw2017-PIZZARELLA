package com.calogardev.pizzarella.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;

public class ClientDto implements Serializable {

	private static final long serialVersionUID = -731313285668747402L;

	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String surnames;

	private List<OrderDto> orders;

	public ClientDto() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	public List<OrderDto> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDto> orders) {
		this.orders = orders;
	}
}
