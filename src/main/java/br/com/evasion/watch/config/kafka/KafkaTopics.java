package br.com.evasion.watch.config.kafka;

public enum KafkaTopics {
	
	FULL_ANALYSIS("full_analysis");

	private String description;
	
	KafkaTopics(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
