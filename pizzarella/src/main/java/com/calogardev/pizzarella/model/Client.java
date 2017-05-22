package com.calogardev.pizzarella.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client implements Serializable {

	private static final long serialVersionUID = 4961603879564736028L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 30, nullable = false)
	private String name;

	@Column(length = 70, nullable = false)
	private String surnames;

	@OneToMany
	private List<FoodOrder> orders;

	public Client() {

	}

	public Client(Long id, String name, String surnames) {
		super();
		this.id = id;
		this.name = name;
		this.surnames = surnames;
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

	public List<FoodOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<FoodOrder> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", surnames=" + surnames + ", orders=" + orders + "]";
	}
}
