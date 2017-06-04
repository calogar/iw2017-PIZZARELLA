package com.calogardev.pizzarella.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -5873766435393503656L;

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = MIN_LENGTH, max = MAX_NAME_LENGTH)
    private String name;

    @NotNull
    @Size(min = MIN_LENGTH, max = MAX_SURNAMES_LENGTH)
    private String surnames;

    @NotNull
    @Size(min = DNI_LENGTH, max = DNI_LENGTH)
    @Column(unique = true)
    private String dni;

    @NotNull
    @Size(max = MAX_USERNAME_LENGTH)
    @Column(unique = true)
    private String username;

    @NotNull
    @Size(min = MIN_PASSWORD_LENGTH, max = MAX_PASSWORD_LENGTH)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    /* Constraint values definition */

    @Transient
    public static final int MAX_NAME_LENGTH = 30;
    @Transient
    public static final int MAX_SURNAMES_LENGTH = 70;
    @Transient
    public static final int MIN_LENGTH = 2;
    @Transient
    public static final int DNI_LENGTH = 9;
    @Transient
    public static final int MAX_USERNAME_LENGTH = 50;
    @Transient
    public static final int MIN_PASSWORD_LENGTH = 6;
    @Transient
    public static final int MAX_PASSWORD_LENGTH = 70;

    public User() {
	super();
    }

    public User(String name, String surnames, String dni, String nickname, String password) {
	super();
	this.name = name;
	this.surnames = surnames;
	this.dni = dni;
	this.username = nickname;
	this.password = password;
    }
}
