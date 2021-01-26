package com.revature.personalfinance.service.impl;

import java.util.List;
import java.util.Optional;

import com.revature.personalfinance.model.Account;
import com.revature.personalfinance.repo.IAccountRepo;
import com.revature.personalfinance.service.IAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/**
 * AccountServiceImpl exposes several service related methods related to
 * accounts such as updating expenses and retrieving accounts.
 */
public class AccountServiceImpl implements IAccountService {

	/**
	 * accountRepo is a spring-data jpa repository which can perform CRUD operations
	 * on Account objects.
	 */
	private IAccountRepo accountRepo;

	@Autowired
	public AccountServiceImpl(IAccountRepo accountRepo) {
		super();
		this.accountRepo = accountRepo;
	}

	/**
	 * updateAccountExpenses updates the expenses of an account stored in the
	 * database
	 * 
	 * @param account the account which we'd like to update which should have all of
	 *                the needed information to perform the update.
	 * @return Account the updated account object.
	 */
	@Override
	public Account updateAccount(Account account) {
		Account persistedAccount = null; // acct stored in db
		
		if (this.accountRepo != null && verifyAccount(account)) {
			persistedAccount = this.accountRepo.findByAccountId(account.getAccountId()); // get account wrapper

			//If optional value is not empty (I.e, the db has a record a of Account account)
			if (persistedAccount != null) {
				persistedAccount.setExpenses(account.getExpenses()); // update expenses
				persistedAccount.setIncome(account.getIncome()); //update income
				this.accountRepo.save(persistedAccount); // persist updated account
			}
		}

		return persistedAccount;
	}

	/**Verify account is used to validate any account we'd like to persist in the db by performing sensible 
	 * checks against the values stored in said account. 
	 * @return boolean indiciating whether or no the values stored in the account are valid.
	 */
	@Override
	public boolean verifyAccount(Account account) {
		boolean isNotNull = account != null;
		boolean hasValidNumericalValues = false;
		boolean hasValidName = false;

		if(isNotNull){
			hasValidNumericalValues = account.getAccountId() > 0 && account.getIncome() >= 0
									  && account.getExpenses() >= 0 && account.getUserId() != null && !account.getUserId().equals("");
			hasValidName = account.getName() != null && !account.getName().equals("");
		}


		return isNotNull && hasValidName && hasValidNumericalValues;
	}

	/**getAllAccountsByUser returns a list of accounts associated with a user.
	 * @param name the name of the user which to 
	 * @return List<Account> of accounts owned by the user specified by param name.
	 */
	@Override
	public List<Account> getAllAccountsByUserId(String userId) {
		List<Account> usersAccountList = null;

		if(userId != null && userId != null && !userId.equals("")){
			usersAccountList = this.accountRepo.findAllByUserId(userId);
		}
		return usersAccountList;
	}

}
