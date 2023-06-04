package br.com.evasion.watch.exceptions;

import br.com.evasion.watch.models.enums.ExceptionKeyEnum;

public class EmailExistsException extends EwException{

	private static final long serialVersionUID = 7377157373866859856L;

	private static final ExceptionKeyEnum exceptionKey = ExceptionKeyEnum.EMAIL_EXISTS;
	
	public EmailExistsException() {
		super(exceptionKey.getDescription(), exceptionKey.getStatus());
	}
}