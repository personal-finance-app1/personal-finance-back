package com.revature.personalfinance.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.personalfinance.model.Account;
import com.revature.personalfinance.service.IAccountService;

//@RunWith(SpringRunner.class)
//@WebMvcTest(AccountController.class)
//@SpringBootTest
//@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {
	
	public AccountController ac;
	@Mock
	public IAccountService iac;
	public Account account;
	ObjectMapper objectMapper;
	
	@Before
	public void setUp() {
		objectMapper = new ObjectMapper();
		ac = new AccountController(iac);
		account = new Account(1,1,"test",1,1);
		when(iac.updateAccountExpenses(any())).thenReturn(null);
		when(iac.updateAccountExpenses(account)).thenReturn(account);
	}
	
	@Test
	public void test() {
//		HttpServletResponse response;
//		ResponseEntity<Account> a = new Account(1,1,"test",1,1);
		ResponseEntity<Account> returnedAccount = ac.updateAccount(account);
		assertEquals(account,objectMapper.convertValue(returnedAccount.getBody(), Account.class));
	}
	
	
	
	

//		int status = mvcResult.getResponse().getStatus();
//		assertEquals(200, status);
//		String content = mvcResult.getResponse().getContentAsString();
//		assertEquals(content, account);
//	}
	
	/*
	@Autowired
	private MockMvc mvc;

	@Test
	public void shouldUpdateAccount() throws Exception {
		Account account = new Account(1,1,"test",1,1);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
				.put("/expenses")
				.content(asJsonString(account))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.accountId").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("test"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.income").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.expenses").value(1))
				.andReturn();
		
		assertEquals(200, mvcResult.getResponse().getStatus());
		
	}
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	*/
}
