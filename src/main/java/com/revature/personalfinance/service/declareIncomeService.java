package com.revature.personalfinance.service;

import com.revature.personalfinance.model.Account;
import com.revature.personalfinance.repo.IAccountRepo;

public class declareIncomeService {
	
	private IAccountRepo aDao;	

	public declareIncomeService(IAccountRepo aDao) {
		super();
		this.aDao = aDao;
	}

	public Account getAccountById(int i) {
		return null;
	}

	public Account updateAccount(Account acc) {
		return null;
	}

}
