package br.com.evasion.watch.exceptions;

import br.com.evasion.watch.models.enums.ExceptionKeyEnum;

public class LoginExistsException extends EwException{

	private static final long serialVersionUID = 7377157373866859856L;

	private static final ExceptionKeyEnum exceptionKey = ExceptionKeyEnum.LOGIN_EXISTS;
	
	public LoginExistsException() {
		super(exceptionKey.getDescription(), exceptionKey.getStatus());
	}
}
