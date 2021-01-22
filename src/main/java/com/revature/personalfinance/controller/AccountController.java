package com.revature.personalfinance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.personalfinance.model.Account;
import com.revature.personalfinance.service.IAccountService;

@RestController
public class AccountController {
    
    private IAccountService accountService;
    
    @Autowired
    public AccountController(IAccountService accountService) {
        super();
        this.accountService = accountService;
    }
    
    @PutMapping(value="/expenses")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
    	return null;
    }
    
}
