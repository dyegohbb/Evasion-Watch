package br.com.evasion.watch.models.transfer;

import java.time.LocalDateTime;

import br.com.evasion.watch.models.entities.ScheduledAnalysis;
import br.com.evasion.watch.models.enums.RecurrenceEnum;

public class ScheduledAnalysisObject{
	
	private static final long serialVersionUID = -7707543858727215387L;

	private String uuid;

	private LocalDateTime createdAt;
	
	private int day;
	
	private LocalDateTime nextExecution;
	
	private RecurrenceEnum recurrence;

	public ScheduledAnalysisObject() {
	}
	
	public ScheduledAnalysisObject(ScheduledAnalysis scheduled) {
		this.uuid = scheduled.getUuid();
		this.createdAt = scheduled.getCreatedAt();
		this.day = scheduled.getDay();
		this.nextExecution = scheduled.getNextExecution();
		this.recurrence = scheduled.getRecurrence();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public LocalDateTime getNextExecution() {
		return nextExecution;
	}

	public void setNextExecution(LocalDateTime nextExecution) {
		this.nextExecution = nextExecution;
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
