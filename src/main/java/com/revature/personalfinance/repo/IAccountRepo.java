package com.revature.personalfinance.repo;

import com.revature.personalfinance.model.Account;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IAccountRepo extends JpaRepository<Account, String> {

    List<Account> getAccountsByUserId(int userId);
    /** findByName returns a list of all accounts assigned to user with username name
     * @param name the name of the user whose List of accounts we'd like to fetch
     * @return List<Account> of all Accounts belonging to user with String name
     */
    List<Account> findAllByUserId(String userId);
    Account findByAccountId(Integer accountId);

//    Account updateAccount(Account account);

    // For Stretch goal (multiple tracked accounts)
    // void deleteAccountByAccountId(int accountId);
    
}
