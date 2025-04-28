package com.example.project03.Service;

import com.example.project03.Model.Account;
import com.example.project03.Model.Customer;
import com.example.project03.Repository.AccountRepository;
import com.example.project03.Repository.CustomerRepository;
import com.example.project03.Api.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    // Customer: Create a new bank account
    public void createAccount(Integer customer_id) {
        Customer customer = getCustomerById(customer_id);
        String accountNumber = generateAccountNumber();
        if (accountRepository.getAccountByAccountNumber(accountNumber) != null)
            throw new ApiException("Account already exists");
        Account account = new Account(null, accountNumber, 0.0, false, customer);
        accountRepository.save(account);
    }

    // Admin: Activate a bank account
    public void activateAccount(Integer account_id) {
        Account account = getAccountById(account_id);
        account.setIs_active(true);
        accountRepository.save(account);
    }

    // Admin: Block a bank account
    public void blockAccount(Integer account_id) {
        Account account = getAccountById(account_id);
        account.setIs_active(false);
        accountRepository.save(account);
    }

    // Customer: View account details
    public Account getAccountDetails(Integer customer_id, Integer account_id) {
        Customer customer = getCustomerById(customer_id);
        Account account = getAccountById(account_id);
        if (!account.getCustomer().getId().equals(customer.getId()))
            throw new ApiException("Account does not belong to customer");
        return getAccountById(account_id);
    }

    // Customer: List all accounts of logged-in customer
    public List<Account> getCustomerAccounts(Integer customer_id) {
        Customer customer = getCustomerById(customer_id);
        return accountRepository.findAccountByCustomer(customer);
    }

    // Customer: Deposit money
    public void deposit(Integer customer_id, Integer account_id, Double amount) {
        Customer customer = getCustomerById(customer_id);
        Account account = getAccountById(account_id);

        if (!account.getCustomer().getId().equals(customer.getId()))
            throw new ApiException("Account does not belong to customer");

        if (amount == null || amount <= 0) {
            throw new ApiException("Deposit amount must be greater than 0");
        }

        checkAccountIsActive(account);
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    // Customer: Withdraw money
    public void withdraw(Integer customer_id, Integer account_id, Double amount) {
        Customer customer = getCustomerById(customer_id);
        Account account = getAccountById(account_id);

        if (!account.getCustomer().getId().equals(customer.getId()))
            throw new ApiException("Account does not belong to customer");

        if (amount == null || amount <= 0) {
            throw new ApiException("Withdraw amount must be greater than 0");
        }

        if (account.getBalance() < amount) {
            throw new ApiException("Insufficient balance");
        }

        checkAccountIsActive(account);
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }

    // Customer: Transfer funds between accounts
    public void transferFunds(Integer customer_id, Integer from_account_id, Integer to_account_id, Double amount) {
        Customer customer = getCustomerById(customer_id);
        Account fromAccount = getAccountById(from_account_id);
        Account toAccount = getAccountById(to_account_id);

        if (!fromAccount.getCustomer().getId().equals(customer.getId()))
            throw new ApiException("Account does not belong to customer");

        if (amount == null || amount <= 0) {
            throw new ApiException("Transfer amount must be greater than 0");
        }

        if (fromAccount.getBalance() < amount) {
            throw new ApiException("Insufficient balance for transfer");
        }

        checkAccountIsActive(fromAccount);
        checkAccountIsActive(toAccount);

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

    // Helper: Get customer by ID
    private Customer getCustomerById(Integer customer_id) {
        Customer customer = customerRepository.findCustomerById(customer_id);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        return customer;
    }

    // Helper: Get account by ID
    private Account getAccountById(Integer accountId) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        return account;
    }

    // Helper: Check if account is active
    private void checkAccountIsActive(Account account) {
        if (!account.getIs_active()) {
            throw new ApiException("Account is not active");
        }
    }

    // Helper: Generate random account number format XXXX-XXXX-XXXX-XXXX
    private String generateAccountNumber() {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
        return uuid.replaceAll("(.{4})", "$1-").substring(0, 19);
    }
}
