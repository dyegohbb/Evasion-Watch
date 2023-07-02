package br.com.evasion.watch.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.evasion.watch.services.AnalysisService;
import jakarta.annotation.PostConstruct;

@Component
public class ScheduleAnalysis implements Runnable{

	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleAnalysis.class);
	
	@Autowired
	private AnalysisService analysisService;
	
	@Override
	@PostConstruct
    @Scheduled(cron = "0 0 3 * * ?")
    public void run() {
		LOGGER.info("[ANÁLISE AGENDADA] Iniciando serviço de análise agendada.");
		analysisService.sheduledAnalysis();
    }

}
