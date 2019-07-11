package bcntec.training.springboot.sso.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.oauth2.provider.OAuth2Authentication;

import lombok.Data;


@Entity
@Table(name = "ACCESS_TOKEN")
@Data
public class AuthenticationAccessToken {

	@Id
    private String tokenId;
	@Column
	private String refreshTokenId;

	@OneToOne
	private OAuth2AccessTokenEntity oAuth2AccessToken;
	
	@Column
    private String authenticationId;
	@Column
    private String userName;
	@Column
    private String clientId;

    private transient OAuth2Authentication authentication;

}
