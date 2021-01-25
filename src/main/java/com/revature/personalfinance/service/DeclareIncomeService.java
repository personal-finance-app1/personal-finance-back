package com.revature.personalfinance.service;

import org.springframework.stereotype.Service;

import com.revature.personalfinance.model.Account;
import com.revature.personalfinance.repo.IAccountRepo;

@Service
public class DeclareIncomeService {
	
	private IAccountRepo aDao;	

	public DeclareIncomeService(IAccountRepo aDao) {
		super();
		this.aDao = aDao;
	}
	
	/**
	 * Method for updating values of an account
	 * @param acc
	 * @return
	 */
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
    
    /**
     * Method for retrieving the account of the provided userId
     * @param userId
     * @return
     */
    
    


	public Account getAccount(int userId) {
		
		Account account = null;
		
		if(userId > 0) {
			account= this.aDao.getOne(userId);
		}
		
		return account;
	}

}
