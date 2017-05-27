package com.calogardev.pizzarella.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

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

    @Column(nullable = false, length = 999)
    private Integer amount;

    public ProductLine() {

    }

    public ProductLine(ProductLinePK id, Product product, Order order, Integer amount) {
	super();
	this.id = id;
	this.product = product;
	this.order = order;
	this.amount = amount;
    }

    @Override
    public String toString() {
	return "ProductLine [id=" + id + ", product=" + product + ", order=" + order + ", amount=" + amount + "]";
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
}
