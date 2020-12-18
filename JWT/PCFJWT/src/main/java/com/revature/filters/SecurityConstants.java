package com.revature.filters;

public class SecurityConstants {
	
	public static final long EXPIRATION_TIME = 360_00;
	public static final String SECRET = "SECRET_SALT";
	public static final String HEADER_STRING = "Authorization";
	public static final String  PREFIX = "Bearer "; // convention scheme, place Bearer ' ' and then your token

}
