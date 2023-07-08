package br.com.evasion.watch.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.evasion.watch.models.transfer.AnalysisResultHistoryObject;
import br.com.evasion.watch.models.transfer.ApiResponseObject;
import br.com.evasion.watch.models.transfer.ScheduledAnalysisLightObject;
import br.com.evasion.watch.models.transfer.ScheduledAnalysisObject;
import br.com.evasion.watch.services.AnalysisService;
import jakarta.validation.Valid;

@CrossOrigin(maxAge = 3600, allowedHeaders = { "Requestor-Type",
"Authorization" }, exposedHeaders = {"X-Get-Header"}, originPatterns = {"http://localhost:4200"}, methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping("/analysis")
public class AnalysisController {
	
	@Autowired
	private AnalysisService analysisService;
	
	/**
	 * @deprecated Este método será removido na próxima versão. Pois a análise completa será usada apenas com agendamento.
	 */
	@Deprecated(forRemoval = true)
	@PostMapping(value = "/full")
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
	
	@PostMapping("/schedule")
    public ResponseEntity<ApiResponseObject> schedule(@RequestBody @Valid ScheduledAnalysisLightObject request,  BindingResult bindingResult) {
		ApiResponseObject response = analysisService.schedule(request, bindingResult);
        return new ResponseEntity<>(response, response.getStatus());
    }
	
	@GetMapping("/schedule/list")
    public ResponseEntity<List<ScheduledAnalysisObject>> listSchedule() {
		List<ScheduledAnalysisObject> objList = analysisService.listSchedule();
		
		if(objList.isEmpty()) {
			new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
        return new ResponseEntity<>(objList, HttpStatus.OK);
    }
	
	@DeleteMapping("/schedule/delete/{uuid}")
    public ResponseEntity<ApiResponseObject> deleteSchedule(@PathVariable String uuid) {
		ApiResponseObject response = analysisService.deleteSchedule(uuid);
        return new ResponseEntity<>(response, response.getStatus());
	}
	
	@PostMapping("/schedule/restaure/{uuid}")
    public ResponseEntity<ApiResponseObject> restaureSchedule(@PathVariable String uuid) {
		ApiResponseObject response = analysisService.restaureSchedule(uuid);
        return new ResponseEntity<>(response, response.getStatus());
	}
	
	@GetMapping("/student/history")
    public ResponseEntity<List<AnalysisResultHistoryObject>> listStudentAnalisysHistory() {
		List<AnalysisResultHistoryObject> objList = analysisService.listStudentAnalisysHistory();
		
		if(objList.isEmpty()) {
			new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
        return new ResponseEntity<>(objList, HttpStatus.OK);
    }

}
