package bcntec.training.springboot.sso.server.domain;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.oauth2.common.OAuth2RefreshToken;

import lombok.Data;

@Entity
@Table(name = "OAUTH_ACCESS_TOKEN")
@Data
public class OAuth2AccessTokenEntity {

	public OAuth2AccessTokenEntity(String value) {
		this.id = value;
	}
	
	/**
	 * Private constructor for JPA and other serialization tools.
	 */
	@SuppressWarnings("unused")
	private OAuth2AccessTokenEntity() {
		this((String) null);
	}	
	
	@Id
	private String id;

	@Column
	private Date expiration;

	@Column
	private String tokenType = "bearer";

	private transient OAuth2RefreshToken refreshToken;

	@OneToMany(fetch = FetchType.EAGER)
	private Set<TokenScopeEntity> scope;

	private transient Map<String, Object> additionalInformation = Collections.emptyMap();

}
