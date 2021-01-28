package com.revature.personalfinance.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import lombok.Data;

@Component
@Scope("singleton")
@Data
public class Authenticator {

	private AuthenticatorUtils authUtil = new AuthenticatorUtils();
	
    private static final Logger log = LogManager.getLogger(Authenticator.class);
    
	/**
	 * Method for authenticating token provided in request header and checking
	 * revocation status
	 * 
	 * @param jwt - JWT contained in request
	 * @return Boolean value indicating if token is authentic
	 */
	public boolean isAuthentic(String jwt) {

		// Initialize firebase config.
		authUtil.firebaseInitialize();

		// authenticates provided token with Firebase
		FirebaseToken decodedToken = null;
		String uid = null;
		try {
			decodedToken = FirebaseAuth.getInstance().verifyIdToken(jwt);
			uid = decodedToken.getUid();
		} catch (FirebaseAuthException e) {
			log.warn("Invalid JWT token");
		} finally {
			if (uid == null)
				FirebaseApp.getInstance().delete();
		}
		
		if (uid != null && !AuthenticatorUtils.isFirebaseRevoked(jwt))
			return true;
		return false;
	}

	/**
	 * Method for retrieving User Id from JWT
	 * 
	 * @param jwt - JWT contained in request
	 * @return UserId in integer form of user submitting request
	 */
	public String getUserId(String jwt) {
		// Initialize firebase config.
		authUtil.firebaseInitialize();

		// authenticates provided token with Firebase
		FirebaseToken decodedToken = null;
		String userId = null;
		try {
			decodedToken = FirebaseAuth.getInstance().verifyIdToken(jwt);
			userId = decodedToken.getUid();
		} catch (FirebaseAuthException e) {
			log.warn("Invalid JWT token");
		} finally {
			if (userId == null)
			FirebaseApp.getInstance().delete();
		}
		// returns userId from parsing JWT
		return String.valueOf(userId);
	}
}
