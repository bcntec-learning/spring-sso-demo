package bcntec.training.springboot.sso.server.security;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bcntec.training.springboot.sso.server.domain.AuthenticationAccessToken;

@Repository
public interface AccessTokenRepository extends CrudRepository<AuthenticationAccessToken, String> {

    public AuthenticationAccessToken findByTokenId(String tokenId);

    public AuthenticationAccessToken findByRefreshTokenId(String refreshTokenId);

    public AuthenticationAccessToken findByAuthenticationId(String authenticationId);

    public List<AuthenticationAccessToken> findByClientIdAndUserName(String clientId, String userName);

    public List<AuthenticationAccessToken> findByClientId(String clientId);

}
