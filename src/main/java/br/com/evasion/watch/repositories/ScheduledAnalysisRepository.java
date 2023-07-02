package br.com.evasion.watch.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.evasion.watch.models.entities.ScheduledAnalysis;

public interface ScheduledAnalysisRepository extends JpaRepository<ScheduledAnalysis, Integer>{

	@Query("SELECT sa FROM ScheduledAnalysis sa WHERE sa.nextExecution < :date")
	Optional<List<ScheduledAnalysis>> findByNextExecution(LocalDateTime date);

}
