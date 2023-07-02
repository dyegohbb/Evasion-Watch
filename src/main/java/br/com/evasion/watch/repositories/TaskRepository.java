package br.com.evasion.watch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.evasion.watch.models.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}
