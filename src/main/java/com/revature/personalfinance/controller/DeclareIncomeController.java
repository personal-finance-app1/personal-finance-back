package com.revature.personalfinance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.personalfinance.model.Account;
import com.revature.personalfinance.service.DeclareIncomeService;

@RestController
public class DeclareIncomeController {
	
	private DeclareIncomeService declareIncomeService;
	
	@Autowired
	public DeclareIncomeController(DeclareIncomeService declareIncomeService) {
		super();
		this.declareIncomeService = declareIncomeService;
	}

	/**
	 * Method for updating the provided account in the database
	 * @param account
	 * @return
	 */
	@PutMapping(value="/account")
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
	
	/**
	 * Method for retrieving an account from the database by userId
	 * @param userId
	 * @return
	 */
	@GetMapping(value="/account")
	public ResponseEntity<Account> getAccount(@RequestBody int userId){
		
		Account account = null;
		
		if(userId > 0) {
			account = declareIncomeService.getAccount(userId);
			if(account == null) {
				return ResponseEntity.status(500).build();
			}
			return ResponseEntity.status(200).body(account);
		}
				
		return ResponseEntity.status(400).build();
		
	}
}



