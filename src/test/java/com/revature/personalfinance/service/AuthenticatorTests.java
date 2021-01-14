package com.revature.personalfinance.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.runners.MockitoJUnitRunner;

//@RunWith(MockitoJUnitRunner.class)
public class AuthenticatorTests {

	Authenticator authTest = new Authenticator();
	String validJWT = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.N6_Glh0-bHSxjiZOgmIY6By_sog63l7bHRlxbExYPY8";
	String invalidJWT = "eyJhbGci0iJTUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIi0iIxMjM0NTY30DkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaXNTb2NpYWwiOnRydWV9.4pcPyMD09olPSyXnrXcjTwXyr4BsezdI1AVTmud2fU4";

	// Check valid JWT
	@Test
	public void testIsAuthenticSuccess() {
		boolean result = authTest.isAuthentic(validJWT);
		System.out.println(("Valid user DOB: " + authTest.toString()));
		assertTrue(result);
	}

	// Check invalid JWT
	@Test
	public void testIsAuthenticFail1() {
		boolean result = authTest.isAuthentic(invalidJWT);
		System.out.println(("Invalid user DOB: " + authTest.toString()));
		assertFalse(result);
	}

	// Check invalid JWT
	@Test
	public void testIsAuthenticFail2() {
		boolean result = authTest.isAuthentic("");
		System.out.println(("Invalid user DOB: " + authTest.toString()));
		assertFalse(result);
	}

	// Check valid getUsername method
	@Test
	public void testUsernameSuccess() {
		String result = authTest.getUsername("valid jwt");
		System.out.println(("Valid user DOB: " + authTest.toString()));
		assertEquals("validName", result);
	}

	// Check invalid getUsername method
	@Test
	public void testUsernameFail1() {
		String result = authTest.getUsername("valid jwt");
		System.out.println(("Valid user DOB: " + authTest.toString()));
		assertEquals("invalidName", result);
	}

	// Check invalid getUsername method
	@Test
	public void testUsernameFail2() {
		String result = authTest.getUsername("");
		System.out.println(("Valid user DOB: " + authTest.toString()));
		assertNull(result);
	}

}
