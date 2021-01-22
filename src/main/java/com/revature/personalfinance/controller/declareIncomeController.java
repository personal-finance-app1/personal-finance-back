package com.revature.personalfinance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	@PutMapping
	public ResponseEntity<Account> updateAccount(@RequestBody Account acc){
		
		//JWT Validation
		
		Account a = declareIncomeService.updateAccount(acc);
		
		if(a != acc) { // If no change has occurred
			return ResponseEntity.status(500).build();
		}
		return ResponseEntity.status(202).body(acc);
	}
	
}
