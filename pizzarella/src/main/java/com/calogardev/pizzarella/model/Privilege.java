package com.calogardev.pizzarella.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.calogardev.pizzarella.enums.Status;

@Entity
public class Privilege {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany(mappedBy = "privileges", fetch = FetchType.EAGER)
    private List<Role> roles;

    public Privilege() {
	super();
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

    public Status getStatus() {
	return status;
    }

    public void setStatus(Status status) {
	this.status = status;
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Privilege [id=" + id + ", name=" + name + ", status=" + status + ", roles=" + roles + "]";
    }
}
