package br.com.evasion.watch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.evasion.watch.models.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
