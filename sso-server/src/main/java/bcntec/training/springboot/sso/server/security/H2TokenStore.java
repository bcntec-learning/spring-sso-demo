package bcntec.training.springboot.sso.server.security;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

import bcntec.training.springboot.sso.server.converter.oauth2.OAuth2AccessTokenEntityReadConverter;
import bcntec.training.springboot.sso.server.converter.oauth2.OAuth2AccessTokenEntityWriteConverter;
import bcntec.training.springboot.sso.server.converter.oauth2.OAuth2RequestEntityWriteConverter;
import bcntec.training.springboot.sso.server.domain.AuthenticationAccessToken;
import bcntec.training.springboot.sso.server.domain.TokenScopeEntity;
import bcntec.training.springboot.sso.server.domain.User;
import bcntec.training.springboot.sso.server.repository.AccessTokenRepository;
import bcntec.training.springboot.sso.server.repository.OAuth2AccessTokenRepository;
import bcntec.training.springboot.sso.server.repository.OAuth2RequestRepository;
import bcntec.training.springboot.sso.server.repository.TokenScopeRepository;

@Component
public class H2TokenStore implements TokenStore {

	@Autowired
	private AccessTokenRepository accessTokenRepository;
	
	@Autowired
	private OAuth2AccessTokenRepository oAuth2AccessTokenRepository;
	
	@Autowired
	private TokenScopeRepository tokenScopeRepository;
	
	@Autowired 
	private OAuth2RequestRepository oAuth2RequestRepository;
	
	@Autowired
	private OAuth2AccessTokenEntityReadConverter oAuth2AccessTokenEntityReadConverter;

	@Autowired
	private OAuth2AccessTokenEntityWriteConverter oAuth2AccessTokenEntityWriteConverter;
	
	@Autowired
	private OAuth2RequestEntityWriteConverter oAuth2RequestEntityWriteConverter;
	

	@Override
	public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
		return this.readAuthentication(token.getValue());
	}

	@Override
	public OAuth2Authentication readAuthentication(String token) {
		return this.accessTokenRepository.findByTokenId(token).getAuthentication();
	}

	@Override
	public void storeAccessToken(OAuth2AccessToken accessToken, OAuth2Authentication authToken) {
		AuthenticationAccessToken token = new AuthenticationAccessToken();
		token.setTokenId(accessToken.getValue());
		token.setOAuth2AccessToken(oAuth2AccessTokenEntityWriteConverter.convert(accessToken));

//		OAuth2RefreshToken rt = accessToken.getRefreshToken();
//		if (null != rt) {
//			token.setRefreshTokenId(rt.getValue());
//			token.setRefreshToken(accessToken.getRefreshToken());
//		}

		token.setAuthentication(authToken);

		@SuppressWarnings("unchecked")
		Map<String, String> details = (Map<String, String>) authToken.getUserAuthentication().getDetails();
		if (null != details) {
			// The token has been generated with login.
			token.setClientId(details.get("client_id"));
			token.setUserName(details.get("username"));
		} else if (null != authToken.getOAuth2Request()) {
			// When the token is generated due to refresh token.
			token.setClientId(authToken.getOAuth2Request().getClientId());
			token.setUserName(((UserDetails) authToken.getUserAuthentication().getPrincipal()).getUsername());
		}
		
		
		// TODO Move to new service
		for (TokenScopeEntity t: token.getOAuth2AccessToken().getScope()) {
			this.tokenScopeRepository.save(t);
		}
		this.oAuth2AccessTokenRepository.save(token.getOAuth2AccessToken());
		this.oAuth2RequestRepository.save(
				oAuth2RequestEntityWriteConverter.convert(
						token.getAuthentication().getOAuth2Request()));
		this.accessTokenRepository.save(token);
	}

	@Override
	public OAuth2AccessToken readAccessToken(String tokenValue) {
		AuthenticationAccessToken token = this.accessTokenRepository.findByTokenId(tokenValue);
		OAuth2AccessToken accessToken = null;
		if (null != token) {
			accessToken = oAuth2AccessTokenEntityReadConverter.convert(token.getOAuth2AccessToken());
		}
		return accessToken;
	}

	@Override
	public void removeAccessToken(OAuth2AccessToken token) {
		String tokenId = token.getValue();
		AuthenticationAccessToken accessToken = this.accessTokenRepository.findByTokenId(tokenId);
		this.accessTokenRepository.deleteById(accessToken.getTokenId());
	}

	@Override
	public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
		// TODO implement.
	}

	@Override
	public OAuth2RefreshToken readRefreshToken(String tokenValue) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void removeRefreshToken(OAuth2RefreshToken token) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
		User principal = (User) authentication.getUserAuthentication().getPrincipal();

		// Retrieve existing token if exists.
		List<AuthenticationAccessToken> tokens =
				this.accessTokenRepository.findByClientIdAndUserName(
						authentication.getOAuth2Request().getClientId() , principal.getUsername());

		// there can only exist one token. It needs to be returned unless it can be expired.
		if (!tokens.isEmpty()) {
			return this.oAuth2AccessTokenEntityReadConverter.convert(tokens.get(0).getOAuth2AccessToken());
		}

		// If the token does not exist return null to create a new one.
		return null;
	}

	@Override
	public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

}
