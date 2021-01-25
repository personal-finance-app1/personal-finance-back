package com.revature.personalfinance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.revature.personalfinance.model.Account;
import com.revature.personalfinance.service.IAccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    
    private IAccountService accountService;
    
    @Autowired
    public AccountController(IAccountService accountService) {
        super();
        this.accountService = accountService;
    }
    

    @PatchMapping()
    public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
        ResponseEntity<Account> returnEntity = ResponseEntity.status(400).body(null);
        Account persistedAccount = null;

        if(this.accountService == null) {
            returnEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        else if(this.accountService.verifyAccount(account)){
            persistedAccount = this.accountService.updateAccount(account);
            
            if(persistedAccount != null) //updateAccount returns non null if the account was updated, otherwise its still a bad req.
                returnEntity = ResponseEntity.status(HttpStatus.OK).body(persistedAccount);
        } 

    	return returnEntity;
    }

    @GetMapping()
    public ResponseEntity<List<Account>> getAllAccountsByUser(@RequestBody Account userAccount){
        ResponseEntity<List<Account>> returnEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        List<Account> returnList = null;
        Integer userId = userAccount.getUserId();

        if(this.accountService == null) {
            returnEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        else if(userAccount != null && userAccount.getUserId() > 0){
            userId = userAccount.getUserId();
            returnList = this.accountService.getAllAccountsByUserId(userId);
            returnEntity = ResponseEntity.status(200).body(returnList);
        }
        
        return returnEntity;
    }
    
}
