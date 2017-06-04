package com.calogardev.pizzarella.model;

import java.io.Serializable;
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
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.calogardev.pizzarella.enums.VatType;

import lombok.Data;

@Entity
@Data
public class Product implements Serializable {

    private static final long serialVersionUID = 6794921999078255153L;

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = MIN_NAME, max = MAX_NAME)
    @Column(unique = true)
    private String name;

    @NotNull
    @Digits(integer = 3, fraction = 2)
    private Float price;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VatType vat;

    @NotNull
    @Size(max = MAX_STOCK)
    private Integer stock;

    @NotNull
    private Boolean isIngredient;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ingredient_id")
    // @JoinTable(name = "product_ingredient", joinColumns = @JoinColumn(name =
    // "composed_id", referencedColumnName = "id"), inverseJoinColumns =
    // @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
    private Set<Product> ingredients;

    /* Constraint values definition */

    @Transient
    private static final int MIN_NAME = 2;
    @Transient
    private static final int MAX_NAME = 50;
    @Transient
    private static final int MAX_STOCK = 200;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_family_id")
    private ProductFamily family;

    public Product() {
	super();
    }
}
