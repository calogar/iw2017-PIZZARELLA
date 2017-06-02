package com.calogardev.pizzarella.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.calogardev.pizzarella.enums.Status;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = { "order" })
public class ProductLine implements Serializable {

	private static final long serialVersionUID = -5228253338512822202L;

	@Id
	@GeneratedValue
	private Long id;

	@JoinColumn(name = "product_id")
	@ManyToOne
	private Product product;

	@JoinColumn(name = "order_id")
	@ManyToOne
	private Order order;

	private Float price;

	@Column(nullable = false, length = 999)
	private Integer amount;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status;

	public ProductLine(Product product, Order order, Float price, Integer amount, Status status) {
		super();
		this.product = product;
		this.order = order;
		this.price = price;
		this.amount = amount;
		this.status = status;
	}

	public ProductLine(Long id, Product product, Order order, Integer amount) {
		super();
		this.id = id;
		this.product = product;
		this.order = order;
		this.amount = amount;
	}
}
