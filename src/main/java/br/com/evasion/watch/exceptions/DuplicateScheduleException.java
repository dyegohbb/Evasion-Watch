package br.com.evasion.watch.exceptions;

import br.com.evasion.watch.models.enums.ExceptionKeyEnum;
import br.com.evasion.watch.models.enums.RecurrenceEnum;

public class DuplicateScheduleException extends EwException{

	private static final long serialVersionUID = 6330364312051203796L;
	
	private static final ExceptionKeyEnum exceptionKey = ExceptionKeyEnum.DUPLICATE_SCHEDULE;
	
	public DuplicateScheduleException(int day, RecurrenceEnum recurrence) {
		super(String.format(exceptionKey.getDescription(), day, recurrence.toString()), exceptionKey.getStatus());
	}
}
