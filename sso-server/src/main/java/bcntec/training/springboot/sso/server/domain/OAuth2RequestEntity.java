package bcntec.training.springboot.sso.server.domain;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Data;

@Entity
@Table(name = "OAUTH_REQUEST")
@Data
public class OAuth2RequestEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private transient Set<String> resourceIds;
	
	private transient Collection<SimpleGrantedAuthority> authorities;
	
	@Column
	private boolean approved;
	
	@Column 
	private String clientId;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<TokenScopeEntity> scope;
	
	private transient Map<String, String> requestParameters;
	
	@Column
	private String redirectUri;

}
