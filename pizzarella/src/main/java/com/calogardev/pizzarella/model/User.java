package com.calogardev.pizzarella.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.calogardev.pizzarella.enums.Status;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = -5873766435393503656L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 30, nullable = false)
	private String name;

	@Column(length = 70, nullable = false)
	private String surnames;

	@Column(length = 9, nullable = false, unique = true)
	private String dni;

	@Column(length = 50, nullable = false, unique = true)
	private String nickname;

	@Column(length = 70, nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<Role> roles;

	public User(String name, String surnames, String dni, String nickname, String password, Status status) {
		super();
		this.name = name;
		this.surnames = surnames;
		this.dni = dni;
		this.nickname = nickname;
		this.password = password;
		this.status = status;
	}
}
