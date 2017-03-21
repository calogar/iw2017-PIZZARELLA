package com.calogardev.pizzarella.dto;

import java.lang.reflect.Field;

/**
 * Dto used for creating Users. Doesn't contain auto-generated fields like
 * status.
 * 
 * @author calogar
 *
 */
public class CreateUserDto implements Dto {

    private String name;

    private String surnames;

    private String dni;

    private String nickname;

    private String password;

    public CreateUserDto() {

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
     * @return the surnames
     */
    public String getSurnames() {
	return surnames;
    }

    /**
     * @param surnames
     *            the surnames to set
     */
    public void setSurnames(String surnames) {
	this.surnames = surnames;
    }

    /**
     * @return the dni
     */
    public String getDni() {
	return dni;
    }

    /**
     * @param dni
     *            the dni to set
     */
    public void setDni(String dni) {
	this.dni = dni;
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
	return nickname;
    }

    /**
     * @param nickname
     *            the nickname to set
     */
    public void setNickname(String nickname) {
	this.nickname = nickname;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    @Override
    public Field[] getDeclaredFields() {
	return this.getClass().getDeclaredFields();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "CreateUserDto [name=" + name + ", surnames=" + surnames + ", dni=" + dni + ", nickname=" + nickname
		+ ", password=" + password + "]";
    }
}
