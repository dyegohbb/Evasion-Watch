package br.com.evasion.watch.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.evasion.watch.models.entities.StudentData;

@Repository
public interface StudentDataRepository extends JpaRepository<StudentData, Integer>{
	
	@Query("SELECT sd.name FROM StudentData sd where studentID = :studentId")
	Optional<String> findNameByStudentId(String studentId);
}
