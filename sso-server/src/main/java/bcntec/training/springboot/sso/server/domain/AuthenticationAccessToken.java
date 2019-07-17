package bcntec.training.springboot.sso.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	
//	@Column
//    private String authenticationId;

	@Column
    private String userName;
	@Column
    private String clientId;

	@OneToOne(fetch = FetchType.EAGER)
    private OAuth2AuthenticationEntity authentication;

}
