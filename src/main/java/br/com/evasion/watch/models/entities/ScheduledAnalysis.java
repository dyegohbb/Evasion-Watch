package br.com.evasion.watch.models.entities;

import java.time.LocalDateTime;

import br.com.evasion.watch.models.enums.RecurrenceEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "scheduled_analysis")
public class ScheduledAnalysis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@NotNull(message = "Dia do agendamento não pode ser nula")
	@Column(nullable = false)
	private int day;
	
	@NotNull(message = "Próxima execução não pode ser nula")
	@Column(nullable = false)
	private LocalDateTime nextExecution;
	
	@NotNull(message = "Próxima execução não pode ser nula")
	@Column(nullable = false)
	private LocalDateTime lastExecution;
	
	@NotNull(message = "Recorrencia não pode ser nula")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RecurrenceEnum recurrence;
	
	public ScheduledAnalysis() {
		// Empty Constructor
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public RecurrenceEnum getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(RecurrenceEnum recurrence) {
		this.recurrence = recurrence;
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

	public LocalDateTime getLastExecution() {
		return lastExecution;
	}

	public void setLastExecution(LocalDateTime lastExecution) {
		this.lastExecution = lastExecution;
	}
	
}
