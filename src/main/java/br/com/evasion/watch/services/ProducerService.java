package br.com.evasion.watch.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import br.com.evasion.watch.models.transfer.KafkaMessageObject;


@Service
public class ProducerService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProducerService.class);
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String topico, KafkaMessageObject messageObject) throws JsonProcessingException {
		LOGGER.info("[PRODUCER-{}] Serializando objeto para o formato StringJson.", topico);
		ObjectWriter objectWriter = new ObjectMapper().findAndRegisterModules().writer().withDefaultPrettyPrinter();
		String message = objectWriter.writeValueAsString(messageObject);
		LOGGER.info("[PRODUCER-{}] Objeto serializado com sucesso.", topico);
		LOGGER.info("[PRODUCER-{}] Enviando mensagem para o tópico.", topico);
		kafkaTemplate.send(topico, message);
		LOGGER.info("[PRODUCER-{}] Mensagem enviada com sucesso!", topico);
	}

}