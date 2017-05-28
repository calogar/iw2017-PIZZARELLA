package com.calogardev.pizzarella.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.calogardev.pizzarella.enums.Status;

@Entity
public class ProductLine implements Serializable {

    private static final long serialVersionUID = -5228253338512822202L;

    @EmbeddedId
    private ProductLinePK id;

    @MapsId("productId")
    @JoinColumn(name = "product_id")
    @ManyToOne
    private Product product;

    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    @ManyToOne
    private Order order;

    private Float price;

    @Column(nullable = false, length = 999)
    private Integer amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public ProductLine() {

    }

    public ProductLine(ProductLinePK id, Product product, Order order, Integer amount) {
	super();
	this.id = id;
	this.product = product;
	this.order = order;
	this.amount = amount;
    }

    /**
     * @return the id
     */
    public ProductLinePK getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(ProductLinePK id) {
	this.id = id;
    }

    /**
     * @return the product
     */
    public Product getProduct() {
	return product;
    }

    /**
     * @param product
     *            the product to set
     */
    public void setProduct(Product product) {
	this.product = product;
    }

    /**
     * @return the order
     */
    public Order getOrder() {
	return order;
    }

    /**
     * @param order
     *            the order to set
     */
    public void setOrder(Order order) {
	this.order = order;
    }

    /**
     * @return the amount
     */
    public Integer getAmount() {
	return amount;
    }

    /**
     * @param amount
     *            the amount to set
     */
    public void setAmount(Integer amount) {
	this.amount = amount;
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "ProductLine [id=" + id + ", product=" + product + ", order=" + order + ", price=" + price + ", amount="
		+ amount + ", status=" + status + "]";
    }

    /**
     * @return the price
     */
    public Float getPrice() {
	return price;
    }

    /**
     * @param price
     *            the price to set
     */
    public void setPrice(Float price) {
	this.price = price;
    }

}
