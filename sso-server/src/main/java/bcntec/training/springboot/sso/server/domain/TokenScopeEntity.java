package bcntec.training.springboot.sso.server.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TOKEN_SCOPE")
@Data
public class TokenScopeEntity {

	@Id
	private String id;
}
