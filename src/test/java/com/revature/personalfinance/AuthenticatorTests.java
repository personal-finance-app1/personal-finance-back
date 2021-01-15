package com.revature.personalfinance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.revature.personalfinance.service.Authenticator;

public class AuthenticatorTests {

	// We will need more accurate JWTs that we can manipulate before being able to complete the testing
	String invalidJWT = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSJ9.mo0PL2Nx0nxyJMPrSNpT1CF2jgeKXzs6H_LUEeS91rc";


//	/**
//	 * Method for testing isAuthentic() with a valid JWT
//	 */
//	@Test
//	public void testIsAuthenticSuccess() {
//		boolean result = false;
//		try {
//			result = Authenticator.isAuthentic(validJWT);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		
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
	 */
	@Test
	public void testIsAuthenticFail2() {
		boolean result = Authenticator.isAuthentic("");
		assertFalse(result);
	}

//	/**
//	 * Method for testing getUserName() with a valid JWT username
//	 */
//	@Test
//	public void testUsernameSuccess() {
//		String result = Authenticator.getUsername(validJWT);
//		assertEquals("validName", result);
//	}

	/**
	 * Method for testing getUserName() with an invalid JWT username
	 */
	@Test
	public void testUsernameFail1() {
		String result = Authenticator.getUsername(invalidJWT);
		assertEquals("invalidName", result);
	}

	/**
	 * Method for testing getUserName() with an empty string fro JWT
	 */
	@Test
	public void testUsernameFail2() {
		String result = Authenticator.getUsername("");
		assertNull(result);
	}

}
