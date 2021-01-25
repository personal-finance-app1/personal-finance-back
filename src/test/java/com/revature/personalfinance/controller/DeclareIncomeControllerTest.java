package com.revature.personalfinance.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.personalfinance.model.Account;
import com.revature.personalfinance.service.DeclareIncomeService;

@RunWith(MockitoJUnitRunner.class)
public class DeclareIncomeControllerTest {
	
	@Mock
	public DeclareIncomeService mockedService;
	
	public DeclareIncomeController cont;
	public ObjectMapper objectMapper;
	public Account acc;
	public Account badAcc;
	
	@Before
	public void setUp(){
		cont = new DeclareIncomeController(mockedService);
		objectMapper = new ObjectMapper();
		
		acc = new Account(1, 1, "firstUser", 100, 50);
		badAcc = new Account(-1, 1, "bad", -50, -50);
		
		when(mockedService.updateAccount(anyObject())).thenReturn(null);
		when(mockedService.updateAccount(badAcc)).thenReturn(null);
		when(mockedService.updateAccount(acc)).thenReturn(acc);
	}
	
	@Test
	public void testBadInput() {
		ResponseEntity<Account> updatedAccount = cont.updateAccount(null);
		assertEquals(null, objectMapper.convertValue(updatedAccount.getBody(), Account.class));
	}
	
	@Test
	public void testGoodInput() {
		ResponseEntity<Account> updatedAccount = cont.updateAccount(acc);
		assertEquals(acc, objectMapper.convertValue(updatedAccount.getBody(), Account.class));
	}
	
	@Test
	public void testServerError() {
		ResponseEntity<Account> updatedAccount = cont.updateAccount(badAcc);
		assertEquals(null, objectMapper.convertValue(updatedAccount.getBody(), Account.class));
	}
	

}
