package com.calogardev.pizzarella.dto;

import java.util.List;

public class PrivilegeDto {

    private Long id;

    private String name;

    private List<RoleDto> roles;

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

    /**
     * @return the roles
     */
    public List<RoleDto> getRoles() {
	return roles;
    }

    /**
     * @param roles
     *            the roles to set
     */
    public void setRoles(List<RoleDto> roles) {
	this.roles = roles;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "PrivilegeDto [id=" + id + ", name=" + name + ", roles=" + roles + "]";
    }

}
