package br.com.evasion.watch.models.enums;

import org.springframework.http.HttpStatus;

public enum ExceptionKeyEnum {

	LOGIN_EXISTS("O login informado já existe", HttpStatus.CONFLICT),
	EMAIL_EXISTS("O email fornecido já existe", HttpStatus.CONFLICT),
	FILE_NOT_SUPPORTED("A extensão de arquivo %s não é suportada, apenas aceitamos CSV ou TXT. Por favor, verifique o tipo de arquivo enviado.", HttpStatus.BAD_REQUEST),
	NO_FILE_CONTENT("Arquivo obrigatório não foi enviado ou não possui conteúdo.", HttpStatus.NO_CONTENT),
	USER_NOT_FOUND("Usuário %s não foi encontrado na base de dados.", HttpStatus.NOT_FOUND),
	USER_TOKEN_NOT_FOUND("Token de usuário %s não foi encontrado na base de dados.", HttpStatus.NOT_FOUND),
	DUPLICATE_SCHEDULE("Já existe agendamento para esse dia[%s] e recorrencia[%s].", HttpStatus.CONFLICT);

	private String description;
	private HttpStatus status;
	
	ExceptionKeyEnum(String description, HttpStatus status) {
		this.description = description;
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public HttpStatus getStatus() {
		return status;
	}
	
}
