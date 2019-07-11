package bcntec.training.springboot.sso.server.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bcntec.training.springboot.sso.server.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

	public Optional<User> findOneByUsername(String username);

}
