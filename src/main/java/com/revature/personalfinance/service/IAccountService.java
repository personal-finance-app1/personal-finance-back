package com.revature.personalfinance.service;

import com.revature.personalfinance.model.Account;

public interface IAccountService {
	
	public Account updateAccountExpenses(Account account);
	public boolean verifyAccount(Account account);

}
