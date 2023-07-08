package br.com.evasion.watch.services;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import br.com.evasion.watch.config.kafka.KafkaTopics;
import br.com.evasion.watch.exceptions.DuplicateScheduleException;
import br.com.evasion.watch.exceptions.EwException;
import br.com.evasion.watch.models.entities.ScheduledAnalysis;
import br.com.evasion.watch.models.entities.Task;
import br.com.evasion.watch.models.enums.RecurrenceEnum;
import br.com.evasion.watch.models.enums.SituationEnum;
import br.com.evasion.watch.models.enums.TaskOperationEnum;
import br.com.evasion.watch.models.transfer.ApiResponseObject;
import br.com.evasion.watch.models.transfer.ScheduledAnalysisLightObject;
import br.com.evasion.watch.models.transfer.ScheduledAnalysisObject;
import br.com.evasion.watch.models.transfer.TaskObject;
import br.com.evasion.watch.models.transfer.UserObject;
import br.com.evasion.watch.repositories.ScheduledAnalysisRepository;
import br.com.evasion.watch.repositories.TaskRepository;

@Service
public class AnalysisService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AnalysisService.class);

	@Autowired
	private ProducerService producerService;

	@Autowired
	private UserService userService;

	@Autowired
	private ScheduledAnalysisRepository scheduledRepository;
	
	@Autowired
	private TaskRepository taskRepository;

	public ApiResponseObject fullAnalysis(String userToken) {
		LOGGER.info("[ANÁLISE COMPLETA] Preparando para solicitar a execução ao serviço responsável.");
		try {
			UserObject user = userService.findUserObjectByToken(userToken);
			
			Task task = new Task(TaskOperationEnum.FULL_ANALYSIS, "", SituationEnum.RUNNING, user.getLogin());
			task.setProgress(10);
			
			Task taskSaved = taskRepository.save(task);
			TaskObject taskObject = new TaskObject(taskSaved);
			producerService.sendMessage(KafkaTopics.FULL_ANALYSIS.getDescription(), taskObject);
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
		Task task = new Task(TaskOperationEnum.FULL_ANALYSIS, "", SituationEnum.RUNNING, "SCHEDULER");
		task.setProgress(10);

		Task taskSaved = taskRepository.save(task);
		TaskObject taskObject = new TaskObject(taskSaved);
		producerService.sendMessage(KafkaTopics.FULL_ANALYSIS.getDescription(), taskObject);
		LOGGER.info("[ANÁLISE AGENDADA] Solicitação enviada com sucesso!");

		LOGGER.info("[ANÁLISE AGENDADA] Preparando os agendamentos para a proxima execução.");
		scheduleList.forEach(this::prepareScheduled);

		scheduledRepository.saveAll(scheduleList);
	}

	private void prepareScheduled(ScheduledAnalysis schedule) {
		int lastDayOfMonth = YearMonth.from(LocalDateTime.now()).lengthOfMonth();
		int scheduleDay = schedule.getDay() <= lastDayOfMonth ? schedule.getDay() : lastDayOfMonth;

		schedule.setNextExecution(LocalDateTime.now().withDayOfMonth(scheduleDay).withHour(0).withMinute(0)
				.withSecond(0).plusMonths(schedule.getRecurrence().getMonths()));
	}

	public ApiResponseObject schedule(ScheduledAnalysisLightObject request, BindingResult bindingResult) {
		try {
			LOGGER.info("[AGENDAR ANÁLISE] Iniciando processo de agendamento.");
			if (bindingResult.hasErrors()) {
				LOGGER.error("[AGENDAR ANÁLISE] Encontramos os seguintes problemas: ");
				StringBuilder errorMessage = new StringBuilder();
				for (ObjectError error : bindingResult.getAllErrors()) {
					LOGGER.error(error.getDefaultMessage());
					errorMessage.append(error.getDefaultMessage()).append("; ");
				}

				return new ApiResponseObject(errorMessage.toString(), HttpStatus.BAD_REQUEST);
			}
			int day = request.getDay();
			RecurrenceEnum recurrence = request.getRecurrence();
			if (scheduledRepository.findByDayAndRecurrence(day, recurrence).isPresent()) {
				LOGGER.error("[AGENDAR ANÁLISE] Já existe um agendamento para esse dia[{}], com essa recurrencia[{}].",
						day, recurrence);

				return new ApiResponseObject(new DuplicateScheduleException(day, recurrence));
			}
			LOGGER.info("[AGENDAR ANÁLISE] Preparando dados para salvar no banco de dados.");
			ScheduledAnalysis schedule = new ScheduledAnalysis(request);

			LocalDateTime nextExecution = LocalDateTime.now().withDayOfMonth(day).withHour(0).withMinute(0)
					.withSecond(0);

			LocalDateTime now = LocalDateTime.now();

			if (nextExecution.isAfter(now)) {
				LOGGER.info("[AGENDAR ANÁLISE] O dia do agendamento é posterior à data atual, portanto a próxima execução será neste mês.");
				schedule.setNextExecution(nextExecution);
			} else {
				LOGGER.info("[AGENDAR ANÁLISE] O dia do agendamento é anterior à data atual, portanto a próxima execução será no próximo mês.");
				schedule.setNextExecution(nextExecution.plusMonths(1));
			}

			LOGGER.info("[AGENDAR ANÁLISE] Salvando agendamento no banco de dados.");
			ScheduledAnalysis scheduleSaved = scheduledRepository.save(schedule);
			LOGGER.info("[AGENDAR ANÁLISE] Agendamento salvo no banco de dados com sucesso!");
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			String formattedDateTime = scheduleSaved.getNextExecution().format(formatter);
			
			return new ApiResponseObject(
					String.format("Agendamento criado com sucesso, dia: %d recurrencia: %s próxima execução: %s",
							scheduleSaved.getDay(), scheduleSaved.getRecurrence().toString(), formattedDateTime),
					HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ApiResponseObject(e);
		}

	}

	public List<ScheduledAnalysisObject> listSchedule() {
		List<ScheduledAnalysis> scheduledAnalysisList = this.scheduledRepository.findAllWithDeleted();
		
		return scheduledAnalysisList.stream().map(ScheduledAnalysisObject::new).toList();
	}

	public ApiResponseObject deleteSchedule(String uuid) {
		try {
			this.scheduledRepository.deleteByUUID(uuid);
		} catch (Exception e) {
			LOGGER.error("Erro ao deletar schedule", e);
			return new ApiResponseObject("Erro ao deletar schedule", HttpStatus.BAD_REQUEST);
		}
		return new ApiResponseObject("Schedule deletado com sucesso!", HttpStatus.OK);
	}
}
