package bcntec.training.springboot.sso.server.converter.oauth2;

import java.util.HashSet;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import bcntec.training.springboot.sso.server.domain.TokenScopeEntity;

@Component
public class TokenScopeWriteConverter implements Converter<Set<String>, Set<TokenScopeEntity>> {

	@Override
	public Set<TokenScopeEntity> convert(Set<String> source) {
		Set<TokenScopeEntity> scope = new HashSet<>();
		for (String s : source) {
			TokenScopeEntity e = new TokenScopeEntity();
			e.setId(s);
			scope.add(e);
		}
		return scope;
	}

}
