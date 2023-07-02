package br.com.evasion.watch.models.entities;

import java.time.LocalDateTime;
import java.util.UUID;

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
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@Column(nullable = false)
	private String uuid;

	@NotNull(message = "A operação não pode ser nula")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TaskOperationEnum operation;

	private String metadata;

	@NotNull(message = "A situação não pode ser nula")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SituationEnum situation;

	@NotNull(message = "O progresso da tarefa não pode ser nulo")
	@Column(nullable = false)
	private int progress = 0;

	@Column(nullable = true)
	private String exeptionMsg;
	
	private String userLogin;

	public Task() {
		// Empty Constructor
	}

	public Task(TaskOperationEnum operation, String metadata, SituationEnum situation, String userLogin) {
		this.operation = operation;
		this.metadata = metadata;
		this.situation = situation;
		this.progress = 0;
		this.userLogin = userLogin;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.uuid = UUID.randomUUID().toString();
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

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
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

	public String getUUID() {
		return uuid;
	}

	public void setUUID(String uUID) {
		uuid = uUID;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
}
