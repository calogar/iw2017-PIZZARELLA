package com.calogardev.pizzarella.dto;

import java.util.List;

public class RoleDto {

	private Long id;

	private String name;

	private List<UserDto> users;

	private List<PrivilegeDto> privileges;

	public RoleDto() {
		super();
	}

	public RoleDto(String name, List<PrivilegeDto> privileges) {
		super();
		this.name = name;
		this.privileges = privileges;
	}

	public List<PrivilegeDto> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<PrivilegeDto> privileges) {
		this.privileges = privileges;
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

	public List<UserDto> getUsers() {
		return users;
	}

	public void setUsers(List<UserDto> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "RoleDto [id=" + id + ", name=" + name + ", users=" + users + "]";
	}
}
