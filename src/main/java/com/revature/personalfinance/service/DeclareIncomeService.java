package com.revature.personalfinance.service;

import com.revature.personalfinance.model.Account;
import com.revature.personalfinance.repo.IAccountRepo;

public class DeclareIncomeService {
	
	private IAccountRepo aDao;	

	public DeclareIncomeService(IAccountRepo aDao) {
		super();
		this.aDao = aDao;
	}

	public Account updateAccount(Account acc) { //We are interested in returning Integer
		
		if(acc.getIncome() < 0) {
			return null;
		} else if(Math.floor(acc.getIncome()) != acc.getIncome()) {
			return null; 
		}
		
		Account updatedAccount = this.aDao.save(acc);
		
		return updatedAccount;
	}

}

//double num = 1.44;
//
//System.out.println("Calc1:");
//System.out.println((num*100));
//
//System.out.println("\nCalc2:");
//System.out.println(Math.floor(num*100));
//
//if(Math.floor(num*100) != (num * 100)) {
//	System.out.println("ERROR DECIMAL BAD"); //Decimal is incorrect
//} else {
//	System.out.println("GOOD NUMBER YES"); //No problems
