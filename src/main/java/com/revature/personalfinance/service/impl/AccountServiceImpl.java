package com.revature.personalfinance.service.impl;

import com.revature.personalfinance.model.Account;
import com.revature.personalfinance.repo.IAccountRepo;
import com.revature.personalfinance.service.IAccountService;

import org.springframework.beans.factory.annotation.Autowired;

/**AccountServiceImpl exposes several service related methods related to accounts 
 * such as updating expenses and retrieving accounts.
 */
public class AccountServiceImpl implements IAccountService {
	
	/**accountRepo is a spring-data jpa repository which can perform CRUD operations on Account objects.*/
	private IAccountRepo accountRepo;

	@Autowired
	public AccountServiceImpl(IAccountRepo accountRepo) {
		super();
		this.accountRepo = accountRepo;
	}


	/**
	 * updateAccountExpenses updates the expenses of an account stored in the database
	 * @param account the account which we'd like to update which should have all of the needed information to perform the update.
	 * @return Account the updated account object.
	 */
	@Override
	public Account updateAccountExpenses(Account account) {
		Account persistedAccount = null; //acct stored in db

		if(this.accountRepo != null && this.verifyAccount(account)){
			persistedAccount = this.accountRepo.getOne(account.getAccountId()); //get account
			persistedAccount.setExpenses(account.getExpenses()); //update expenses
			this.accountRepo.save(persistedAccount); //persist updated account
		}
	
		return persistedAccount;
	}

	/**
	 * verifyAccount ensures that an account's sensitive state is matched up to the state in the database
	 * @param account the account which we'd like to verify 
	 * @return boolean value indicating that account contains a valid name and id combination.
	 */
	@Override
	public boolean verifyAccount(Account account) {
		boolean verifiedAccount = false; //boolean indicating if the account is verified
		Account persistedAccount = null; //reference to account 

		if(account != null){
			persistedAccount = this.accountRepo.getOne(account.getAccountId());

			if(persistedAccount != null) {
				verifiedAccount = persistedAccount.getName() != null && 
									persistedAccount.getName().equals(account.getName());
			}
		}
		return verifiedAccount;
	}

}
