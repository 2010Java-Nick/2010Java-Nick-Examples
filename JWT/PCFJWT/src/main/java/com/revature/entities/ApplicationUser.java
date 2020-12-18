package com.revature.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class ApplicationUser {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String username;
	private String password;
	
	@ManyToMany
	@JoinTable(
			name="USER_ROLES",
			joinColumns=@JoinColumn(name="USER_ID", referencedColumnName="id"),
			inverseJoinColumns=@JoinColumn(name="ROLE_ID", referencedColumnName="id"))
	private Collection<Role> roles;

	public ApplicationUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApplicationUser(String name, String password) {
		super();
		this.username = name;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "ApplicationUser [id=" + id + ", name=" + username + ", password=" + password + "]";
	}
	
	
	
	
}
