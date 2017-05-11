package com.calogardev.pizzarella.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProductLine implements Serializable {

	private static final long serialVersionUID = -5228253338512822202L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Product product;

	@ManyToOne
	private Order order;

	@Column(nullable = false, length = 999)
	private Integer amount;

	public ProductLine() {

	}

	public ProductLine(Long id, Product product, Order order, Integer amount) {
		super();
		this.id = id;
		this.product = product;
		this.order = order;
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "ProductLine [id=" + id + ", product=" + product + ", order=" + order + ", amount=" + amount + "]";
	}
}
