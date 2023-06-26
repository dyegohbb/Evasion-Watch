package br.com.evasion.watch.validation;

import org.springframework.security.core.userdetails.UserDetails;

import br.com.evasion.watch.exceptions.UserNotFoundException;
import br.com.evasion.watch.models.entities.User;
import br.com.evasion.watch.repositories.UserRepository;

public class UserValidation {
	
	private UserRepository userRepository;

	public UserValidation(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User isValid(UserDetails user) throws UserNotFoundException {
		return userRepository.findByLogin(user.getUsername()).orElseThrow(() -> new UserNotFoundException(user.getUsername()));
	}
}
