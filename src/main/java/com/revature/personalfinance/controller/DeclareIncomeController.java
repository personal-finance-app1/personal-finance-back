package com.revature.personalfinance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.revature.personalfinance.model.Account;
import com.revature.personalfinance.service.DeclareIncomeService;


public class DeclareIncomeController {
	
	private DeclareIncomeService declareIncomeService;
	
	@Autowired
	public DeclareIncomeController(DeclareIncomeService declareIncomeService) {
		super();
		this.declareIncomeService = declareIncomeService;
	}

	public ResponseEntity<Account> updateAccount(Account account){
		
		return null;
	}
}
