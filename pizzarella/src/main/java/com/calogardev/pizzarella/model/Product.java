package com.calogardev.pizzarella.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.calogardev.pizzarella.enums.Status;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @OneToMany(fetch = FetchType.EAGER)
    private ProductFamily family;

    @NotNull
    private Status status;

    @NotNull
    private BigDecimal price;

    @NotNull
    private BigDecimal vat;

    @NotNull
    private Integer amount;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Product> products;

    public Product() {
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
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * @return the family
     */
    public ProductFamily getFamily() {
	return family;
    }

    /**
     * @param family
     *            the family to set
     */
    public void setFamily(ProductFamily family) {
	this.family = family;
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
     * @return the price
     */
    public BigDecimal getPrice() {
	return price;
    }

    /**
     * @param price
     *            the price to set
     */
    public void setPrice(BigDecimal price) {
	this.price = price;
    }

    /**
     * @return the vat
     */
    public BigDecimal getVat() {
	return vat;
    }

    /**
     * @param vat
     *            the vat to set
     */
    public void setVat(BigDecimal vat) {
	this.vat = vat;
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Product [id=" + id + ", name=" + name + ", family=" + family + ", status=" + status + ", price=" + price
		+ ", vat=" + vat + ", amount=" + amount + ", products=" + products + "]";
    }
}
