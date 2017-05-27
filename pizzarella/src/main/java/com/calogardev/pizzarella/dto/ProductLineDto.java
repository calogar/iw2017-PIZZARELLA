package com.calogardev.pizzarella.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class ProductLineDto {

    private Long id;

    @NotNull
    private ProductDto product;

    @NotNull
    private OrderDto order;

    @Column(nullable = false, length = 999)
    private Integer amount;

    public ProductLineDto() {

    }

    public ProductLineDto(Long id, ProductDto product, OrderDto order, Integer amount) {
	super();
	this.id = id;
	this.product = product;
	this.order = order;
	this.amount = amount;
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "ProductLineDto [id=" + id + ", product=" + product + ", order=" + order + ", amount=" + amount + "]";
    }

}
