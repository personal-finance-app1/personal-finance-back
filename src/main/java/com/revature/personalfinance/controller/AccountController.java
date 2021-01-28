package com.revature.personalfinance.controller;

import com.google.api.Http;
import com.google.api.HttpOrBuilder;
import com.revature.personalfinance.service.Authenticator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.revature.personalfinance.model.Account;
import com.revature.personalfinance.service.IAccountService;

@RestController
@RequestMapping("/accounts")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {
    
    private final IAccountService accountService;
    private final Authenticator authenticator;

    private static final Logger log = LogManager.getLogger();
    
    @Autowired
    public AccountController(IAccountService accountService, Authenticator authenticator) {
        super();
        this.accountService = accountService;
        this.authenticator = authenticator;
    }
    
    @PatchMapping()
    public ResponseEntity<Account> updateAccount(@RequestHeader(name = "Authorization") String jwt, @RequestBody Account account) {
        ResponseEntity<Account> returnEntity = ResponseEntity.status(400).body(null);
        Account persistedAccount = null;
        if(!authenticator.isAuthentic(jwt)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        if(this.accountService == null) {
            returnEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        else if(accountService.verifyAccount(account)){
            persistedAccount = this.accountService.updateAccount(account);
            
            if(persistedAccount != null) //updateAccount returns non null if the account was updated, otherwise its still a bad req.
                returnEntity = ResponseEntity.status(HttpStatus.OK).body(persistedAccount);
        } 

    	return returnEntity;
    }

    @GetMapping()
    public ResponseEntity<List<Account>> getAllAccountsByUser(@RequestHeader(name = "Authorization") String jwt){
        log.info(jwt);
        String userId = "";
        userId = authenticator.getUserId(jwt);
        String message = String.format("userId: %s", userId);
        log.info(message);

        ResponseEntity<List<Account>> returnEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        if (!authenticator.isAuthentic(jwt)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        List<Account> returnList = null;


        if(this.accountService == null) {
            returnEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        else if(userId != null && !userId.equals("")) {
            returnList = this.accountService.getAllAccountsByUserId(userId);
            returnEntity = ResponseEntity.status(200).body(returnList);
        }
        
        return returnEntity;
    }
    
}
