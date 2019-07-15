package bcntec.training.springboot.sso.server.converter.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.stereotype.Component;

import bcntec.training.springboot.sso.server.domain.OAuth2AuthenticationEntity;

@Component
@ReadingConverter
public final class OAuth2AuthenticationEntityReadConverter implements Converter<OAuth2AuthenticationEntity, OAuth2Authentication> {

	@Autowired
	private UserAuthenticationEntityReadConverter authConverter;
	
	@Autowired
	private OAuth2RequestEntityReadConverter oAuth2RequestEntityReadConverter;
	
	@Override
	public OAuth2Authentication convert(OAuth2AuthenticationEntity source) {
		
		OAuth2Request storedRequest = 
				oAuth2RequestEntityReadConverter.convert(source.getStoredRequest());
		Authentication userAuthentication = 
				authConverter.convert(source.getUserAuthentication());
		
		OAuth2Authentication authentication = new OAuth2Authentication(storedRequest, userAuthentication);
		return authentication;
	}
	
	

}
