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
    private List<ProductLine> productLines;

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

    /**
     * @return the status
     */
    public Status getStatus() {
	return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(Status status) {
	this.status = status;
    }

    /**
     * @return the productLines
     */
    public List<ProductLine> getProductLines() {
	return productLines;
    }

    /**
     * @param productLines
     *            the productLines to set
     */
    public void setProductLines(List<ProductLine> productLines) {
	this.productLines = productLines;
    }

    /**
     * @return the client
     */
    public Client getClient() {
	return client;
    }

    /**
     * @param client
     *            the client to set
     */
    public void setClient(Client client) {
	this.client = client;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "FoodOrder [id=" + id + ", place=" + place + ", tableNumber=" + tableNumber + ", totalPrice="
		+ totalPrice + ", orderedAt=" + orderedAt + ", notes=" + notes + ", orderStatus=" + orderStatus
		+ ", status=" + status + ", productLines=" + productLines + ", client=" + client + "]";
    }
}
