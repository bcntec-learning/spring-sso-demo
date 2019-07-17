package bcntec.training.springboot.sso.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bcntec.training.springboot.sso.server.domain.OAuth2RequestEntity;

@Repository
public interface OAuth2RequestRepository extends CrudRepository<OAuth2RequestEntity, Long> {

}
