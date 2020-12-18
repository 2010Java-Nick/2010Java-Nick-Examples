package com.revature.filters;

import static com.revature.filters.SecurityConstants.HEADER_STRING;
import static com.revature.filters.SecurityConstants.PREFIX;
import static com.revature.filters.SecurityConstants.SECRET;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager){
		super(authenticationManager);
	}
	
	@Override 
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException{
		
		String header = req.getHeader(HEADER_STRING);
		
		logger.info("Header: " + header);
		
		if(header == null || !header.startsWith(PREFIX)){
			chain.doFilter(req, resp);
			return;
		}
		
		UsernamePasswordAuthenticationToken authToken = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authToken);
		chain.doFilter(req, resp);
		
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req){
		String token = ((HttpServletRequest) req).getHeader(HEADER_STRING);
		
		if(token != null){
			Jws<Claims> claims = Jwts.parser()
					.setSigningKey(SECRET.getBytes())
					.parseClaimsJws(token.replace(PREFIX, ""));
			String user = claims.getBody().getSubject();
			ArrayList<String> scopes = (ArrayList<String>) claims.getBody().get("scope");
			
			if(user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, getAuthorities(scopes));
			}
			
			return null;
		}
		
		return null;
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Collection<String> privs){
		ArrayList<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		for(String p : privs){
			auths.add(new SimpleGrantedAuthority(p));
		}
		return auths;
	}
	
}
