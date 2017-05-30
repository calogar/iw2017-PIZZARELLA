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

/**
 * The main entity of the application. The class name is different from the DTO
 * because it cannot be Order due to SQL conflicts.
 * 
 * @author calogar
 *
 */
@Entity
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

    public Order() {
	super();
	// TODO Auto-generated constructor stub
    }

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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Order other = (Order) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Order [id=" + id + ", place=" + place + ", type=" + type + ", tableNumber=" + tableNumber
		+ ", totalPrice=" + totalPrice + ", orderedAt=" + orderedAt + ", notes=" + notes + ", orderStatus="
		+ orderStatus + ", status=" + status + ", productLines=" + productLines + ", telephone=" + telephone
		+ "]";
    }

}
