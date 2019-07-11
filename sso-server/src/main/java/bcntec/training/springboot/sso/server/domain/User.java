package bcntec.training.springboot.sso.server.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Entity
@Table(name = "USER")
@Data
public class User implements UserDetails {

	private static final long serialVersionUID = 1415027050448311832L;

	@Id
    @GeneratedValue
    @Column(nullable = false)
	private String id;
	
	@Column(nullable = false)
	private String username;

	@Column
	private String password;

	@Column
	private String name;
	
	@Column
	private String lastname;

	@Column
	private boolean enabled = true;
	
	@Column
	private boolean locked = true;
	
	
	private transient boolean expired = false;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Store at database.
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("USER");
		ArrayList<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
		list.add(authority);
		return list;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !this.expired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !this.locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

}
