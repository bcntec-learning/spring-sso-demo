package bcntec.training.springboot.sso.server.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bcntec.training.springboot.sso.server.domain.OAuth2AccessTokenEntity;

@Repository
public interface OAuth2AccessTokenRepository extends CrudRepository<OAuth2AccessTokenEntity, String> {

    @Override
	public Optional<OAuth2AccessTokenEntity> findById(String id);

}
