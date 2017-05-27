package com.calogardev.pizzarella.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.calogardev.pizzarella.dto.Dto;
import com.calogardev.pizzarella.enums.Status;

@Entity
public class Product implements Serializable, Dto {

    private static final long serialVersionUID = 6794921999078255153L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false, precision = 3, scale = 2)
    private Float price;

    @Column(nullable = false, precision = 3, scale = 2)
    private Float vat;

    @Column(length = 999, nullable = false)
    private Integer amount;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Product> products;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
    private ProductFamily family;

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

    /**
     * @return the vat
     */
    public Float getVat() {
	return vat;
    }

    /**
     * @param vat
     *            the vat to set
     */
    public void setVat(Float vat) {
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Product [id=" + id + ", name=" + name + ", status=" + status + ", price=" + price + ", vat=" + vat
		+ ", amount=" + amount + ", products=" + products + ", family=" + family + "]";
    }

    @Override
    public Field[] getDeclaredFields() {
	// TODO Auto-generated method stub
	return null;
    }

}
