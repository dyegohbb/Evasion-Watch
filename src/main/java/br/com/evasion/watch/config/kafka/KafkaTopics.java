package br.com.evasion.watch.config.kafka;

public enum KafkaTopics {

	FULL_ANALYSIS("full_analysis"), CUSTOMIZED_ANALYSIS("customized_analysis"), IA_TRAIN("ia_train"), FAST_ANALYSIS("fast_analysis");

	private String description;

	KafkaTopics(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
