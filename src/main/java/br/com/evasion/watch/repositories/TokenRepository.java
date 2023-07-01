package br.com.evasion.watch.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.evasion.watch.models.entities.Token;
import br.com.evasion.watch.models.entities.User;

public interface TokenRepository extends JpaRepository<Token, Integer> {

	@Query(value = """
			select t from Token t inner join User u\s
			on t.user.id = u.id\s
			where u.id = :id and (t.expired = false or t.revoked = false)\s
			""")
	List<Token> findAllValidTokenByUser(Integer id);

	Optional<Token> findByToken(String token);

	@Query(value = "SELECT t.user FROM Token t WHERE t.token = :token")
	Optional<User> findUserByToken(String token);
}
