package x.y.z.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailServiceImpl implements UserDetailsService, AuthenticationProvider {

	@Resource(name="userDAO")
	private UserDAO userDAO;
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String userId = authentication.getName();
		
		UserDetails userDetail = loadUserByUsername(userId);
		String inputPassword = authentication.getCredentials().toString();
		String dbPassowrd = userDetail.getPassword();
		if(!dbPassowrd.equals(inputPassword)) {
			throw new BadCredentialsException("invalid password!!");
		}
		
		Collection<? extends GrantedAuthority> authorities = userDetail.getAuthorities();
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userId,	dbPassowrd, authorities);
		
		// user 정보 setting
		token.setDetails(userDetail);
		
		return token;
	}


	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Map user = userDAO.getUser(username);
		System.out.println(user);
		System.out.println("----------------");
		
		if(user == null)
			throw new UsernameNotFoundException(username +"not exist!!!!");
		
		Collection<GrantedAuthority> role = getAutority();
		User userdetail = new User(user.get("USER_ID").toString(), (String)user.get("PASSWORD"), true, true, true, true, role);
		
		return userdetail;
	}


	private Collection<GrantedAuthority> getAutority() {
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_USER"));
		return roles;
	}
	

}
