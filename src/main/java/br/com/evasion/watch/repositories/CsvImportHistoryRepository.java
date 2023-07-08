package br.com.evasion.watch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.evasion.watch.models.entities.CsvImportHistory;

@Repository
public interface CsvImportHistoryRepository extends JpaRepository<CsvImportHistory, Integer>{

}
