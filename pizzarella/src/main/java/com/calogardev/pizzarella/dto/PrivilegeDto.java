package com.calogardev.pizzarella.dto;

import java.util.List;

import com.calogardev.pizzarella.model.Role;

public class PrivilegeDto {

	private Long id;

	private String name;

	private List<Role> roles;

	public PrivilegeDto() {
		super();
	}

	public PrivilegeDto(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "PrivilegeDto [id=" + id + ", name=" + name + "]";
	}

}
