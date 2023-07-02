package br.com.evasion.watch.models.enums;

public enum RecurrenceEnum {
	MONTHLY(1), WEEKLY(6), YEARLY(12);
	
	int months;

	private RecurrenceEnum(int months) {
		this.months = months;
	}

	public int getMonths() {
		return months;
	}
	
}
