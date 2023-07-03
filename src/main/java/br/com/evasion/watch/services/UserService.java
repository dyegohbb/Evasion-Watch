package br.com.evasion.watch.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import br.com.evasion.watch.config.security.service.JwtService;
import br.com.evasion.watch.exceptions.EmailExistsException;
import br.com.evasion.watch.exceptions.EwException;
import br.com.evasion.watch.exceptions.LoginExistsException;
import br.com.evasion.watch.exceptions.UserNotFoundException;
import br.com.evasion.watch.exceptions.UserTokenNotFoundException;
import br.com.evasion.watch.models.entities.UserToken;
import br.com.evasion.watch.models.entities.User;
import br.com.evasion.watch.models.enums.TokenTypeEnum;
import br.com.evasion.watch.models.transfer.ApiResponseObject;
import br.com.evasion.watch.models.transfer.AuthenticationApiResponseObject;
import br.com.evasion.watch.models.transfer.AuthenticationObject;
import br.com.evasion.watch.models.transfer.UserObject;
import br.com.evasion.watch.repositories.TokenRepository;
import br.com.evasion.watch.repositories.UserRepository;

@Service
public class UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private TokenRepository tokenRepository;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	public User findUserByLogin(String login) throws UserNotFoundException {
		return userRepository.findByLogin(login).orElseThrow(() -> new UserNotFoundException(login));
	}

	public ApiResponseObject registerUser(User user, BindingResult bindingResult) {
		LOGGER.info("[REGISTRO DE USUÁRIO] Iniciando a criação do usuário: {}", user.getLogin());
		AuthenticationApiResponseObject response = new AuthenticationApiResponseObject();

		if (bindingResult.hasErrors()) {
			LOGGER.error("[REGISTRO DE USUÁRIO] A validação do usuário encontrou os seguintes problemas:");
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
				User savedUser = userRepository.save(user);

				String jwtToken = jwtService.generateToken(user);
				String refreshToken = jwtService.generateRefreshToken(user);
				saveUserToken(savedUser, jwtToken);
				response.setAccessToken(jwtToken);
				response.setRefreshToken(refreshToken);

				response.setMessage("Usuário cadastrado com sucesso!");
				response.setStatus(HttpStatus.CREATED);

			} catch (EwException e) {
				LOGGER.error("Erro ao criar usuário: ", e);
				return new ApiResponseObject(new EwException("Não foi possível criar usuário com essas credenciais", HttpStatus.BAD_REQUEST));
			} catch (Exception e) {
				LOGGER.error("Erro ao criar usuário: ", e);
				return new ApiResponseObject(new EwException());
			}
		}

		return response;
	}

	private void saveUserToken(User user, String jwtToken) {
		UserToken token = new UserToken(user, jwtToken, TokenTypeEnum.BEARER, false, false);
		tokenRepository.save(token);
	}

	public ApiResponseObject login(AuthenticationObject request) {

		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
			
			User user = userRepository.findByLogin(request.getLogin())
			        .orElseThrow();
			    var jwtToken = jwtService.generateToken(user);
			    var refreshToken = jwtService.generateRefreshToken(user);
			    revokeAllUserTokens(user);
			    saveUserToken(user, jwtToken);
			    
			String message = "Login efetuado com sucesso!";
			return new AuthenticationApiResponseObject(message, HttpStatus.OK, jwtToken, refreshToken);
		} catch (Exception e) {
			LOGGER.error("Erro ao efetuar login de usuário: ", e);
			return new ApiResponseObject(new EwException());
		}
	}
	
	private void revokeAllUserTokens(User user) {
	    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
	    if (validUserTokens.isEmpty())
	      return;
	    validUserTokens.forEach(token -> {
	      token.setExpired(true);
	      token.setRevoked(true);
	    });
	    tokenRepository.saveAll(validUserTokens);
	  }

	public User findUserByToken(String token) throws UserTokenNotFoundException {
		String jwt = token.substring(7);
		return tokenRepository.findUserByToken(jwt).orElseThrow(() -> new UserTokenNotFoundException(jwt));
	}
	
	public UserObject findUserObjectByToken(String token) throws UserTokenNotFoundException {
		return new UserObject(findUserByToken(token));
	}
}
