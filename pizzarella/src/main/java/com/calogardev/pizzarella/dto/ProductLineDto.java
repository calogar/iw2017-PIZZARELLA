package com.calogardev.pizzarella.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import com.calogardev.pizzarella.enums.Status;

public class ProductLineDto implements Serializable {

    private static final long serialVersionUID = -4073065623017991679L;

    private ProductLinePKDto id;

    @NotNull
    private ProductDto product;

    @NotNull
    private OrderDto order;

    @Column(nullable = false, length = 999)
    private Integer amount;

    private Float price;

    private Status status;

    public String getProductName() {
	return product.getName();
    }

    public ProductLineDto() {

    }

    public ProductLineDto(ProductDto product, OrderDto order, Integer amount, Float price) {
	super();
	this.product = product;
	this.order = order;
	this.amount = amount;
	this.price = price;
	this.id = new ProductLinePKDto();
    }

    /**
     * @return the product
     */
    public ProductDto getProduct() {
	return product;
    }

    /**
     * @param product
     *            the product to set
     */
    public void setProduct(ProductDto product) {
	this.product = product;
    }

    /**
     * @return the order
     */
    public OrderDto getOrder() {
	return order;
    }

    /**
     * @param order
     *            the order to set
     */
    public void setOrder(OrderDto order) {
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

    public Float getPrice() {
	return price;
    }

    public void setPrice(Float price) {
	this.price = price;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "ProductLineDto [id=" + id + ", product=" + product + ", order=" + order + ", amount=" + amount
		+ ", price=" + price + ", status=" + status + "]";
    }

    /**
     * @return the id
     */
    public ProductLinePKDto getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(ProductLinePKDto id) {
	this.id = id;
    }

}
