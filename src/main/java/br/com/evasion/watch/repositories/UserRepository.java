package br.com.evasion.watch.repositories;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.evasion.watch.models.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	boolean existsByLogin(String login);

	boolean existsByEmail(String email);

	Optional<User> findByLogin(String login);

	@Query(value="SELECT u FROM User u JOIN UserToken as ut on ut.user_id = u.id WHERE ut.token = :token LIMIT 1")
	Optional<User> findUserByToken(String token);
}
