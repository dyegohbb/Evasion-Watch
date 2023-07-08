package br.com.evasion.watch.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.evasion.watch.models.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	boolean existsByLogin(String login);

	boolean existsByEmail(String email);

	Optional<User> findByLogin(String login);

}
