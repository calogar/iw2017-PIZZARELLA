package com.calogardev.pizzarella.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class ProductFamily implements Serializable {

    private static final long serialVersionUID = -8921817738107980342L;

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = MIN_LENGTH, max = MAX_LENGTH)
    private String name;

    @Size(min = MIN_LENGTH, max = MAX_LENGTH)
    @Column(unique = true) // DB constraint - no validation
    private String code;

    /* Constraint values definition */

    @Transient
    public static final int MAX_LENGTH = 50;
    @Transient
    public static final int MIN_LENGTH = 2;
}
