package com.revature.personalfinance.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import lombok.Data;

@Component
@Scope("singleton")
@Data
public class Authenticator {

	/**
	 * Method for authenticating token provided in request header
	 * and checking revocation status
	 * 
	 * @param jwt - JWT contained in request
	 * @return Boolean value indicating if token is authentic
	 */
	public static boolean isAuthentic(String jwt) {

		// Initialize firebase config.
		AuthenticatorUtils.firebaseInitialize();

		// authenticates provided token with Firebase
		FirebaseToken decodedToken = null;
		String uid = null;
		try {
			decodedToken = FirebaseAuth.getInstance().verifyIdToken(jwt);
			uid = decodedToken.getUid();
		} catch (FirebaseAuthException e) {
			e.printStackTrace();
		}
		if (uid != null && !AuthenticatorUtils.isFirebaseRevoked(jwt))
			return true;
		return false;
	}


	/**
	 * Method for retrieving username from JWT
	 * @param jwt - JWT contained in request
	 * @return Username of user submitting request
	 */
	public static String getUsername(String jwt) {
		// Initialize firebase config.
		AuthenticatorUtils.firebaseInitialize();

		// authenticates provided token with Firebase
		FirebaseToken decodedToken = null;
		String username = null;
		try {
			decodedToken = FirebaseAuth.getInstance().verifyIdToken(jwt);
			username = decodedToken.getName();
		} catch (FirebaseAuthException e) {
			e.printStackTrace();
		}
		// returns username from parsing JWT
		return username;
	}
}
