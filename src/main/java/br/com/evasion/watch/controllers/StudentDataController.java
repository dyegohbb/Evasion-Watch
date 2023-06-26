package br.com.evasion.watch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.evasion.watch.models.transfer.ApiResponseObject;
import br.com.evasion.watch.services.StudentDataService;

@RestController
@RequestMapping("/student/data")
public class StudentDataController {

	@Autowired
	private StudentDataService studentDataService;

	@PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ApiResponseObject> importStudentData(@RequestPart("file") MultipartFile studentDataCsv,
			Authentication authentication) {
		UserDetails user = (UserDetails) authentication.getPrincipal();

		ApiResponseObject response = studentDataService.importStudentData(user, studentDataCsv);
		return new ResponseEntity<>(response, response.getStatus());
	}

}