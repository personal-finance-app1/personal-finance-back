package com.revature.personalfinance.controller;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.personalfinance.model.Account;
import com.revature.personalfinance.service.Authenticator;
import com.revature.personalfinance.service.IAccountService;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {
	
	public AccountController accountController;
	@Mock
	public Authenticator authenticator;
    @Mock
    public IAccountService accountService;
    public Account account;
    public List<Account> accountsList;
    ObjectMapper objectMapper;
    
    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
        accountController = new AccountController(accountService, authenticator);
        account = new Account(1,"test-user","test",1,1);
        accountsList = new ArrayList<>();
        
        Mockito.lenient().when(authenticator.getUserId(anyString())).thenReturn("badId");
        Mockito.lenient().when(authenticator.getUserId("jwt")).thenReturn("goodId");
        
        Mockito.lenient().when(authenticator.isAuthentic(anyString())).thenReturn(false);
        Mockito.lenient().when(authenticator.isAuthentic("jwt")).thenReturn(true);
        
        Mockito.lenient().when(accountService.getAllAccountsByUserId("goodId")).thenReturn(accountsList);
        Mockito.lenient().when(accountService.updateAccount(any())).thenReturn(null);
        Mockito.lenient().when(accountService.updateAccount(account)).thenReturn(account);
        Mockito.lenient().when(accountService.verifyAccount(account)).thenReturn(true);
    }
    
    @Test
    public void testGetAllAccountsByUserSuccess() {
        ResponseEntity<List<Account>> returnedAccount = accountController.getAllAccountsByUser("jwt");
        assertEquals(HttpStatus.OK, returnedAccount.getStatusCode());
    }
    
    @Test
    public void testGetAllAccountsByUserFail() {
		ResponseEntity<List<Account>> returnedAccount = accountController.getAllAccountsByUser("bad");
        assertEquals(HttpStatus.FORBIDDEN,returnedAccount.getStatusCode());
    }
    
    @Test
    public void testUpdateAccountSuccess() {
        ResponseEntity<Account> returnedAccount = accountController.updateAccount("jwt", account);
        assertNotNull(objectMapper.convertValue(returnedAccount.getBody(), Account.class));
    }
    
    @Test
    public void testUpdateAccountFail() {
        Account badAccount = new Account(1000,"invalid-user","NotInDB",1000,1000);
		ResponseEntity<Account> returnedAccount = accountController.updateAccount("jwt", badAccount);
        assertNotEquals(account,objectMapper.convertValue(returnedAccount.getBody(), Account.class));
    }
    
    @Test
    public void testUpdateAccountNull() {
    	ResponseEntity<Account> returnedAccount = accountController.updateAccount("jwt", null);
        assertNull(objectMapper.convertValue(returnedAccount.getBody(), Account.class));
    }
}
