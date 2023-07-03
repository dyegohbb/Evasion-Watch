package br.com.evasion.watch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.evasion.watch.models.entities.User;
import br.com.evasion.watch.models.transfer.ApiResponseObject;
import br.com.evasion.watch.models.transfer.AuthenticationObject;
import br.com.evasion.watch.services.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@CrossOrigin("*")
	@PostMapping("/register")
    public ResponseEntity<ApiResponseObject> register(@RequestBody @Valid User user, BindingResult bindingResult) {
		ApiResponseObject response = userService.registerUser(user, bindingResult);
        return new ResponseEntity<>(response, response.getStatus());
    }
	
	@CrossOrigin("*")
	@PostMapping("/login")
    public ResponseEntity<ApiResponseObject> login(@RequestBody AuthenticationObject request) {
		ApiResponseObject response = userService.login(request);
        return new ResponseEntity<>(response, response.getStatus());
    }
	
}
