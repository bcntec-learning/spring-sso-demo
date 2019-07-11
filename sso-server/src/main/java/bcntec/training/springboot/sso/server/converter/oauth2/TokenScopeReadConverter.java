package bcntec.training.springboot.sso.server.converter.oauth2;

import java.util.HashSet;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

import bcntec.training.springboot.sso.server.domain.TokenScopeEntity;

@ReadingConverter
@Component
public class TokenScopeReadConverter implements Converter<Set<TokenScopeEntity>, Set<String>> {

	@Override
	public Set<String> convert(Set<TokenScopeEntity> source) {
		Set<String> scope = new HashSet<>();
		for (TokenScopeEntity s : source) {
			scope.add(s.toString());
		}
		return scope;
	}

}
