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
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@NotNull
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
	
}
