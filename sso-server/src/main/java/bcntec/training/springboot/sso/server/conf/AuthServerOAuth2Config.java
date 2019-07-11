package bcntec.training.springboot.sso.server.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

import bcntec.training.springboot.sso.server.security.H2TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthServerOAuth2Config extends AuthorizationServerConfigurerAdapter {

	private final String MOBILE_APP_ID = "bcntec-app";
	private final String RESOURCE_ID = "bcntec-resource";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder oauthClientPasswordEncoder;

    @Autowired
    H2TokenStore tokenStore;

    @Bean
    public TokenStore tokenStore() {
        return this.tokenStore;
    }

    @Bean
    public OAuth2AccessDeniedHandler oauthAccessDeniedHandler() {
        return new OAuth2AccessDeniedHandler();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
    	// defines the security constraints on the token endpoint.

        oauthServer.passwordEncoder(this.oauthClientPasswordEncoder);
//    	oauthServer.realm("bcntec-app");
//        oauthServer
//        	.tokenKeyAccess("permitAll()")
//        	.checkTokenAccess("isAuthenticated()");
//        	// .passwordEncoder(this.oauthClientPasswordEncoder);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    	// a configurer that defines the client details service. Client details can be initialized, or you can just refer to an existing store.

        // TODO Learn grant types and configure accordingly.
    	// TODO Configure security for web app.
		// @formatter:off
		clients.inMemory()
				.withClient(this.MOBILE_APP_ID)
		 			.resourceIds(this.RESOURCE_ID)
		 			.authorizedGrantTypes("password", "refresh_token")
		 			.authorities("ROLE_USER")
		 			.scopes("read", "write")
		 			.secret("$2a$08$zPIf.5a/WDxdMJGEsLAwfuCrs5fV3/pY6UEv0pIKWPukxdMTXtSfa");
//		 			.secret(this.oauthClientPasswordEncoder.encode("toto"));

//        clients.inMemory()
//	        .withClient("foo")
//	        .secret("{noop}bar")
//	        .authorizedGrantTypes("authorization_code", "refresh_token", "password")
//	        .scopes("user_info")
//	        .autoApprove(true)
//	        .redirectUris("http://localhost:8081/sso-client/login");		
		
		// @formatter:on
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    	// defines the authorization and token endpoints and the token services.
        endpoints
        	.tokenStore(this.tokenStore())
        	.authenticationManager(this.authenticationManager)
        	.userDetailsService(this.userDetailsService);
    }
}
