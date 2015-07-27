package x.y.z.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailServiceImpl implements UserDetailsService {

	@Resource(name="userDAO")
	private UserDAO userDAO;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Map user = userDAO.getUser(username);
		System.out.println(user);
		System.out.println("----------------");
		if(user == null)
			throw new UsernameNotFoundException(username +"not exist!!!!");
		
		Collection<GrantedAuthority> role = getAutority();
		User userdetail = new User((String)user.get("user_id"), (String)user.get("password"), true, true, true, true, role);
		
		return userdetail;
	}



	private Collection<GrantedAuthority> getAutority() {
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_USER"));
		return roles;
	}
	

}
