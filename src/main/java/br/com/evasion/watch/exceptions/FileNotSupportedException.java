package br.com.evasion.watch.exceptions;

import br.com.evasion.watch.models.enums.ExceptionKeyEnum;

public class FileNotSupportedException extends EwException{

	private static final long serialVersionUID = -5299115734440415360L;
	
	private static final ExceptionKeyEnum exceptionKey = ExceptionKeyEnum.FILE_NOT_SUPPORTED;

	public FileNotSupportedException(String extension) {
		super(String.format(exceptionKey.getDescription(), extension), exceptionKey.getStatus());
	}
}
