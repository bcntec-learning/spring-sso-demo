package bcntec.training.springboot.sso.server.converter.oauth2;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import bcntec.training.springboot.sso.server.domain.UserAuthenticationEntity;

@Component
public class UserAuthenticationEntityReadConverter implements Converter<UserAuthenticationEntity, Authentication> {

	@Override
	public Authentication convert(UserAuthenticationEntity source) {
		Object principal = null;
		Object credentials = null;
		Authentication auth = new UsernamePasswordAuthenticationToken(principal, credentials);
		// TODO Convert missing fields.
		auth.setAuthenticated(source.isAuthenticated());
		return auth;
	}

}
