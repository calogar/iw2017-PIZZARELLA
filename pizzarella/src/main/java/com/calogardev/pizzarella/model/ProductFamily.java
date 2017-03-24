package com.calogardev.pizzarella.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.calogardev.pizzarella.enums.Status;

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

	@Enumerated(EnumType.STRING)
	private Status status;

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

	public Boolean isIngredient() {
		return "ingredient".equals(code);
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductFamily [id=" + id + ", name=" + name + ", code=" + code + ", status=" + status + "]";
	}
}
