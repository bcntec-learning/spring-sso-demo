package bcntec.training.springboot.sso.server.converter.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;

import bcntec.training.springboot.sso.server.domain.OAuth2AccessTokenEntity;

@Component
public class OAuth2AccessTokenEntityWriteConverter implements Converter<OAuth2AccessToken, OAuth2AccessTokenEntity> {

	@Autowired
	TokenScopeWriteConverter scopeConverter;
	
	@Override
	public OAuth2AccessTokenEntity convert(OAuth2AccessToken source) {
		OAuth2AccessTokenEntity entity = new OAuth2AccessTokenEntity(source.getValue());
		entity.setAdditionalInformation(source.getAdditionalInformation());
		entity.setExpiration(source.getExpiration());
		entity.setRefreshToken(source.getRefreshToken());
		entity.setScope(this.scopeConverter.convert(source.getScope()));
		entity.setTokenType(source.getTokenType());
		
		return entity;
	}
	
	

}
