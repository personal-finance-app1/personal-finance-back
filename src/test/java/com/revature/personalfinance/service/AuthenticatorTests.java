package com.revature.personalfinance.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AuthenticatorTests {

	// We will need more accurate JWTs that we can manipulate before being able to complete the testing
	String validJWT = "";
	String invalidJWT = "";


	/**
	 * Method for testing isAuthentic() with a valid JWT
	 */
	@Test
	public void testIsAuthenticSuccess() {
		boolean result = false;
		try {
			result = Authenticator.isAuthentic(validJWT);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Authentic JWT case: " + validJWT);
		assertTrue(result);
	}

	/**
	 * Method for testing isAuthentic() with an invalid JWT
	 */
	@Test
	public void testIsAuthenticFail1() {
		boolean result = Authenticator.isAuthentic(invalidJWT);
		System.out.println("Invalid JWT case: " + invalidJWT);
		assertFalse(result);
	}

	/**
	 * Method for testing isAuthentic() with an empty string for JWT
	 */
	@Test
	public void testIsAuthenticFail2() {
		boolean result = Authenticator.isAuthentic("");
		System.out.println("Empty String JWT case. ");
		assertFalse(result);
	}

	/**
	 * Method for testing getUserName() with a valid JWT username
	 */
	@Test
	public void testUsernameSuccess() {
		String result = Authenticator.getUsername(validJWT);
		System.out.println("Valid getUsername case: " + validJWT);
		assertEquals("validName", result);
	}

	/**
	 * Method for testing getUserName() with an invalid JWT username
	 */
	@Test
	public void testUsernameFail1() {
		String result = Authenticator.getUsername(invalidJWT);
		System.out.println("Invalid getUserName with invalid JWT: " + invalidJWT);
		assertEquals("invalidName", result);
	}

	/**
	 * Method for testing getUserName() with an empty string fro JWT
	 */
	@Test
	public void testUsernameFail2() {
		String result = Authenticator.getUsername("");
		System.out.println("Invalid getUsername with empty JWT.");
		assertNull(result);
	}

}
