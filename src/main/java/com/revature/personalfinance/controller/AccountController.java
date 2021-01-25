
package com.revature.personalfinance.controller;

import com.revature.personalfinance.model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @GetMapping
    public ResponseEntity<String> getAccount(int uid) {
        return ResponseEntity.status(HttpStatus.OK).body("Welcome! Requests are working!");
    }
}
