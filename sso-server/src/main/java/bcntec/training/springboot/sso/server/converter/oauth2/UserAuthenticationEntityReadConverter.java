package bcntec.training.springboot.sso.server.converter.oauth2;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import bcntec.training.springboot.sso.server.domain.UserAuthenticationEntity;

@Component
public class UserAuthenticationEntityReadConverter implements Converter<UserAuthenticationEntity, Authentication> {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public Authentication convert(UserAuthenticationEntity source) {
		String username = source.getName();
		Object principal = this.userDetailsService.loadUserByUsername(username);
		Object credentials = null;
		Collection<? extends GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		Authentication auth = new UsernamePasswordAuthenticationToken(principal, credentials, authorities);
		return auth;
	}

}
