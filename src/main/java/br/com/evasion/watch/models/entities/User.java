package br.com.evasion.watch.models.entities;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.evasion.watch.models.enums.UserRoleEnum;
import br.com.evasion.watch.validation.LoginValidation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserRoleEnum userRole;

	@LoginValidation(message = "Login inválido")
	@Column(nullable = false, unique = true)
	private String login;

	@NotBlank(message = "A senha deve ser preenchida")
	@Column(nullable = false)
	private String password;

	@NotBlank(message = "O nome deve ser preenchido")
	@Column(nullable = false)
	private String name;

	@Email(message = "Email inválido")
	@Column(nullable = false, unique = true)
	private String email;
	
	public User() {
		// Empty Constructor
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.userRole = UserRoleEnum.BASIC_USER;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public void prepareFields(BCryptPasswordEncoder encoder) {
		this.email = this.email.toLowerCase();
		this.login = this.login.toLowerCase();
		this.password = encoder.encode(this.password);
	}
	
}
