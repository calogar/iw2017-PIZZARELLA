package com.calogardev.pizzarella.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.calogardev.pizzarella.enums.Status;

/**
 * The main Dto for Products. Needs the id because it's the only unique
 * identifier.
 * 
 * @author calogar
 *
 */
public class ProductDto implements Serializable {

    private static final long serialVersionUID = 1376426136782175559L;

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
    // @Size(min = 1, max = 200)
    private Integer amount;

    @NotNull
    private Status status;

    private Boolean isIngredient;

    private Set<ProductDto> ingredients;

    private ProductFamilyDto family;

    public ProductDto() {
	super();
	// TODO Auto-generated constructor stub
    }

    public ProductDto(String name, Float price, Float vat, Integer amount, Boolean isIngredient,
	    ProductFamilyDto family) {
	super();
	this.name = name;
	this.price = price;
	this.vat = vat;
	this.amount = amount;
	this.isIngredient = isIngredient;
	this.family = family;
    }

    public String getFormattedIngredients() {
	String result;
	if (ingredients.size() == 0) {
	    result = "This product has no ingredients.";
	} else {
	    result = "";
	    for (ProductDto ingredient : ingredients) {
		result = ingredient.getName() + ", " + result;
	    }
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
    public Set<ProductDto> getIngredients() {
	return ingredients;
    }

    /**
     * @param ingredients
     *            the ingredients to set
     */
    public void setIngredients(Set<ProductDto> ingredients) {
	this.ingredients = ingredients;
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "ProductDto [id=" + id + ", name=" + name + ", price=" + price + ", vat=" + vat + ", amount=" + amount
		+ ", status=" + status + ", isIngredient=" + isIngredient + ", ingredients=" + ingredients + ", family="
		+ family + "]";
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
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((amount == null) ? 0 : amount.hashCode());
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
	ProductDto other = (ProductDto) obj;
	if (amount == null) {
	    if (other.amount != null)
		return false;
	} else if (!amount.equals(other.amount))
	    return false;
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

}
