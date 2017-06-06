package com.calogardev.pizzarella.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = { "products" })
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

    @OneToMany(mappedBy = "family", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Product> products;

    public ProductFamily() {
	super();
    }

    public ProductFamily(String name, String code) {
	super();
	this.name = name;
	this.code = code;
    }
}
