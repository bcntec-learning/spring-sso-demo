package bcntec.training.springboot.sso.server.converter.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.stereotype.Component;

import bcntec.training.springboot.sso.server.domain.OAuth2RequestEntity;

@Component
public class OAuth2RequestEntityWriteConverter implements Converter<OAuth2Request, OAuth2RequestEntity>  {

	@Autowired
	private TokenScopeWriteConverter scopeConverter;
	
	@Override
	public OAuth2RequestEntity convert(OAuth2Request source) {
		OAuth2RequestEntity request = new OAuth2RequestEntity();
		request.setApproved(source.isApproved());
		// TODO set authorities
		//request.setAuthorities(source.getAuthorities());
		request.setClientId(source.getClientId());
		request.setRedirectUri(request.getRedirectUri());
		request.setRequestParameters(source.getRequestParameters());
		request.setResourceIds(request.getResourceIds());
		request.setScope(scopeConverter.convert(source.getScope()));
		return request;
	}

}
