package bcntec.training.springboot.sso.server.converter.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.stereotype.Component;

import bcntec.training.springboot.sso.server.domain.OAuth2AccessTokenEntity;

@ReadingConverter
@Component
public class OAuth2AccessTokenEntityReadConverter implements Converter<OAuth2AccessTokenEntity, DefaultOAuth2AccessToken> {

	@Autowired
	TokenScopeReadConverter scopeConverter;
	
	@Override
	public DefaultOAuth2AccessToken convert(OAuth2AccessTokenEntity source) {
		DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(source.getId());
		token.setAdditionalInformation(source.getAdditionalInformation());
		token.setExpiration(source.getExpiration());
		token.setRefreshToken(source.getRefreshToken());
		token.setScope(this.scopeConverter.convert(source.getScope()));
		token.setTokenType(source.getTokenType());
		
		return token;
	}
	
	

}
