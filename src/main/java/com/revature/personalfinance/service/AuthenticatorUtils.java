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
	 * Method for initializing firebase Admin SDK 
	 * 
	 * GoogleCredentials.getApplicationDefault() method looks for credentials at the following system
	 * env variable: GOOGLE_APPLICATION_CREDENTIALS with a path to the tim-gattie-firebase-service-account.json file
	 * The fire-base-client config file in the src/main/resources folder is simply to provide as throrough an access 
	 * for the Admin SDK  being created for the backend as we don't have direct database credentials available.
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
	 * Method for checking revoked status of token provided in request header
	 * The auth token may be valid but have been revoked by the firebase server
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
				// Token has been revoked. Inform the user to re-authenticate or signOut() the user.
				return true;
			} 
		}
		return false;
	}

}
