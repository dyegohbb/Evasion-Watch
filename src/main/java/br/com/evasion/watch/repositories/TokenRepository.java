package br.com.evasion.watch.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.evasion.watch.models.entities.UserToken;
import br.com.evasion.watch.models.entities.User;

public interface TokenRepository extends JpaRepository<UserToken, Integer> {

	@Query(value = """
			select t from UserToken t inner join User u\s
			on t.user.id = u.id\s
			where u.id = :id and (t.expired = false or t.revoked = false)\s
			""")
	List<UserToken> findAllValidTokenByUser(Integer id);

	Optional<UserToken> findByToken(String token);

	@Query(value = "SELECT t.user FROM UserToken t WHERE t.token = :token")
	Optional<User> findUserByToken(String token);
}
