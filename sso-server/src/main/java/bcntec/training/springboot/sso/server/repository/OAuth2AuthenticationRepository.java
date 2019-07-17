package bcntec.training.springboot.sso.server.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bcntec.training.springboot.sso.server.domain.OAuth2AuthenticationEntity;

@Repository
public interface OAuth2AuthenticationRepository extends CrudRepository<OAuth2AuthenticationEntity, Long> {

    @Override
	public Optional<OAuth2AuthenticationEntity> findById(Long id);

}
