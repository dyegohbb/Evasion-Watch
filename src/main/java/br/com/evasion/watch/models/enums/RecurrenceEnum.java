package br.com.evasion.watch.models.enums;

public enum RecurrenceEnum {
	MONTHLY(1), SEMESTERLY(6), YEARLY(12);
	
	int months;

	private RecurrenceEnum(int months) {
		this.months = months;
	}

	public int getMonths() {
		return months;
	}
	
}
