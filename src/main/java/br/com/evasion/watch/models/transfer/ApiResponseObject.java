package br.com.evasion.watch.models.transfer;

import org.springframework.http.HttpStatus;

import br.com.evasion.watch.exceptions.EwException;

public class ApiResponseObject {

	private String message;
	
	private HttpStatus status;
	
	public ApiResponseObject() {
		this.status = HttpStatus.OK;
		this.message = "Sucesso.";
	}
	
	public ApiResponseObject(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
	}
	
	public ApiResponseObject(EwException exception) {
		this.message = exception.getMessage();
		this.status = exception.getHttpStatus();
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
}
