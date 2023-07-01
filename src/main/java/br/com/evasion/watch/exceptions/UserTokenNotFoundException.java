package br.com.evasion.watch.exceptions;

import br.com.evasion.watch.models.enums.ExceptionKeyEnum;

public class UserTokenNotFoundException extends EwException{
	
	private static final long serialVersionUID = 7044502473868507531L;
	
	private static final ExceptionKeyEnum exceptionKey = ExceptionKeyEnum.USER_TOKEN_NOT_FOUND;

	public UserTokenNotFoundException(String userToken) {
		super(String.format(exceptionKey.getDescription(), userToken), exceptionKey.getStatus());
	}
}
