package bcntec.training.springboot.sso.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "OAUTH_USER_AUTHENTICATION")
@Data
public class UserAuthenticationEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private boolean authenticated;
	
	@Column
	private String name;

}
