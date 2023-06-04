package br.com.evasion.watch.models.enums;

public enum TaskOperationEnum {
	CSV_IMPORT("Importação de dados por arquivo CSV"),
	IA_TRAIN("Treinamento de IA por arquivo CSV"),
	CUSTOMIZED_ANALYSIS("Análise personalizada por arquivo CSV"),
	SHEDULED_ANALYSIS("Análise agendada por dados populados no banco de dados");
	
	private String description;

	TaskOperationEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
}
