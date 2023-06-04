package br.com.evasion.watch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.evasion.watch.models.entities.User;
import br.com.evasion.watch.models.transfer.ApiResponseObject;
import br.com.evasion.watch.services.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
    public ResponseEntity<ApiResponseObject> registerUser(@RequestBody @Valid User user, BindingResult bindingResult) {
		ApiResponseObject response = userService.registerUser(user, bindingResult);
        return new ResponseEntity<>(response, response.getStatus());
    }
	
}
