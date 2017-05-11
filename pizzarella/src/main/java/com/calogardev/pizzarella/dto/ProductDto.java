package com.calogardev.pizzarella.dto;

import java.lang.reflect.Field;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The main Dto for Products. Needs the id because it's the only unique
 * identifier.
 * 
 * @author calogar
 *
 */
public class ProductDto implements Dto {

	private Long id;

	@NotNull
	@Size(min = 2, max = 30)
	private String name;

	@NotNull
	@Digits(integer = 3, fraction = 2)
	private Float price;

	@NotNull
	@Digits(integer = 3, fraction = 2)
	private Float vat;

	@NotNull
	@Size(min = 1, max = 999)
	private Integer amount;

	private List<ProductDto> products;

	private ProductFamilyDto family;

	public ProductDto() {

	}

	public String getFormattedIngredients() {
		String result;
		if (products.size() == 0) {
			result = "This product has no ingredients.";
		} else {
			result = "";
			for (ProductDto product : products) {
				result = product.getName() + ", ";
			}
			// Remove the last separator
			result.substring(0, result.length() - 3);
		}
		return result;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name + " (" + family.getName() + ")";
	}

	@Override
	public Field[] getDeclaredFields() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}
}
