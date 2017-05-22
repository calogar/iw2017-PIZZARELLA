package com.calogardev.pizzarella.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.calogardev.pizzarella.enums.Status;

@Entity
public class Privilege {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private Status status;

	@ManyToMany(mappedBy = "privileges")
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Privilege [id=" + id + ", name=" + name + ", status=" + status + ", roles=" + roles + "]";
	}

}
