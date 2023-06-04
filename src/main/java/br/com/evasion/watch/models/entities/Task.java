package br.com.evasion.watch.models.entities;

import java.time.LocalDateTime;

import br.com.evasion.watch.models.enums.SituationEnum;
import br.com.evasion.watch.models.enums.TaskOperationEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "task")
public class Task {

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TaskOperationEnum operation;
	
	@NotBlank
	@Column(nullable = false)
	private String data;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SituationEnum situation;
	
	@NotNull
	@Column(nullable = false)
	private int progress = 0;
	
	@Column(nullable = true)
	private String exeptionMsg;
	
	public Task() {
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

	public TaskOperationEnum getOperation() {
		return operation;
	}

	public void setOperation(TaskOperationEnum operation) {
		this.operation = operation;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public SituationEnum getSituation() {
		return situation;
	}

	public void setSituation(SituationEnum situation) {
		this.situation = situation;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public String getExeptionMsg() {
		return exeptionMsg;
	}

	public void setExeptionMsg(String exeptionMsg) {
		this.exeptionMsg = exeptionMsg;
	}
	
}
