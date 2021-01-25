package com.revature.personalfinance.service.impl;

import org.springframework.stereotype.Service;

import com.revature.personalfinance.model.Account;
import com.revature.personalfinance.repo.IAccountRepo;
import com.revature.personalfinance.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {
		
	private IAccountRepo accountRepo;
	
	public AccountServiceImpl(IAccountRepo accountRepo) {
		super();
		this.accountRepo = accountRepo;
	}

	@Override
	public Account updateAccountExpenses(Account account) {
		Account updatedAccount = null;
		if  (verifyAccount(account)) {
			updatedAccount = accountRepo.save(account);
		}
		return updatedAccount;
	}

	@Override
	public boolean verifyAccount(Account account) {
		if (account == null) {
			// log
			return false;
		}
		else if (account.getAccountId() <= 0) {
			// log
			return false;
		}
		else if (account.getUserId() <= 0) {
			// log
			return false;
		}
		else if (account.getName() == null || account.getName() == "") {
			// log
			return false;
		}
		else if (account.getIncome() < 0) {
			// log
			return false;
		}
		else if (account.getExpenses() < 0) {
			// log
			return false;
		}
		else return true;
	}

}
