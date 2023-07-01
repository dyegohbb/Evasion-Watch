package br.com.evasion.watch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
