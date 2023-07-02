package br.com.evasion.watch.services;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.evasion.watch.config.kafka.KafkaTopics;
import br.com.evasion.watch.exceptions.EwException;
import br.com.evasion.watch.models.entities.ScheduledAnalysis;
import br.com.evasion.watch.models.enums.TaskOperationEnum;
import br.com.evasion.watch.models.transfer.ApiResponseObject;
import br.com.evasion.watch.models.transfer.KafkaMessageObject;
import br.com.evasion.watch.models.transfer.UserObject;
import br.com.evasion.watch.repositories.ScheduledAnalysisRepository;

@Service
public class AnalysisService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AnalysisService.class);

	@Autowired
	private ProducerService producerService;

	@Autowired
	private UserService userService;

	@Autowired
	private ScheduledAnalysisRepository scheduledRepository;

	public ApiResponseObject fullAnalysis(String userToken) {
		LOGGER.info("[ANÁLISE COMPLETA] Preparando para solicitar a execução ao serviço responsável.");
		try {
			UserObject user = userService.findUserObjectByToken(userToken);
			KafkaMessageObject messageObject = new KafkaMessageObject(user, "", TaskOperationEnum.FULL_ANALYSIS);

			producerService.sendMessage(KafkaTopics.FULL_ANALYSIS.getDescription(), messageObject);
		} catch (Exception e) {
			LOGGER.error("[ANÁLISE COMPLETA] Erro ao enviar solicitação, motivo: ", e);
			return new ApiResponseObject(e);
		}
		LOGGER.info("[ANÁLISE COMPLETA] Solicitação enviada com sucesso!");
		return new ApiResponseObject("Solicitação enviada com sucesso, aguarde a finalização.", HttpStatus.CREATED);
	}

	public void sheduledAnalysis() {
		LOGGER.info("[ANÁLISE AGENDADA] Buscando agendamentos previstos/atrasados pra hoje.");
		try {
			List<ScheduledAnalysis> scheduleList = scheduledRepository
					.findByNextExecution(LocalDateTime.now().withHour(3).withMinute(0).withSecond(0));

			if (scheduleList.isEmpty()) {
				LOGGER.info("[ANÁLISE AGENDADA] Nenhum agendamento localizado, serviço finalizado.");
			} else {
				LOGGER.info("[ANÁLISE AGENDADA] Encontramos agendamento para hoje.");

				sendMessageAndPrepareScheduled(scheduleList);
				LOGGER.info("[ANÁLISE AGENDADA] Serviço finalizado com sucesso!");
			}
		} catch (Exception e) {
			LOGGER.error("[ANÁLISE AGENDADA] Erro ao enviar solicitação, motivo: ", e);
		}
	}

	private void sendMessageAndPrepareScheduled(List<ScheduledAnalysis> scheduleList) throws EwException {
		LOGGER.info("[ANÁLISE AGENDADA] Preparando para solicitar a execução ao serviço responsável.");
		KafkaMessageObject messageObject = new KafkaMessageObject(new UserObject(), "",
				TaskOperationEnum.FULL_ANALYSIS);

		producerService.sendMessage(KafkaTopics.FULL_ANALYSIS.getDescription(), messageObject);
		LOGGER.info("[ANÁLISE AGENDADA] Solicitação enviada com sucesso!");

		LOGGER.info("[ANÁLISE AGENDADA] Preparando os agendamentos para a proxima execução.");
		scheduleList.forEach(this::prepareScheduled);

		scheduledRepository.saveAll(scheduleList);
	}

	private void prepareScheduled(ScheduledAnalysis schedule) {
		schedule.setLastExecution(LocalDateTime.now().withHour(3).withMinute(0).withSecond(0));
		int lastDayOfMonth = YearMonth.from(LocalDateTime.now()).lengthOfMonth();
		int scheduleDay = schedule.getDay() <= lastDayOfMonth ? schedule.getDay() : lastDayOfMonth;

		schedule.setNextExecution(LocalDateTime.now().withDayOfMonth(scheduleDay).withHour(0).withMinute(0)
				.withSecond(0).plusMonths(schedule.getRecurrence().getMonths()));
	}
}
