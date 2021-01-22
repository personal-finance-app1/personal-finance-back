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
		
		Account updatedAccount = null;
		
		if(verifyAccount(acc)) {
			updatedAccount= this.aDao.save(acc);
		}
		
		return updatedAccount;
	}
	
    public boolean verifyAccount(Account account) {
        if(account == null) {
            // log warning
            return false;
        }
        else if(account.getAccountId() <= 0) {
            // log warning
            return false;
        }
        else if (account.getUserId() <= 0) {
            // log warning
            return false;
        }
        else if(account.getName() == null || account.getName() == "") {
            // log warning
            return false;
        }
        else if(account.getIncome() < 0) {
            // log warning
            return false;
        }
        else if(account.getExpenses() < 0) {
            // log warning
            return false;
        }
        else return true;
    }
}
