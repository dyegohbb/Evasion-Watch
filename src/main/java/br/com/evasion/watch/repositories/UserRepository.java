package br.com.evasion.watch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.evasion.watch.models.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	boolean existsByLogin(String login);

	boolean existsByEmail(String email);

}
