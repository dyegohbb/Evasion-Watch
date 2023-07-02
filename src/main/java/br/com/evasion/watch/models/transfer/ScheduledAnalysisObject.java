package br.com.evasion.watch.models.transfer;

import java.io.Serializable;

import br.com.evasion.watch.models.enums.RecurrenceEnum;
import jakarta.validation.constraints.NotNull;

public class ScheduledAnalysisObject implements Serializable{
	
	private static final long serialVersionUID = -2186211253648201901L;

	@NotNull(message = "Dia do agendamento não pode ser nula")
	private int day;
	
	@NotNull(message = "Recorrencia não pode ser nula")
	private RecurrenceEnum recurrence;

	public ScheduledAnalysisObject() {
		// Empty Constructor
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public RecurrenceEnum getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(RecurrenceEnum recurrence) {
		this.recurrence = recurrence;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
