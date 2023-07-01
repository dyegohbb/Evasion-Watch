package br.com.evasion.watch.models.transfer;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.com.evasion.watch.models.entities.User;
import br.com.evasion.watch.models.enums.UserRoleEnum;

public class UserObject implements Serializable{

	private static final long serialVersionUID = 7069327771313774154L;

	private int id;

	private LocalDateTime createdAt;

	private UserRoleEnum userRole;

	private String login;
	
	private String name;

	private String email;

	public UserObject() {
		// Empty Constructor
	}

	public UserObject(User user) {
		this.id = user.getId();
		this.createdAt = user.getCreatedAt();
		this.userRole = user.getUserRole();
		this.login = user.getLogin();
		this.name = user.getName();
		this.email = user.getEmail();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public UserRoleEnum getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRoleEnum userRole) {
		this.userRole = userRole;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
