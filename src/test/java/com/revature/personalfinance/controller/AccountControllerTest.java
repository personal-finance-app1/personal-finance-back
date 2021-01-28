package com.revature.personalfinance.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.revature.personalfinance.service.Authenticator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.personalfinance.model.Account;
import com.revature.personalfinance.service.IAccountService;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {
	public AccountController accountController;
	public Authenticator authenticator;
    @Mock
    public IAccountService accountService;
    public Account account;
    ObjectMapper objectMapper;
    
    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
        authenticator = new Authenticator();
        accountController = new AccountController(accountService, authenticator);
        account = new Account(1,"test-user","test",1,1);
        when(accountService.updateAccount(any())).thenReturn(null);
        when(accountService.updateAccount(account)).thenReturn(account);
        when(accountService.verifyAccount(account)).thenReturn(true);
    }
    
    
    @Test
    public void testUpdateAccountSuccess() {
        ResponseEntity<Account> returnedAccount = accountController.updateAccount("", account);
        assertEquals(account,objectMapper.convertValue(returnedAccount.getBody(), Account.class));
    }
    
    @Test
    public void testUpdateAccountFail() {
        Account badAccount = new Account(1000,"invalid-user","NotInDB",1000,1000);
		ResponseEntity<Account> returnedAccount = accountController.updateAccount("", badAccount);
        assertNotEquals(account,objectMapper.convertValue(returnedAccount.getBody(), Account.class));
    }
    
    @Test
    public void testUpdateAccountNull() {
    	ResponseEntity<Account> returnedAccount = accountController.updateAccount("", null);
        assertEquals(null,objectMapper.convertValue(returnedAccount.getBody(), Account.class));
    }
}
