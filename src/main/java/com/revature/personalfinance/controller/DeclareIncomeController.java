package com.revature.personalfinance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.revature.personalfinance.model.Account;
import com.revature.personalfinance.service.DeclareIncomeService;


public class DeclareIncomeController {
	
	private DeclareIncomeService declareIncomeService;
	
	@Autowired
	public DeclareIncomeController(DeclareIncomeService declareIncomeService) {
		super();
		this.declareIncomeService = declareIncomeService;
	}

	@PutMapping(value="/income")
	public ResponseEntity<Account> updateAccount(@RequestBody Account account){

	Account updatedAccount = null;

		if(account != null) {
			updatedAccount = declareIncomeService.updateAccount(account);
			if(updatedAccount == null) {
				return ResponseEntity.status(500).build();
			}
			return ResponseEntity.status(200).body(updatedAccount);
		}
		return ResponseEntity.status(400).build();
	}
}



