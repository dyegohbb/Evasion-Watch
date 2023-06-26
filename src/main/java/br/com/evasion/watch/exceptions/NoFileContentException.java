package br.com.evasion.watch.exceptions;

import br.com.evasion.watch.models.enums.ExceptionKeyEnum;

public class NoFileContentException extends EwException {

	private static final long serialVersionUID = -296292854626457816L;

	private static final ExceptionKeyEnum exceptionKey = ExceptionKeyEnum.NO_FILE_CONTENT;

	public NoFileContentException() {
		super(exceptionKey.getDescription(), exceptionKey.getStatus());
	}
}
