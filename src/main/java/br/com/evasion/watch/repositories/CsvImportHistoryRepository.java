package br.com.evasion.watch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.evasion.watch.models.entities.CsvImportHistory;

public interface CsvImportHistoryRepository extends JpaRepository<CsvImportHistory, Integer>{

}
