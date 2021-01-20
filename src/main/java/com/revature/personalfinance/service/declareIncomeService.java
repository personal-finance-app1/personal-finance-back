package com.revature.personalfinance.service;

import com.revature.personalfinance.model.Account;
import com.revature.personalfinance.repo.IAccountRepo;

public class declareIncomeService {
	
	private IAccountRepo aDao;	

	public declareIncomeService(IAccountRepo aDao) {
		super();
		this.aDao = aDao;
	}

	public Account updateAccount(Account acc) {
		Account updatedAccount = this.aDao.updateAccount(acc);
		
		if(updatedAccount == null) {
			return null;
		}
		
		return updatedAccount;
	}

}
