package bcntec.training.springboot.sso.server.converter.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.stereotype.Component;

import bcntec.training.springboot.sso.server.domain.OAuth2RequestEntity;

@ReadingConverter
@Component
public class OAuth2RequestEntityReadConverter implements Converter<OAuth2RequestEntity, OAuth2Request>  {

	@Autowired
	private TokenScopeReadConverter scopeConverter;
	
	@Override
	public OAuth2Request convert(OAuth2RequestEntity source) {
		OAuth2Request request = new OAuth2Request(
				source.getRequestParameters(), 
				source.getClientId(),
				source.getAuthorities(),
				source.isApproved(),
				scopeConverter.convert(source.getScope()),
				source.getResourceIds(), 
				source.getRedirectUri(),
				null, // responseTypes
				null); // extensionProperties
		return request;
	}

}
