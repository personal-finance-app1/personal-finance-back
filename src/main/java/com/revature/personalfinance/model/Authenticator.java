package com.revature.personalfinance.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class Authenticator {
	
	/**
	 * Method for authenticating token provided in request header
	 * @param jwt - JWT contained in request
	 * @return Boolean value indicating if token is authentic
	 */
	public static boolean isAuthentic(String jwt) {
		// TODO implement this method
		// authenticates provided token with Firebase
		return true;
	}
	
	/**
	 * Method for retrieving username from JWT
	 * @param jwt - JWT contained in request
	 * @return Username of user submitting request
	 */
	public static String getUsername(String jwt) {
		//TODO implement this method
		// returns username from parsing JWT
		return null;
	}
}
