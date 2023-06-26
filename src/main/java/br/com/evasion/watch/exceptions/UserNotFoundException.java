package br.com.evasion.watch.exceptions;

import br.com.evasion.watch.models.enums.ExceptionKeyEnum;

public class UserNotFoundException extends EwException{
	
	private static final long serialVersionUID = -593256085746717022L;
	
	private static final ExceptionKeyEnum exceptionKey = ExceptionKeyEnum.USER_NOT_FOUND;

	public UserNotFoundException(String userLogin) {
		super(String.format(exceptionKey.getDescription(), userLogin), exceptionKey.getStatus());
	}
	
}
