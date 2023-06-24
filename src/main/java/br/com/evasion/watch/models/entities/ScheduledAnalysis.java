package br.com.evasion.watch.models.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.evasion.watch.models.enums.RecurrenceEnum;
import br.com.evasion.watch.validation.EndDateValidation;
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
	
	@NotNull(message = "Data de inicio não pode ser nula")
	@Column(nullable = false)
	private LocalDate startDate;
	
	@EndDateValidation(startDate = "startDate", message = "Data final inválida")
	@Column(nullable = true)
	private LocalDate endDate;
	
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public RecurrenceEnum getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(RecurrenceEnum recurrence) {
		this.recurrence = recurrence;
	}
	
}
