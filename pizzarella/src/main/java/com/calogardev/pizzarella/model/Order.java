package com.calogardev.pizzarella.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.calogardev.pizzarella.enums.OrderPlace;
import com.calogardev.pizzarella.enums.OrderStatus;
import com.calogardev.pizzarella.enums.OrderType;
import com.calogardev.pizzarella.enums.Status;

import lombok.Data;

/**
 * The main entity of the application. The class name is different from the DTO
 * because it cannot be Order due to SQL conflicts.
 * 
 * @author calogar
 *
 */
@Entity
@Data
@Table(name = "orders") // To avoid keyword conflicts in SQL
public class Order implements Serializable {

	private static final long serialVersionUID = 3311526638346833028L;

	@Id
	@GeneratedValue
	private Long id;

	@Enumerated(EnumType.STRING)
	private OrderPlace place;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private OrderType type;

	private Integer tableNumber;

	@Column(nullable = false, precision = 3, scale = 2)
	private Float totalPrice;

	@Temporal(TemporalType.TIMESTAMP)
	private Date orderedAt;

	@Column(length = 255)
	private String notes;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<ProductLine> productLines;

	private String telephone;

	public Order(OrderType type, Integer tableNumber, Float totalPrice, Date orderedAt, OrderStatus orderStatus,
			Status status, List<ProductLine> productLines, String telephone, OrderPlace place) {
		super();
		this.place = place;
		this.type = type;
		this.tableNumber = tableNumber;
		this.totalPrice = totalPrice;
		this.orderedAt = orderedAt;
		this.orderStatus = orderStatus;
		this.status = status;
		this.productLines = productLines;
		this.telephone = telephone;
	}

	public Order(OrderType type, Float totalPrice, Date orderedAt, OrderStatus orderStatus, Status status,
			List<ProductLine> productLines, String telephone) {
		super();
		this.type = type;
		this.totalPrice = totalPrice;
		this.orderedAt = orderedAt;
		this.orderStatus = orderStatus;
		this.status = status;
		this.productLines = productLines;
		this.telephone = telephone;
	}
}
