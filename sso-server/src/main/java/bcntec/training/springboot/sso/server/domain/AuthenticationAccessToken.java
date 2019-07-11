package bcntec.training.springboot.sso.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
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

	private transient OAuth2AccessToken oAuth2AccessToken;
	@Column
    private String authenticationId;
	@Column
    private String userName;
	@Column
    private String clientId;

    private transient OAuth2Authentication authentication;

}
