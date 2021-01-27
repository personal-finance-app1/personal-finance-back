package com.revature.personalfinance.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
@RunWith(MockitoJUnitRunner.class)
public class AuthenticatorTests {

	// We will need more accurate JWTs that we can manipulate before being able to
	// complete the testing
	

	String validJWT = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImEyYjkxODJiMWI0NmNiN2ZjN2MzMTFlZTgwMjFhZDY1MmVlMjc2MjIiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiRGVtbyBVc2VyIiwiaXNzIjoiaHR0cHM6Ly9zZWN1cmV0b2tlbi5nb29nbGUuY29tL3RyYWluaW5nLXRlYW0tMjUzOTE2IiwiYXVkIjoidHJhaW5pbmctdGVhbS0yNTM5MTYiLCJhdXRoX3RpbWUiOjE2MTE3MTU5MjIsInVzZXJfaWQiOiJPVENuc1JVMEdUY2lJendMMTFiT2o4SjJPZ0QyIiwic3ViIjoiT1RDbnNSVTBHVGNpSXp3TDExYk9qOEoyT2dEMiIsImlhdCI6MTYxMTcxNTkyMiwiZXhwIjoxNjExNzE5NTIyLCJlbWFpbCI6ImRlbW8udXNlckByZXZhdHVyZS5uZXQiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZW1haWwiOlsiZGVtby51c2VyQHJldmF0dXJlLm5ldCJdfSwic2lnbl9pbl9wcm92aWRlciI6InBhc3N3b3JkIn19.bqJon9qPCQ7AAmBfxNfz8XnrP_riqdO3R3Yot0bRMoRAvnOTuULF4EdSsOhp9V_ajkJwUYrtMjWZkY_Ntx3uBIji_3TEz8n6A5sLhy-eg8b4zeaTOmtBwfV05S-7UTIqg8E28i6eHqwYJ7lKHNipwyBqMLX-7yJGx3FWr8fTkB82PB8GjNCPgepXY4_SdYZ_04NeT2lmza8apFiwiSqAgXEcdP1gRnyth-3B6p-Aw07u1nOU-Ej4RivL-StlncQzv74WTlqo3NctWzY-f9sO_sY9HfbTMikSoZ7_oaMgzg9wzirW5muemeow3JYpJI7TQgmaKLnQorJS-qv6KISCXQ";
	String invalidJWT = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9."
			+ "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ."
			+ "SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

	@Mock
	Authenticator authenticatorMock;

	/**
	 * Method for testing isAuthentic() with a valid JWT
	 */
//	@Test
//	public void testIsAuthenticSuccess() throws InterruptedException {
//		boolean result = Authenticator.isAuthentic(validJWT);
//		assertTrue(result);
//	}


@Before
public void setUp() {
	
	
	
	when(authenticatorMock.isAuthentic(validJWT)).thenReturn(true);
	when(authenticatorMock.isAuthentic(any())).thenReturn(false);
   
	
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
	 */
	@Test
	public void testIsAuthenticFail2() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			authenticatorMock.isAuthentic("");
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
		String result = authenticatorMock.getUserId(invalidJWT);
		assertEquals("null", result);
	}

	/**
	 * Method for testing getUserId() with an empty string from JWT
	 */
	@Test
	public void testUserIdFail2() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			authenticatorMock.getUserId("");
		});
	}

}
