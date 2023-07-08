package br.com.evasion.watch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.evasion.watch.models.entities.AnalysisResultHistory;

@Repository
public interface AnalysisResultHistoryRepository extends JpaRepository<AnalysisResultHistory, Integer>{

}
