package com.revature.personalfinance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {
    
    private IAccountService accountService;
    
    @Autowired
    public AccountController(IAccountService accountService) {
        super();
        this.accountService = accountService;
    }
    
    /**
     * Will verify JWT token in the header before sending account to the service layer.
     * @param account The account to be updated in the database.
     * @return Returns the updated account on success and null on failure.
     */
    @PatchMapping()
    public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
        ResponseEntity<Account> returnEntity = ResponseEntity.status(400).body(null);
        Account persistedAccount = null;

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

    /**
     * Obtains the account after login. In future iterations will retrieve every account associated with the user
     * so that the user can choose between them.
     * @param userAccount Passes in entire object, but only accesses the userId to locate the user's accounts.
     * @return Returns a list of accounts belonging to the user.
     */
    @GetMapping()
    public ResponseEntity<List<Account>> getAllAccountsByUser(@RequestBody Account userAccount){
        ResponseEntity<List<Account>> returnEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        List<Account> returnList = null;
        String userId = userAccount.getUserId();

        if(this.accountService == null) {
            returnEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        else if(userAccount != null && userAccount.getUserId() != null && !userAccount.getUserId().equals("")){
            userId = userAccount.getUserId();
            returnList = this.accountService.getAllAccountsByUserId(userId);
            returnEntity = ResponseEntity.status(200).body(returnList);
        }
        
        return returnEntity;
    }
    
}
