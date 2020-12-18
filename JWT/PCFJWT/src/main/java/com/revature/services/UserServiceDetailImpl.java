package com.revature.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.revature.entities.ApplicationUser;
import com.revature.entities.Privilage;
import com.revature.entities.Role;
import com.revature.repos.ApplicationUserRepo;

public class UserServiceDetailImpl implements UserDetailsService {
	
	@Autowired
	private ApplicationUserRepo applicationUserRepo;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceDetailImpl.class);

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		logger.info("username passed: " + arg0);
		ApplicationUser user = applicationUserRepo.getByUsername(arg0);
		if(user==null){
			logger.info("user not found");
			throw new UsernameNotFoundException(arg0);
		}
		
		logger.info("password: " + user.getPassword());
		
		return new User(user.getUsername(), user.getPassword(), getAuthorities(user));
		
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities(ApplicationUser user){
		return getGrantedAuthorities(getPrivilages(user.getRoles()));
		
	}
	
	private List<String> getPrivilages(Collection<Role> roles){
		List<String> authorities = new ArrayList<String>();
		for(Role role: roles){
			for(Privilage priv : role.getPrivilages()){
				authorities.add(priv.getName());
			}
		}
		return authorities;
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(List<String> privs){
		ArrayList<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		for(String p : privs){
			auths.add(new SimpleGrantedAuthority(p));
		}
		return auths;
	}

}
