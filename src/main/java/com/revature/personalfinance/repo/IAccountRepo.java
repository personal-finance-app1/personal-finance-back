package com.revature.personalfinance.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.personalfinance.model.Account;


public interface IAccountRepo extends JpaRepository<Account, Integer> {

    Account getAccountByAccountId(int accountId);
    List<Account> getAccountsByUserId(int userId);

    Account updateAccount(Account account);

    // For Stretch goal (multiple tracked accounts)
    // void deleteAccountByAccountId(int accountId);
}
