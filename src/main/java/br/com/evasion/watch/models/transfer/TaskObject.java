package br.com.evasion.watch.models.transfer;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.com.evasion.watch.models.entities.Task;
import br.com.evasion.watch.models.enums.SituationEnum;
import br.com.evasion.watch.models.enums.TaskOperationEnum;

public class TaskObject implements Serializable{

	private static final long serialVersionUID = -8390004561031800192L;

	private String uuid;
	
	private LocalDateTime createdAt;

	private TaskOperationEnum operation;

	private String metadata;

	private SituationEnum situation;

	private int progress = 0;

	private String exeptionMsg;

	public TaskObject() {
		// Empty Constructor
	}
	
	public TaskObject(Task task) {
		this.uuid = task.getUUID();
		this.createdAt = task.getCreatedAt();
		this.operation = task.getOperation();
		this.metadata = task.getMetadata();
		this.situation = task.getSituation();
		this.progress = task.getProgress();
		this.exeptionMsg = task.getExeptionMsg();
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
