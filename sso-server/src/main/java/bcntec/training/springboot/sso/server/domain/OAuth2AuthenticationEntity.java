package bcntec.training.springboot.sso.server.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "OAUTH_AUTHENTICATION")
@Data
public class OAuth2AuthenticationEntity {

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne(fetch = FetchType.EAGER)
	private OAuth2RequestEntity storedRequest;
	
	@OneToOne(fetch = FetchType.EAGER)
	private UserAuthenticationEntity userAuthentication;
}
