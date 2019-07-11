package bcntec.training.springboot.sso.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bcntec.training.springboot.sso.server.domain.TokenScopeEntity;

@Repository
public interface TokenScopeRepository extends CrudRepository<TokenScopeEntity, String> {

}
