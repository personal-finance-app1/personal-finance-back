package com.revature.personalfinance.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.google.firebase.auth.FirebaseAuthException;

public class AuthenticatorTests {

	// We will need more accurate JWTs that we can manipulate before being able to
	// complete the testing

//	String validJWT;
	String invalidJWT = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9."
			+ "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ."
			+ "SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

//	/**
//	 * Method for testing isAuthentic() with a valid JWT
//	 */
//	@Test
//	public void testIsAuthenticSuccess() throws InterruptedException {
//		boolean result = Authenticator.isAuthentic(validJWT);
//		System.out.println(result);
//		assertTrue(result);
//	}

	/**
	 * Method for testing isAuthentic() with an invalid JWT
	 */
	@Test
	public void testIsAuthenticFail1() {
		boolean result = Authenticator.isAuthentic(invalidJWT);
		assertFalse(result);
	}

	/**
	 * Method for testing isAuthentic() with an empty string for JWT
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testIsAuthenticFail2() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Authenticator.isAuthentic("");
		});
	}

//	/**
//	 * Method for testing getUserName() with a valid JWT username
//	 */
//	@Test
//	public void testUsernameSuccess() {
//		String result = Authenticator.getUserId(validJWT);
//		assertEquals("validName", result);
//	}

	/**
	 * Method for testing getUserId() with an invalid JWT userId
	 */
	@Test
	public void testUserIdFail1() {
		String result = Authenticator.getUserId(invalidJWT);
		assertEquals("null", result);
	}

	/**
	 * Method for testing getUserId() with an empty string from JWT
	 */
	@Test
	public void testUserIdFail2() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Authenticator.getUserId("");
		});
	}

}
