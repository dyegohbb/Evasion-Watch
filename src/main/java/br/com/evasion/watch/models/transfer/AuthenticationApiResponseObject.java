package br.com.evasion.watch.models.transfer;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationApiResponseObject extends ApiResponseObject {
	
	@JsonProperty("name")
	private String nameOfUser;
	
	@JsonProperty("access_token")
	private String accessToken;
	
	@JsonProperty("refresh_token")
	private String refreshToken;

	public AuthenticationApiResponseObject() {
		//Empty constructor
	}
	
	public AuthenticationApiResponseObject(String message, HttpStatus status, String accessToken, String refreshToken, String nameOfUser) {
		super(message, status);
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.nameOfUser = nameOfUser;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	public String getNameOfUser() {
		return nameOfUser;
	}

	public void setNameOfUser(String nameOfUser) {
		this.nameOfUser = nameOfUser;
	}
}
