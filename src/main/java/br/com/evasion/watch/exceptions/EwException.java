package br.com.evasion.watch.exceptions;

import org.springframework.http.HttpStatus;

public class EwException extends Exception{
	
	private static final long serialVersionUID = -7344352460077412958L;

	private final HttpStatus httpStatus;
	
	public EwException() {
		super("Ocorreu um erro no processamento da solicitação, contacte os administradores do sistema.");
		this.httpStatus = HttpStatus.BAD_REQUEST;
	}
	
	public EwException(Exception e) {
		super(e);
		this.httpStatus = HttpStatus.BAD_REQUEST;
	}
	
	public EwException(String msg, HttpStatus status) {
		super(msg);
		this.httpStatus = status;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
}
