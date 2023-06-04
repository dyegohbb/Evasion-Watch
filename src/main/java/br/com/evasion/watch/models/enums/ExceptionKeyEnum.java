package br.com.evasion.watch.models.enums;

import org.springframework.http.HttpStatus;

public enum ExceptionKeyEnum {

	LOGIN_EXISTS("O login informado já existe", HttpStatus.CONFLICT),
	EMAIL_EXISTS("O email fornecido já existe", HttpStatus.CONFLICT);

	private String description;
	private HttpStatus status;
	
	ExceptionKeyEnum(String description, HttpStatus status) {
		this.description = description;
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public HttpStatus getStatus() {
		return status;
	}
	
}
