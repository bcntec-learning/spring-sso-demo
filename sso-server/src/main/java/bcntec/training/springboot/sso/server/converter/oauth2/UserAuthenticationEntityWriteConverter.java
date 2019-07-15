package bcntec.training.springboot.sso.server.converter.oauth2;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import bcntec.training.springboot.sso.server.domain.UserAuthenticationEntity;

@Component
public class UserAuthenticationEntityWriteConverter implements Converter<Authentication, UserAuthenticationEntity> {

	@Override
	public UserAuthenticationEntity convert(Authentication source) {
		UserAuthenticationEntity entity = new UserAuthenticationEntity();
		// TODO Convert missing fields.
		entity.setName(source.getName());
		return entity;
	}

}
