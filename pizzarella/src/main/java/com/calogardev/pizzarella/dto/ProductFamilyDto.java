package com.calogardev.pizzarella.dto;

import java.lang.reflect.Field;

public class ProductFamilyDto implements Dto {

    private Long id;

    private String name;

    private String code;

    public ProductFamilyDto() {
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * @return the code
     */
    public String getCode() {
	return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
	this.code = code;
    }

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
	this.id = id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "ProductFamilyDto [id=" + id + ", name=" + name + ", code=" + code + "]";
    }

    @Override
    public Field[] getDeclaredFields() {
	// TODO Auto-generated method stub
	return null;
    }
}
