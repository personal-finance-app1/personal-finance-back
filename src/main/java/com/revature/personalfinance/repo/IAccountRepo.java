package com.revature.personalfinance.repo;

import com.revature.personalfinance.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IAccountRepo extends JpaRepository<Account, Integer> {

    List<Account> getAccountsByUserId(int userId);

//    Account updateAccount(Account account);

    // For Stretch goal (multiple tracked accounts)
    // void deleteAccountByAccountId(int accountId);
    
}
