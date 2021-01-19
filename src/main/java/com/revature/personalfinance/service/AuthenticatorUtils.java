package com.revature.personalfinance.service;

import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthErrorCode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

public class AuthenticatorUtils {

	/**
	 * Method for initializing firebase config
	 */
	public static void firebaseInitialize() {

		FirebaseOptions options = null;
		try {
			options = FirebaseOptions.builder().setCredentials(GoogleCredentials.getApplicationDefault())
					.setStorageBucket("training-team-253916.appspot.com").build();
		} catch (IOException e) {
			e.printStackTrace();
		}

		FirebaseApp.initializeApp(options);
	}

	/**
	 * Method for authenticating token provided in request header
	 * 
	 * @param jwt - JWT contained in request
	 * @return Boolean value indicating if token is revoked
	 */
	public static boolean isFirebaseRevoked(String jwt) {
		try {
			// Verify the ID token while checking if the token is revoked by passing checkRevoked as true.
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
