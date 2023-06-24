package br.com.evasion.watch.models.transfer;

public class AuthenticationRequest {

	private String login;
	
	private String password;

	public AuthenticationRequest() {
		//EMPTY CONSTRUCTOR
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
