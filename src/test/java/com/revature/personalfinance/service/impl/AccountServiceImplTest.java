package com.revature.personalfinance.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.personalfinance.model.Account;
import com.revature.personalfinance.repo.IAccountRepo;
import com.revature.personalfinance.service.IAccountService;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {

	@Mock
	private IAccountRepo mockedAccountRepo; 
	private IAccountService accountService;
	private Account account;
	
	@Before
	public void setUp() {
		account = new Account(1, 2, "testUser", 10, 10);
		accountService = new AccountServiceImpl(mockedAccountRepo);
		
		when(mockedAccountRepo.save(any())).thenReturn(null);
		when(mockedAccountRepo.save(account)).thenReturn(account);
		when(mockedAccountRepo.getOne(account.getAccountId())).thenReturn(account);
	}
	
	@BeforeEach
	public void resetAccount(){
		account = new Account(1, 2, "testUser", 10, 10);
	}
	@Test
	public void testUpdateAccountFail() {
		Account badAccount = new Account(10, 10, "badUser", 20, 20);
		boolean check = true;
		if (accountService.updateAccount(badAccount) == null)
			check = false;
		assertFalse(check);
	}
	
	@Test
	public void testUpdateAccountSuccess() {
		boolean check = false;
		if (accountService.updateAccount(account) != null)
			check = true;
		assertTrue(check);
	}
	
	@Test
	public void testVerifyAccountNullFail() {
		assertFalse(accountService.verifyAccount(null));
	}
	
	@Test
	public void testVerifyAccountIdFail() {
		account.setAccountId(0);
		assertFalse(accountService.verifyAccount(account));
	}
	
	@Test
	public void testVerifyAccountUserIdFail() {
		account.setUserId(0);
		assertFalse(accountService.verifyAccount(account));
	}
	
	@Test
	public void testVerifyNameNullFail() {
		account.setName(null);
		assertFalse(accountService.verifyAccount(account));
	}
	
	@Test
	public void testVerifyNameEmptyFail() {
		account.setName("");
		assertFalse(accountService.verifyAccount(account));
	}
	
	@Test
	public void testVerifyNegativeIncomeFail() {
		account.setIncome(-1);
		assertFalse(accountService.verifyAccount(account));
	}
	
	@Test
	public void testVerifyNegativeExpenseFail() {
		account.setExpenses(-1);
		assertFalse(accountService.verifyAccount(account));
	}
}
