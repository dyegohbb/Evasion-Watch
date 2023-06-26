package br.com.evasion.watch.models.enums;

import java.util.Arrays;

public enum StudentDataExtensionType {
	TEXT("txt"), CSV("csv");
	
	private String extension;

	private StudentDataExtensionType(String extension) {
		this.extension = extension;
	}
	
	public String getExtension() {
		return extension;
	}
	
	public static boolean isValidExtension(String extension) {
	    return Arrays.stream(StudentDataExtensionType.values())
	            .anyMatch(type -> type.getExtension().equalsIgnoreCase(extension));
	}
	
}
