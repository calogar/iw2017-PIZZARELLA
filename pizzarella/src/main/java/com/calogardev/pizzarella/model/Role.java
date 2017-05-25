package com.calogardev.pizzarella.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.calogardev.pizzarella.enums.Status;

@Entity
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // @ManyToMany(mappedBy = "roles")
    // private List<User> users;

    // // We use eager because a Role without Privileges is useless
    // @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    // @JoinTable(name = "role_privilege", joinColumns = @JoinColumn(name =
    // "role_id", referencedColumnName = "id"), inverseJoinColumns =
    // @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    // private List<Privilege> privileges = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status status;

    public Role() {
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Role [id=" + id + ", name=" + name + ", status=" + status + "]";
    }
}
