package br.com.evasion.watch.models.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.evasion.watch.models.enums.RecurrenceEnum;
import br.com.evasion.watch.models.transfer.ScheduledAnalysisLightObject;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "scheduled_analysis", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"day", "recurrence"})})
public class ScheduledAnalysis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String uuid;

	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@NotNull(message = "Dia do agendamento não pode ser nula")
	@Column(nullable = false)
	private int day;
	
	@NotNull(message = "Próxima execução não pode ser nula")
	@Column(nullable = false)
	private LocalDateTime nextExecution;
	
	@NotNull(message = "Recorrencia não pode ser nula")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RecurrenceEnum recurrence;
	
	@Column(nullable = false, columnDefinition = "TINYINT")
	private boolean deleted = false;
	
	public ScheduledAnalysis() {
		// Empty Constructor
	}

	public ScheduledAnalysis(ScheduledAnalysisLightObject request) {
		this.day = request.getDay();
		this.recurrence = request.getRecurrence();
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.setUuid(UUID.randomUUID().toString());
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
