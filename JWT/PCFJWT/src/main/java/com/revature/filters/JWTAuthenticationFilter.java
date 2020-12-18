package com.revature.filters;

import static com.revature.filters.SecurityConstants.EXPIRATION_TIME;
import static com.revature.filters.SecurityConstants.HEADER_STRING;
import static com.revature.filters.SecurityConstants.PREFIX;
import static com.revature.filters.SecurityConstants.SECRET;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.entities.ApplicationUser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	private AuthenticationManager authenticationManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			ApplicationUser creds = new ObjectMapper().readValue(request.getInputStream(), ApplicationUser.class);

			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), new ArrayList<>()));
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String token = Jwts.builder().setSubject(((User)authResult.getPrincipal()).getUsername())
										.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
										.claim("scope", getPrivilages(authResult.getAuthorities()))
										.signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
										.compact();
		response.setHeader(HEADER_STRING,  PREFIX + token);
		response.setHeader("Expires", new Long(System.currentTimeMillis() + EXPIRATION_TIME).toString());
	}

	private Collection<String> getPrivilages(Collection<? extends GrantedAuthority> authorities){
		ArrayList<String> privs = new ArrayList<String>();
		for(GrantedAuthority g : authorities){
			privs.add(g.getAuthority());
		}
		return privs;
	}

}
