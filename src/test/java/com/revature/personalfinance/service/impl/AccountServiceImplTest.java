package com.revature.personalfinance.service.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
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
	private Account goodAccount;
	
	@Before
	public void setUp() {
		goodAccount = new Account(1, 2, "testUser", 10, 10);
		accountService = new AccountServiceImpl(mockedAccountRepo);
		
//		when(mockedAccountRepo.updateAccount(any())).thenReturn(null);
		when(mockedAccountRepo.updateAccount(goodAccount)).thenReturn(goodAccount);
	}
	
	@Test
	public void testUpdateAccountSuccess() {
		boolean check = false;
		if (accountService.updateAccountExpenses(goodAccount) != null)
			check = true;
		assertTrue(check);
	}
	
}
