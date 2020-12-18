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
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;

	@ManyToMany(mappedBy="roles")
	private Collection<ApplicationUser> users;
	
	@ManyToMany
	@JoinTable(
			name="ROLES_Privilages",
			joinColumns=@JoinColumn(name="ROLE_ID", referencedColumnName="id"),
			inverseJoinColumns=@JoinColumn(name="PRIVILAGE_ID", referencedColumnName="id"))
	private Collection<Privilage> privilages;

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(Integer id, String name, Collection<Privilage> privilages) {
		super();
		this.id = id;
		this.name = name;
		this.privilages = privilages;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<ApplicationUser> getUsers() {
		return users;
	}

	public void setUsers(Collection<ApplicationUser> users) {
		this.users = users;
	}

	public Collection<Privilage> getPrivilages() {
		return privilages;
	}

	public void setPrivilages(Collection<Privilage> privilages) {
		this.privilages = privilages;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", users=" + users + ", privilages=" + privilages + "]";
	}
	
}
