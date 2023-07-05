package br.com.evasion.watch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.evasion.watch.models.entities.User;
import br.com.evasion.watch.models.transfer.ApiResponseObject;
import br.com.evasion.watch.models.transfer.AuthenticationObject;
import br.com.evasion.watch.services.UserService;
import jakarta.validation.Valid;

@CrossOrigin(maxAge = 3600, allowedHeaders = { "Requestor-Type",
		"Authorization" }, exposedHeaders = {"X-Get-Header"}, originPatterns = {"http://localhost:4200"}, methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<ApiResponseObject> register(@RequestBody @Valid User user, BindingResult bindingResult) {
		ApiResponseObject response = userService.registerUser(user, bindingResult);
		return new ResponseEntity<>(response, response.getStatus());
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponseObject> login(@RequestBody AuthenticationObject request) {
		ApiResponseObject response = userService.login(request);
		return new ResponseEntity<>(response, response.getStatus());
	}

	@GetMapping("/check")
	public ResponseEntity<ApiResponseObject> checkToken() {
		ApiResponseObject response = new ApiResponseObject();
		return new ResponseEntity<>(response, response.getStatus());
	}
}
