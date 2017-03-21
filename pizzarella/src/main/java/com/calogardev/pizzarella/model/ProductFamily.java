package com.calogardev.pizzarella.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Identify how the products are grouped. It's not an enum so we can extend it
 * at runtime. The family with the "ingredient" code is a special family that
 * must always exist in the system.
 * 
 * @author calogar
 *
 */
@Entity
public class ProductFamily {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @Column(unique = true)
    @NotNull
    private String code;

    public ProductFamily() {
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
     * @return the code
     */
    public String getCode() {
	return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
	this.code = code;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "ProductFamily [id=" + id + ", name=" + name + ", code=" + code + "]";
    }

    public Boolean isIngredient() {
	return "ingredient".equals(code);
    }
}
