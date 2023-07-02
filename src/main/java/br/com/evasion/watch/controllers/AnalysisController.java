package br.com.evasion.watch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.evasion.watch.models.transfer.ApiResponseObject;
import br.com.evasion.watch.services.AnalysisService;

@RestController
@RequestMapping("/analysis")
public class AnalysisController {
	
	@Autowired
	private AnalysisService analysisService;
	
	/**
	 * @deprecated Este método será removido na próxima versão. Pois a análise completa será usada apenas com agendamento.
	 */
	@Deprecated(forRemoval = true)
	@GetMapping(value = "/full")
	public ResponseEntity<ApiResponseObject> importStudentData(@RequestHeader("Authorization") String authorization, Authentication authentication) {
		ApiResponseObject response = analysisService.fullAnalysis(authorization);
		return new ResponseEntity<>(response, response.getStatus());
	}
	
	@PostMapping(value = "/custom", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ApiResponseObject> importStudentData(@RequestHeader("Authorization") String authorization, @RequestPart("file") MultipartFile studentDataCsv,
			Authentication authentication) {
//		ApiResponseObject response = studentDataService.importStudentData(authorization, studentDataCsv);
		ApiResponseObject response = new ApiResponseObject();
		return new ResponseEntity<>(response, response.getStatus());
	}

}
