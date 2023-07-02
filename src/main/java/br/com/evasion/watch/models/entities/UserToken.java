package br.com.evasion.watch.models.entities;

import br.com.evasion.watch.models.enums.TokenTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_token")
public class UserToken {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(unique = true)
	private String token;

	@Enumerated(EnumType.STRING)
	private TokenTypeEnum tokenType = TokenTypeEnum.BEARER;

	private boolean revoked;

	private boolean expired;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User user;

	public UserToken() {
	}

	public UserToken(User user, String jwtToken, TokenTypeEnum tokenType, boolean revoked, boolean expired) {
		this.user = user;
		this.token = jwtToken;
		this.tokenType = tokenType;
		this.revoked = revoked;
		this.expired = expired;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public TokenTypeEnum getTokenType() {
		return tokenType;
	}

	public void setTokenType(TokenTypeEnum tokenType) {
		this.tokenType = tokenType;
	}

	public boolean isRevoked() {
		return revoked;
	}

	public void setRevoked(boolean revoked) {
		this.revoked = revoked;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
