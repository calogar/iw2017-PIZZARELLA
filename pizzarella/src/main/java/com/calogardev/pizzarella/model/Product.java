package com.calogardev.pizzarella.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

	@Column(length = 50, nullable = false, unique = true)
	private String name;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;

	@Column(nullable = false, precision = 3, scale = 2)
	private Float price;

	@Column(nullable = false, precision = 3, scale = 2)
	private Float vat;

	@Column(/* length = 200, */ nullable = false)
	private Integer amount;

	private Boolean isIngredient;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "ingredient_id")
	// @JoinTable(name = "product_ingredient", joinColumns = @JoinColumn(name =
	// "composed_id", referencedColumnName = "id"), inverseJoinColumns =
	// @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
	private Set<Product> ingredients;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_family_id")
	private ProductFamily family;

	public Product() {
		super();
	}

	public Product(String name, Float price, Float vat, Integer amount, Boolean isIngredient, ProductFamily family) {
		super();
		this.name = name;
		this.price = price;
		this.vat = vat;
		this.amount = amount;
		this.isIngredient = isIngredient;
		this.family = family;
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
	 * @return the isIngredient
	 */
	public Boolean getIsIngredient() {
		return isIngredient;
	}

	/**
	 * @param isIngredient
	 *            the isIngredient to set
	 */
	public void setIsIngredient(Boolean isIngredient) {
		this.isIngredient = isIngredient;
	}

	/**
	 * @return the ingredients
	 */
	public Set<Product> getIngredients() {
		return ingredients;
	}

	/**
	 * @param ingredients
	 *            the ingredients to set
	 */
	public void setIngredients(Set<Product> ingredients) {
		this.ingredients = ingredients;
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

	@Override
	public Field[] getDeclaredFields() {
		// TODO Auto-generated method stub
		return null;
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
		result = prime * result + ((family == null) ? 0 : family.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ingredients == null) ? 0 : ingredients.hashCode());
		result = prime * result + ((isIngredient == null) ? 0 : isIngredient.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((vat == null) ? 0 : vat.hashCode());
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
		Product other = (Product) obj;
		if (family == null) {
			if (other.family != null)
				return false;
		} else if (!family.equals(other.family))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ingredients == null) {
			if (other.ingredients != null)
				return false;
		} else if (!ingredients.equals(other.ingredients))
			return false;
		if (isIngredient == null) {
			if (other.isIngredient != null)
				return false;
		} else if (!isIngredient.equals(other.isIngredient))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (vat == null) {
			if (other.vat != null)
				return false;
		} else if (!vat.equals(other.vat))
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
		return "Product [id=" + id + ", name=" + name + ", status=" + status + ", price=" + price + ", vat=" + vat
				+ ", amount=" + amount + ", isIngredient=" + isIngredient + ", ingredients=" + ingredients + ", family="
				+ family + "]";
	}

}
