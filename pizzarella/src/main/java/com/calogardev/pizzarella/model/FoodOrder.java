package com.calogardev.pizzarella.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.calogardev.pizzarella.enums.OrderPlace;
import com.calogardev.pizzarella.enums.OrderStatus;
import com.calogardev.pizzarella.enums.Status;

/**
 * The main entity of the application. The class name is different from the DTO
 * because it cannot be Order due to SQL conflicts.
 * 
 * @author calogar
 *
 */
@Entity
public class FoodOrder implements Serializable {

    private static final long serialVersionUID = 3311526638346833028L;

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderPlace place;

    @Column(length = 10, nullable = false)
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

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Product> products;

    @ManyToOne
    private Client client;

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
	this.id = id;
    }

    /**
     * @return the products
     */
    public List<Product> getProducts() {
	return products;
    }

    /**
     * @param products
     *            the products to set
     */
    public void setProducts(List<Product> products) {
	this.products = products;
    }

    /**
     * @return the place
     */
    public OrderPlace getPlace() {
	return place;
    }

    /**
     * @param place
     *            the place to set
     */
    public void setPlace(OrderPlace place) {
	this.place = place;
    }

    /**
     * @return the tableNumber
     */
    public Integer getTableNumber() {
	return tableNumber;
    }

    /**
     * @param tableNumber
     *            the tableNumber to set
     */
    public void setTableNumber(Integer tableNumber) {
	this.tableNumber = tableNumber;
    }

    /**
     * @return the totalPrice
     */
    public Float getTotalPrice() {
	return totalPrice;
    }

    /**
     * @param totalPrice
     *            the totalPrice to set
     */
    public void setTotalPrice(Float totalPrice) {
	this.totalPrice = totalPrice;
    }

    /**
     * @return the orderedAt
     */
    public Date getOrderedAt() {
	return orderedAt;
    }

    /**
     * @param orderedAt
     *            the orderedAt to set
     */
    public void setOrderedAt(Date orderedAt) {
	this.orderedAt = orderedAt;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
	return notes;
    }

    /**
     * @param notes
     *            the notes to set
     */
    public void setNotes(String notes) {
	this.notes = notes;
    }

    /**
     * @return the orderStatus
     */
    public OrderStatus getOrderStatus() {
	return orderStatus;
    }

    /**
     * @param orderStatus
     *            the orderStatus to set
     */
    public void setOrderStatus(OrderStatus orderStatus) {
	this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
	return "Order [id=" + id + ", products=" + products + ", place=" + place + ", tableNumber=" + tableNumber
		+ ", totalPrice=" + totalPrice + ", orderedAt=" + orderedAt + ", notes=" + notes + ", orderStatus="
		+ orderStatus + "]";
    }

}
