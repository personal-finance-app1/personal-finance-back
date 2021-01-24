package com.revature.personalfinance.service;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PutMapping;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.ArgumentMatchers.any;

import com.revature.personalfinance.controller.AccountController;
import com.revature.personalfinance.model.Account;
import com.revature.personalfinance.service.IAccountService;

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
        when(accountService.updateAccountExpenses(any())).thenReturn(null);
        when(accountService.updateAccountExpenses(account)).thenReturn(account);
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