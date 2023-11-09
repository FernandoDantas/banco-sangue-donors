package br.com.myapk.core.security.authorizationserver;

import br.com.myapk.domain.model.Users;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AuthUser extends User{
	private static final long serialVersionUID = 1L;
	
	private Long userId;	
	private String userName;
	private String photo;

	private Long tenantId;
	
	public AuthUser(Users user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getUserEmail(), user.getCryptedPass(), authorities);
		
		this.userId = user.getId();		
		this.userName = user.getUserName();	
		this.photo = user.getPhoto();
		this.tenantId = user.getTenant().getId();
	}
	
}
