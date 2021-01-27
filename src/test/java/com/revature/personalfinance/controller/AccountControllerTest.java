package com.revature.personalfinance.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import com.google.rpc.context.AttributeContext.Auth;
import com.revature.personalfinance.model.Account;
import com.revature.personalfinance.service.Authenticator;
import com.revature.personalfinance.service.AuthenticatorUtils;
import com.revature.personalfinance.service.IAccountService;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {
    public AccountController accountController;
    String testJWT = "test-jwt";
    @Mock
    public IAccountService accountService;
    public Account account;
    ObjectMapper objectMapper;
    public Authenticator mockedAuthenticator;
    public AuthenticatorUtils mockedUtils;
    
    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
        
        
        accountController = new AccountController(accountService,mockedAuthenticator);
        account = new Account(1,"test-user","test",1,1);
        when(accountService.updateAccount(any())).thenReturn(null);
        when(accountService.updateAccount(account)).thenReturn(account);
        when(accountService.verifyAccount(account)).thenReturn(true);
        when(mockedAuthenticator.isAuthentic(testJWT)).thenReturn(true);
        when(mockedAuthenticator.isAuthentic(any())).thenReturn(false);
        //when(mockedUtils)
       
        
    }
    
    
    
    @Disabled
    public void testUpdateAccountSuccess() {
        ResponseEntity<Account> returnedAccount = accountController.updateAccount(testJWT,account);
        assertEquals(account,objectMapper.convertValue(returnedAccount.getBody(), Account.class));
    }
    
    
    @Disabled
    public void testUpdateAccountFail() {
        Account badAccount = new Account(1000,"invalid-user","NotInDB",1000,1000);
		ResponseEntity<Account> returnedAccount = accountController.updateAccount(testJWT,badAccount);
        assertNotEquals(account,objectMapper.convertValue(returnedAccount.getBody(), Account.class));
    }
    
    
    public void testUpdateAccountNull() {
    	ResponseEntity<Account> returnedAccount = accountController.updateAccount(null,null);
        assertEquals(null,objectMapper.convertValue(returnedAccount.getBody(), Account.class));
    }
}