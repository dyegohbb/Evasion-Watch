package br.com.evasion.watch.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.evasion.watch.models.entities.AnalysisResultHistory;

public interface AnalysisResultHistoryRepository extends JpaRepository<AnalysisResultHistory, Integer>{
	
	@Query("SELECT arh FROM AnalysisResultHistory arh where evaded = true")
	List<AnalysisResultHistory> findAllWithEvadedTrue();

}
