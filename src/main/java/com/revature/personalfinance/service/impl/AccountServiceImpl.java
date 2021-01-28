package com.revature.personalfinance.service.impl;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.revature.personalfinance.model.Account;
import com.revature.personalfinance.repo.IAccountRepo;
import com.revature.personalfinance.service.IAccountService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements IAccountService {

	private IAccountRepo accountRepo;
	
	private static final Logger log = LogManager.getLogger();

	@Autowired
	public AccountServiceImpl(IAccountRepo accountRepo) {
		super();
		this.accountRepo = accountRepo;
	}

	/**
	 * Updates the expenses of an account stored in the database.
	 * @param account The account which we'd like to update which should have all of
	 *                the needed information to perform the update.
	 * @return Returns the updated account object.
	 */
	@Override
	public Account updateAccount(Account account) {
		Account persistedAccount = null; // acc stored in db

		if (this.accountRepo != null && verifyAccount(account)) {
			persistedAccount = this.accountRepo.findByAccountId(account.getAccountId()); // get account wrapper

			//If optional value is not empty (I.e, the db has a record a of Account account)
			if (persistedAccount != null) {
				persistedAccount.setExpenses(account.getExpenses()); // update expenses
				persistedAccount.setIncome(account.getIncome()); //update income
				this.accountRepo.save(persistedAccount); // persist updated account
			}else {
				log.warn("Account does not exist. The account is null.");
			}
		}

		return persistedAccount;
	}

	/**
	 * Verify account is used to validate any account we'd like to persist in the db by performing sensible
	 * checks against the values stored in said account.
	 * @param account The account to be verified.
	 * @return boolean indicating whether or no the values stored in the account are valid.
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

	 /**
	 * Retrieves a list of accounts associated with a user id.
	 * @param userId the id of the user which to.
	 * @return List of accounts owned by the user specified by {@code userId}.
	 */
	@Override
	public List<Account> getAllAccountsByUserId(String userId) {
		List<Account> usersAccountList = null;

		if(userId != null && userId != null && !userId.equals("")){
			usersAccountList = this.accountRepo.findAllByUserId(userId);
		}else {
			log.info("Invalid user Id");
		}
		return usersAccountList;
	}

}
