package com.revature.config;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.revature.filters.JWTAuthenticationFilter;
import com.revature.filters.JWTAuthorizationFilter;
import com.revature.services.UserServiceDetailImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebConfig extends WebSecurityConfigurerAdapter{
	
	private UserDetailsService userDetailsService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		//http.csrf().disable().authorizeRequests().antMatchers("/*").permitAll();  //Open to all
		http.cors()
			.and()
			.csrf().disable() //protect against cross origin resource forgery, the big vulnerability with JWT
			.authorizeRequests()
			.antMatchers("/login").permitAll() //allow access to login page
			.anyRequest().authenticated() //all incoming requests must be authenticated
			.and()
			.addFilter(new JWTAuthenticationFilter(authenticationManager()))
			.addFilter(new JWTAuthorizationFilter(authenticationManager()))
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //disable sessions, must be stateless
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource(){
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		List<String> exposedHeaders = new ArrayList<String>();
		exposedHeaders.add("Authorization");
		CorsConfiguration cc = new CorsConfiguration().applyPermitDefaultValues();
		cc.setExposedHeaders(exposedHeaders);
		source.registerCorsConfiguration("/**", cc);
		return source;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService);
	}
	
	@Autowired
	public void setUserDetailsService(UserDetailsService userDetailsService){
		this.userDetailsService = userDetailsService;
	}
	
	@Autowired
	public void setBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder){
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
/*	@Autowired
	public WebConfig(UserDetailsService userDetailsService,BCryptPasswordEncoder bcryptPasswordEncoder){
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bcryptPasswordEncoder;
	}*/
	
	@Bean
	public UserDetailsService userDetailsService(){
		return new UserServiceDetailImpl();
		
	}

}
