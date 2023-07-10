package br.com.evasion.watch.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.evasion.watch.models.entities.ScheduledAnalysis;
import br.com.evasion.watch.models.enums.RecurrenceEnum;

public interface ScheduledAnalysisRepository extends JpaRepository<ScheduledAnalysis, Integer>{

	@Query("SELECT sa FROM ScheduledAnalysis sa WHERE sa.nextExecution < :date and sa.deleted = false")
	List<ScheduledAnalysis> findByNextExecution(LocalDateTime date);
	
	@Query("SELECT sa FROM ScheduledAnalysis sa WHERE sa.day = :day AND sa.recurrence = :recurrence and sa.deleted = false")
	Optional<ScheduledAnalysis> findByDayAndRecurrence(int day, RecurrenceEnum recurrence);

	@Query("SELECT sa FROM ScheduledAnalysis sa order by sa.nextExecution")
	List<ScheduledAnalysis> findAllWithDeleted();

	@Transactional
	@Modifying
	@Query("UPDATE ScheduledAnalysis SET deleted = true WHERE uuid = :uuid")
	void deleteByUUID(String uuid);

	@Transactional
	@Modifying
	@Query("UPDATE ScheduledAnalysis SET deleted = false WHERE uuid = :uuid")
	void restaureByUUID(String uuid);

}
