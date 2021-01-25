package com.revature.personalfinance.service;

import java.util.List;

import com.revature.personalfinance.model.Account;

public interface IAccountService {
	
	public Account updateAccount(Account account);
	public boolean verifyAccount(Account account);
	public List<Account> getAllAccountsByUserId(Integer userId);

}
