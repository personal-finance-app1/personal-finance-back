package com.revature.personalfinance.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.ArgumentMatchers.any;
import com.revature.personalfinance.controller.AccountController;
import com.revature.personalfinance.model.Account;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {
	public AccountController accountController;
    @Mock
    public IAccountService accountService;
    public Account account;
    ObjectMapper objectMapper;
    
    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
        
        accountController = new AccountController(accountService);
        account = new Account(1,1,"test",1,1);
        when(accountService.updateAccount(any())).thenReturn(null);
        when(accountService.updateAccount(account)).thenReturn(account);
    }
    
    @Test
    public void testUpdateAccountSuccess() {
        ResponseEntity<Account> returnedAccount = accountController.updateAccount(account);
        assertEquals(account,objectMapper.convertValue(returnedAccount.getBody(), Account.class));
    }
    @Test
    public void testUpdateAccountFail() {
        Account badAccount=new Account(1000,1000,"NotInDB",1000,1000);
		ResponseEntity<Account> returnedAccount = accountController.updateAccount(badAccount);
        assertNotEquals(account,objectMapper.convertValue(returnedAccount.getBody(), Account.class));
    }
    @Test
    public void testUpdateAccountNull() {
    	ResponseEntity<Account> returnedAccount = accountController.updateAccount(null);
        assertEquals(null,objectMapper.convertValue(returnedAccount.getBody(), Account.class));
    }
}