package com.calogardev.pizzarella.dto;

public class RoleDto {

    private Long id;

    private String name;

    public RoleDto() {
	super();
    }

    public RoleDto(String name) {
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "RoleDto [id=" + id + ", name=" + name + "]";
    }
}
