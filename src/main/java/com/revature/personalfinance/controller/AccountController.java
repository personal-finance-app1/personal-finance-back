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
@CrossOrigin
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
    
    /**
     * Will verify JWT token in the header before sending account to the service layer.
     * @param account The account to be updated in the database.
     * @return Returns the updated account on success and null on failure.
     */
    @PatchMapping()
    public ResponseEntity<Account> updateAccount(@RequestHeader(name = "Authorization") String jwt, @RequestBody Account account) {
        ResponseEntity<Account> returnEntity = ResponseEntity.status(400).body(null);
        Account persistedAccount = null;
        if(!authenticator.isAuthentic(jwt)) {
        	log.warn(jwt + "is not an authentic JWT token");
        	return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if(this.accountService == null) {
        	log.error("internal server error when calling updateAccount()");
            returnEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        else if(accountService.verifyAccount(account)){
            persistedAccount = this.accountService.updateAccount(account);
            
            if(persistedAccount != null) //updateAccount returns non null if the account was updated, otherwise its still a bad req.
                returnEntity = ResponseEntity.status(HttpStatus.OK).body(persistedAccount);
        } 

    	return returnEntity;
    }

    /**
     * Obtains the account after login. In future iterations will retrieve every account associated with the user
     * so that the user can choose between them.
     * @param userAccount Passes in entire object, but only accesses the userId to locate the user's accounts.
     * @return Returns a list of accounts belonging to the user.
     */
    @GetMapping()
    public ResponseEntity<List<Account>> getAllAccountsByUser(@RequestHeader(name = "Authorization") String jwt){
        String userId = authenticator.getUserId(jwt);

        ResponseEntity<List<Account>> returnEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        if (!authenticator.isAuthentic(jwt)) {
        	log.warn(jwt + "token is not valid");
        	return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        List<Account> returnList = null;


        if(this.accountService == null) {
        	log.error("internal error occurred in getAllAccountsByUser()");
            returnEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        else if(userId != null && !userId.equals("")) {
            returnList = this.accountService.getAllAccountsByUserId(userId);
            returnEntity = ResponseEntity.status(200).body(returnList);
        }
        
        return returnEntity;
    }
    
}
