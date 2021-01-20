package com.revature.personalfinance.service.impl;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.personalfinance.repo.IAccountRepo;
import com.revature.personalfinance.service.IAccountService;

@RunWith(MockitoJUnitRunner.class)
class AccountServiceImplTest {

	@Mock
	private IAccountRepo mockedAccountRepo; 
	private IAccountService accountService;
	
	@Before
	public void setup() {
		accountService = new AccountServiceImpl(mockedAccountRepo);
	
	}
}
