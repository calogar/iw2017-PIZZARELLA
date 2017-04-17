package com.calogardev.pizzarella.dto;

import java.lang.reflect.Field;
import java.util.List;

/**
 * The main Dto for Products. Needs the id because it's the only unique
 * identifier.
 * 
 * @author calogar
 *
 */
public class ProductDto implements Dto {

    private Long id;

    private String name;

    private ProductFamilyDto family;

    private Float price;

    private Float vat;

    private Integer amount;

    private List<ProductDto> products;

    public ProductDto() {

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
    public ProductFamilyDto getFamily() {
	return family;
    }

    /**
     * @param family
     *            the family to set
     */
    public void setFamily(ProductFamilyDto family) {
	this.family = family;
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
    public List<ProductDto> getProducts() {
	return products;
    }

    /**
     * @param products
     *            the products to set
     */
    public void setProducts(List<ProductDto> products) {
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

    @Override
    public Field[] getDeclaredFields() {
	// TODO Auto-generated method stub
	return null;
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

}
