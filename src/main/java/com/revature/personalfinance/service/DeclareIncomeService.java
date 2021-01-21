package com.revature.personalfinance.service;

import com.revature.personalfinance.model.Account;
import com.revature.personalfinance.repo.IAccountRepo;

public class DeclareIncomeService {
	
	private IAccountRepo aDao;	

	public DeclareIncomeService(IAccountRepo aDao) {
		super();
		this.aDao = aDao;
	}

	public Account updateAccount(Account acc) {
		Account updatedAccount = this.aDao.save(acc);
		
		if(updatedAccount == null) {
			return null;
		}
		
		return updatedAccount;
	}

}
