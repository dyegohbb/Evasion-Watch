package br.com.evasion.watch.models.enums;

public enum UserRoleEnum {
	
	BASIC_USER("Usuário"),
	ADMIN_USER("Administrador");

	private String description;
	
	UserRoleEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
