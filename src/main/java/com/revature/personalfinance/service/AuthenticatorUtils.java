package com.revature.personalfinance.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthErrorCode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

@Component
public class AuthenticatorUtils {
	
	private static final Logger log = LogManager.getLogger();


	/**
	 * Method for initializing firebase Admin SDK
	 * 
	 * GoogleCredentials.getApplicationDefault() method looks for credentials at the
	 * following system env variable: GOOGLE_APPLICATION_CREDENTIALS with a path to
	 * the tim-gattie-firebase-service-account.json file The fire-base-client config
	 * file in the src/main/resources folder is simply to provide as throrough an
	 * access for the Admin SDK being created for the backend as we don't have
	 * direct database credentials available.
	 */
	public void firebaseInitialize() {
		FirebaseOptions options = null;
		try {
			options = FirebaseOptions.builder().setCredentials(GoogleCredentials.getApplicationDefault()).build();
		} catch (Exception e) {

		}
		try {
			FirebaseApp.initializeApp(options);
		} catch(Exception e) {
			System.out.println("error");
		}
	}

	/**
	 * Method for checking revoked status of token provided in request header The
	 * auth token may be valid but have been revoked by the firebase server
	 * 
	 * @param jwt - JWT contained in request
	 * @return Boolean value indicating if token is revoked
	 */
	public static boolean isFirebaseRevoked(String jwt) {
		try {
			// Verify the ID token while checking if the token is revoked by passing
			// checkRevoked as true.
			boolean checkRevoked = true;
			FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(jwt, checkRevoked);
			// Token is valid and not revoked.
			String uid = decodedToken.getUid();
		} catch (FirebaseAuthException e) {
			if (e.getAuthErrorCode() == AuthErrorCode.REVOKED_ID_TOKEN) {
				// Token has been revoked. Inform the user to re-authenticate or signOut() the
				// user.
				return true;
			}
		}
		return false;
	}

}
