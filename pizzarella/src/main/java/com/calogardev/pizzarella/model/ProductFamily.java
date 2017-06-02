package com.calogardev.pizzarella.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data // lombok
public class ProductFamily implements Serializable {

	private static final long serialVersionUID = -8921817738107980342L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Size(min = 2, max = 50)
	private String name;

	@Size(min = 2, max = 50)
	@Column(unique = true) // DB constraint - no validation
	private String code;
}
