package br.com.evasion.watch.validation;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LoginValidator implements ConstraintValidator<LoginValidation, String> {

	private static final String LOGIN_PATTERN = "^[a-zA-Z0-9._!#@]{6,12}$";
	private static final Pattern pattern = Pattern.compile(LOGIN_PATTERN);

	@Override
	public boolean isValid(String login, ConstraintValidatorContext context) {
		if (login == null || login.isEmpty()) {
			return false;
		}

		return pattern.matcher(login).matches();
	}

}
