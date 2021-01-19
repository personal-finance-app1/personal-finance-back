package com.revature.personalfinance;

import org.junit.Before;
import org.mockito.Mock;

import com.revature.personalfinance.model.Account;
import com.revature.personalfinance.repo.IAccountRepo;
import com.revature.personalfinance.service.declareIncomeService;

public class declareIncomeServiceTest {
	
	@Mock
	private IAccountRepo mockedDao;
	private declareIncomeService testService;
	private Account acc;
		
	@Before
	public void setUp() {
		testService = new declareIncomeService();
		acc = new Account();
		acc = new Account(1, 1, "firstUser", 100, 50, 500);
		
	}
	
//	@Test
	
}
