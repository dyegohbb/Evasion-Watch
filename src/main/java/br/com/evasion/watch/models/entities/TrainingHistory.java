package br.com.evasion.watch.models.entities;

import java.time.LocalDateTime;

import br.com.evasion.watch.models.embeddables.TrainingMetrics;
import br.com.evasion.watch.models.enums.SituationEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
@Table(name="training_history")
public class TrainingHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@NotNull(message = "A situação do treino não pode ser nula")
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private SituationEnum situation;
	
	@Embedded
	private TrainingMetrics metrics;
	
	public TrainingHistory() {
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

	public SituationEnum getSituation() {
		return situation;
	}

	public void setSituation(SituationEnum situation) {
		this.situation = situation;
	}

	public TrainingMetrics getMetrics() {
		return metrics;
	}

	public void setMetrics(TrainingMetrics metrics) {
		this.metrics = metrics;
	}
	
}
