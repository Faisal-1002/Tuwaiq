package com.example.project03.Repository;

import com.example.project03.Model.Account;
import com.example.project03.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findAccountById(Integer id);
    @Query("select a from Account a where a.account_number=?1")
    Account getAccountByAccountNumber(String accountNumber);
    List<Account> findAccountByCustomer(Customer customer);
}
