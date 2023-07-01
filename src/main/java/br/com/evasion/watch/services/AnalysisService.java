package br.com.evasion.watch.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.evasion.watch.config.kafka.KafkaTopics;
import br.com.evasion.watch.models.enums.TaskOperationEnum;
import br.com.evasion.watch.models.transfer.ApiResponseObject;
import br.com.evasion.watch.models.transfer.KafkaMessageObject;
import br.com.evasion.watch.models.transfer.UserObject;

@Service
public class AnalysisService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AnalysisService.class);
	
	@Autowired
	ProducerService producerService;
	
	@Autowired
	private UserService userService;

	public ApiResponseObject fullAnalysis(String userToken) {
		LOGGER.info("[ANÁLISE COMPLETA] Preparando para solicitar a execução ao serviço responsável.");
		try {
			UserObject user = userService.findUserObjectByToken(userToken);
			KafkaMessageObject messageObject = new KafkaMessageObject(user, "", TaskOperationEnum.SHEDULED_ANALYSIS);
			
			producerService.sendMessage(KafkaTopics.FULL_ANALYSIS.getDescription(), messageObject);
		} catch (Exception e) {
			LOGGER.error("[ANÁLISE COMPLETA] Erro ao enviar solicitação, motivo: ", e);
			return new ApiResponseObject(e);
		}
		LOGGER.info("[ANÁLISE COMPLETA] Solicitação enviada com sucesso!");
		return new ApiResponseObject("Solicitação enviada com sucesso, aguarde a finalização.", HttpStatus.CREATED);
	}
}
