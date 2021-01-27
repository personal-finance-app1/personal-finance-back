package com.revature.personalfinance.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class AuthenticatorTests {

	// We will need more accurate JWTs that we can manipulate before being able to
	// complete the testing
	String validJWT = "valid";
	String invalidJWT = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9."
			+ "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ."
			+ "SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
	
	@Mock
	Authenticator authenticatorMock;
	
	@Before
	public void setUp() {
		when(authenticatorMock.isAuthentic(anyString())).thenReturn(false);
		when(authenticatorMock.getUserId(anyString())).thenReturn(null);
		when(authenticatorMock.isAuthentic(validJWT)).thenReturn(true);
		when(authenticatorMock.getUserId(validJWT)).thenReturn("validName");
	}

	/**
	 * Method for testing isAuthentic() with a valid JWT
	 */
	@Test
	public void testIsAuthenticSuccess() {
		boolean result = authenticatorMock.isAuthentic(validJWT);
		assertTrue(result);
	}

	/**
	 * Method for testing isAuthentic() with an invalid JWT
	 */
	@Test
	public void testIsAuthenticFail1() {
		boolean result = authenticatorMock.isAuthentic(invalidJWT);
		assertFalse(result);
	}

	/**
	 * Method for testing isAuthentic() with an empty string for JWT
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testIsAuthenticFail2() {
		boolean result = authenticatorMock.isAuthentic("");
		assertFalse(result);
	}

	/**
	 * Method for testing getUserName() with a valid JWT username
	 */
	@Test
	public void testUsernameSuccess() {
		String result = authenticatorMock.getUserId(validJWT);
		assertEquals("validName", result);
	}

	/**
	 * Method for testing getUserId() with an invalid JWT userId
	 */
	@Test
	public void testUserIdFail1() {
		String result = authenticatorMock.getUserId(invalidJWT);
		assertNull(result);
	}

	/**
	 * Method for testing getUserId() with an empty string from JWT
	 */
	@Test
	public void testUserIdFail2() {
		String result = authenticatorMock.getUserId("");
		assertNull(result);
	}

}
