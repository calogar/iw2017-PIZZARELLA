package com.calogardev.pizzarella.dto;

import java.math.BigDecimal;
import java.util.List;

import com.calogardev.pizzarella.model.Product;
import com.calogardev.pizzarella.model.ProductFamily;

/**
 * The main Dto for Products. Needs the id because it's the only unique
 * identifier.
 * 
 * @author calogar
 *
 */
public class ProductDto {

    private Long id;

    private String name;

    private ProductFamily family;

    private BigDecimal price;

    private BigDecimal vat;

    private Integer amount;

    private List<Product> products;

    public ProductDto() {

    }

    public Long getId() {
	return id;
    }

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
	return "ProductDto [id=" + id + ", name=" + name + ", family=" + family + ", price=" + price + ", vat=" + vat
		+ ", amount=" + amount + ", products=" + products + "]";
    }

}
