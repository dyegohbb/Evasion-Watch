package br.com.evasion.watch.models.transfer;

import java.io.Serializable;

import br.com.evasion.watch.models.enums.TaskOperationEnum;

public class KafkaMessageObject implements Serializable {
	
	private static final long serialVersionUID = 323130208053306489L;
	
	private UserObject user;
	
	private String metadata;
	
	private TaskOperationEnum operation;

	public KafkaMessageObject() {
		// Empty Constructor
	}

	public KafkaMessageObject(UserObject user, String metadata, TaskOperationEnum operation) {
		this.user = user;
		this.metadata = metadata;
		this.operation = operation;
	}

	public UserObject getUser() {
		return user;
	}

	public void setUser(UserObject user) {
		this.user = user;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public TaskOperationEnum getOperation() {
		return operation;
	}

	public void setOperation(TaskOperationEnum operation) {
		this.operation = operation;
	}
	
}
