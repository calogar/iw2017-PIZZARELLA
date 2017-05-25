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

    public User() {
	super();
    }

    public User(String name, String surnames, String dni, String nickname, String password, Status status) {
	super();
	this.name = name;
	this.surnames = surnames;
	this.dni = dni;
	this.nickname = nickname;
	this.password = password;
	this.status = status;
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

    /**
     * @return the password
     */
    public String getPassword() {
	return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
	this.password = password;
    }

    /**
     * @return the status
     */
    public Status getStatus() {
	return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(Status status) {
	this.status = status;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "User [id=" + id + ", name=" + name + ", surnames=" + surnames + ", dni=" + dni + ", nickname="
		+ nickname + ", password=" + password + ", status=" + status + ", roles=" + roles + "]";
    }

    /**
     * @return the roles
     */
    public List<Role> getRoles() {
	return roles;
    }

    /**
     * @param roles
     *            the roles to set
     */
    public void setRoles(List<Role> roles) {
	this.roles = roles;
    }
}
