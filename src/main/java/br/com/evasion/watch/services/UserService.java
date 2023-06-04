package br.com.evasion.watch.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import br.com.evasion.watch.exceptions.EmailExistsException;
import br.com.evasion.watch.exceptions.EwException;
import br.com.evasion.watch.exceptions.LoginExistsException;
import br.com.evasion.watch.models.entities.User;
import br.com.evasion.watch.models.transfer.ApiResponseObject;
import br.com.evasion.watch.repositories.UserRepository;

@Service
public class UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public ApiResponseObject registerUser(User user, BindingResult bindingResult) {
		LOGGER.info("Iniciando a criação do usuário: {}", user.getLogin());
		ApiResponseObject response = new ApiResponseObject();

		if (bindingResult.hasErrors()) {
			LOGGER.error("A validação do usuário encontrou os seguintes problemas:");
			StringBuilder errorMessage = new StringBuilder();
			for (ObjectError error : bindingResult.getAllErrors()) {
				LOGGER.error(error.getDefaultMessage());
				errorMessage.append(error.getDefaultMessage()).append("; ");
			}

			response.setMessage(errorMessage.toString());
			response.setStatus(HttpStatus.BAD_REQUEST);

		} else {
			
			try {
				if (userRepository.existsByLogin(user.getLogin())) {
					throw new LoginExistsException();
				}

				if (userRepository.existsByEmail(user.getEmail())) {
					throw new EmailExistsException();
				}
				
				user.prepareFields(encoder);
				userRepository.save(user);

				response.setMessage("Usuário cadastrado com sucesso!");
				response.setStatus(HttpStatus.CREATED);

			} catch (EwException e) {
				LOGGER.error("Erro ao criar usuário: ", e);
				response = new ApiResponseObject(e);
			} catch (Exception e) {
				LOGGER.error("Erro ao criar usuário: ", e);
				response = new ApiResponseObject(new EwException());
			}
		}

		return response;
	}

}
