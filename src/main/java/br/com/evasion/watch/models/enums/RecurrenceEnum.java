package br.com.evasion.watch.models.enums;

public enum RecurrenceEnum {
	MENSAL(1), SEMESTRAL(6), ANUAL(12);
	
	int months;

	private RecurrenceEnum(int months) {
		this.months = months;
	}

	public int getMonths() {
		return months;
	}
	
}
