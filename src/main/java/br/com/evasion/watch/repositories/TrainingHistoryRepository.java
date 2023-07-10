package br.com.evasion.watch.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.evasion.watch.models.entities.TrainingHistory;

public interface TrainingHistoryRepository extends JpaRepository<TrainingHistory, Integer>{

	@Query("SELECT th FROM TrainingHistory th ORDER BY th.createdAt DESC")
	public List<TrainingHistory> getTrainingHistoryOrderByDate();
}
