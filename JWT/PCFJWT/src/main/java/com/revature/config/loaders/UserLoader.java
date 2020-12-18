package com.revature.config.loaders;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.revature.entities.ApplicationUser;
import com.revature.entities.Privilage;
import com.revature.entities.Role;
import com.revature.repos.ApplicationUserRepo;
import com.revature.repos.PrivilageRepo;
import com.revature.repos.RoleRepo;

@Component
public class UserLoader implements ApplicationListener<ContextRefreshedEvent> {
	
	private boolean isSetUp = false;
	
	private ApplicationUserRepo userRepo;
	
	private RoleRepo roleRepo;
	
	private PrivilageRepo privilageRepo;
	
	Logger logger = LoggerFactory.getLogger(UserLoader.class);
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	public UserLoader(ApplicationUserRepo userRepo, RoleRepo roleRepo, PrivilageRepo privilageRepo) {
		super();
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.privilageRepo = privilageRepo;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		logger.info("inside creating users");
		if (isSetUp){
			return;
		}
		
		isSetUp = true;
		Privilage p = createPrivilageIfNotFound("read");
		List<Privilage> pList = new ArrayList<Privilage>();
		pList.add(p);
		Role r = createRoleIfNotFound("admin", pList);
		List<Role> rList = new ArrayList<Role>();
		rList.add(r);
		ApplicationUser u = createUserIfNotFound("admin", "pass", rList);
				
	}
	
	@Transactional
	protected Privilage createPrivilageIfNotFound(String privilage){
		
		Privilage priv = privilageRepo.getByName(privilage);
		if (priv == null){
			priv = new Privilage();
			priv.setName(privilage);
		}
		
		privilageRepo.save(priv);
		
		return priv;
		
	}
	
	@Transactional
	protected Role createRoleIfNotFound(String role, List<Privilage> privs){
		
		Role r = roleRepo.getByName(role);
		
		if(r == null){
			r = new Role();
			r.setName(role);
			r.setPrivilages(privs);
		}
		roleRepo.save(r);
		return r;
	}
	
	@Transactional
	protected ApplicationUser createUserIfNotFound(String username, String password, List<Role> roles){
		
		ApplicationUser user = userRepo.getByUsername(username);
		
		if (user == null){
			user = new ApplicationUser();
			user.setUsername(username);
			user.setPassword(encoder.encode(password));
			user.setRoles(roles);
		}
		
		userRepo.save(user);
		logger.info("input user with username: " + user.getUsername() + " and password " + user.getPassword());
		return user;
	}
	

}
