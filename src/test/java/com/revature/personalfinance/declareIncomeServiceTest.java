package com.revature.personalfinance;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;

import com.revature.personalfinance.model.Account;
import com.revature.personalfinance.repo.IAccountRepo;
import com.revature.personalfinance.service.declareIncomeService;


@RunWith(MockitoJUnitRunner.class)
public class declareIncomeServiceTest {
	
	@Mock
	private IAccountRepo mockedDao;
	
	private declareIncomeService testService;
	private Account acc;
	private Account acc2;

	@Before
	public void setUp() {
		testService = new declareIncomeService(mockedDao);
		acc = new Account(1, 1, "firstUser", 100, 50);
		acc2 = new Account(2, 2, "secondUser", 200, 100);
		when(mockedDao.updateAccount(anyObject())).thenReturn(null);
		when(mockedDao.updateAccount(acc)).thenReturn(acc);
	}

	@Test
	public void testupdateAccountSuccess() {
		assertEquals(new Account(1, 1, "firstUser", 100, 50), testService.updateAccount(acc));
	}
	
	@Test
	public void testupdateAccountFail() {
		assertEquals(null, testService.updateAccount(acc2));
	}	
}
