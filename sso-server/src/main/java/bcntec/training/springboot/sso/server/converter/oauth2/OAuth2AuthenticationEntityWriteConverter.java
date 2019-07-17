package bcntec.training.springboot.sso.server.converter.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import bcntec.training.springboot.sso.server.domain.OAuth2AuthenticationEntity;

@Component
public final class OAuth2AuthenticationEntityWriteConverter implements Converter<OAuth2Authentication, OAuth2AuthenticationEntity> {

	@Autowired
	private UserAuthenticationEntityWriteConverter authConverter;
	
	@Autowired
	private OAuth2RequestEntityWriteConverter oAuth2RequestEntityWriteConverter;
	
	@Override
	public OAuth2AuthenticationEntity convert(OAuth2Authentication source) {
		OAuth2AuthenticationEntity entity = new OAuth2AuthenticationEntity();
		entity.setUserAuthentication(authConverter.convert(source.getUserAuthentication()));
		entity.setStoredRequest(oAuth2RequestEntityWriteConverter.convert(source.getOAuth2Request()));
		return entity;
	}
	
	

}
