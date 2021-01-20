package com.revature.personalfinance.service.impl;

import com.revature.personalfinance.model.Account;
import com.revature.personalfinance.repo.IAccountRepo;
import com.revature.personalfinance.service.IAccountService;

public class AccountServiceImpl implements IAccountService {
		
	private IAccountRepo accountRepo;
	public AccountServiceImpl(IAccountRepo accountRepo) {
		super();
		this.accountRepo = accountRepo;
	}

	@Override
	public Account updateAccountExpenses(Account account) {
		Account returnedAccount=null;
		if(account!=null) {
		 
		}
		else if(account.getExpenses()<0) {
			
		}
		else
			returnedAccount =accountRepo.updateAccount(account);
		
		return returnedAccount;
	}

}
