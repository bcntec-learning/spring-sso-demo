package bcntec.training.springboot.sso.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bcntec.training.springboot.sso.server.domain.UserAuthenticationEntity;

@Repository
public interface UserAuthenticationRepository extends CrudRepository<UserAuthenticationEntity, Long> {

}
