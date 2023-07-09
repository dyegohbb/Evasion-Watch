package br.com.evasion.watch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.evasion.watch.models.transfer.ApiResponseObject;
import br.com.evasion.watch.services.StudentDataService;

@CrossOrigin(maxAge = 3600, allowedHeaders = { "Requestor-Type", "Authorization" }, exposedHeaders = {
		"X-Get-Header" }, originPatterns = {
				"*" }, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE })
@RestController
@RequestMapping("/student/data")
public class StudentDataController {

	@Autowired
	private StudentDataService studentDataService;

	@PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ApiResponseObject> importStudentData(@RequestHeader("Authorization") String authorization,
			@RequestPart("file") MultipartFile studentDataCsv, Authentication authentication) {
		ApiResponseObject response = studentDataService.importStudentData(authorization, studentDataCsv);
		return new ResponseEntity<>(response, response.getStatus());
	}

}
