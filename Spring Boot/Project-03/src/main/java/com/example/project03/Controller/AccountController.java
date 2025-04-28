package com.example.project03.Controller;

import com.example.project03.Model.Account;
import com.example.project03.Model.User;
import com.example.project03.Service.AccountService;
import com.example.project03.Api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    // Customer: Create a new bank account
    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@AuthenticationPrincipal User user) {
        accountService.createAccount(user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Bank account created successfully"));
    }

    // Customer: View account details
    @GetMapping("/details/{accountId}")
    public ResponseEntity<?> getAccountDetails(@AuthenticationPrincipal User user,
                                               @PathVariable Integer accountId) {
        Account account = accountService.getAccountDetails(user.getId(), accountId);
        return ResponseEntity.status(200).body(account);
    }

    // Customer: List all accounts
    @GetMapping("/my-accounts")
    public ResponseEntity<?> getCustomerAccounts(@AuthenticationPrincipal User user) {
        List<Account> accounts = accountService.getCustomerAccounts(user.getId());
        return ResponseEntity.status(200).body(accounts);
    }

    // Customer: Deposit money
    @PutMapping("/deposit/{accountId}")
    public ResponseEntity<?> deposit(@AuthenticationPrincipal User user,
                                     @PathVariable Integer accountId,
                                     @RequestParam Double amount) {
        accountService.deposit(user.getId(), accountId, amount);
        return ResponseEntity.status(200).body(new ApiResponse("Deposit successful"));
    }

    // Customer: Withdraw money
    @PutMapping("/withdraw/{accountId}")
    public ResponseEntity<?> withdraw(@AuthenticationPrincipal User user,
                                      @PathVariable Integer accountId,
                                      @RequestParam Double amount) {
        accountService.withdraw(user.getId(), accountId, amount);
        return ResponseEntity.status(200).body(new ApiResponse("Withdrawal successful"));
    }

    // Customer: Transfer funds between accounts
    @PutMapping("/transfer/{fromAccountId}/{toAccountId}")
    public ResponseEntity<?> transferFunds(@AuthenticationPrincipal User user,
                                           @PathVariable Integer fromAccountId,
                                           @PathVariable Integer toAccountId,
                                           @RequestParam Double amount) {
        accountService.transferFunds(user.getId(), fromAccountId, toAccountId, amount);
        return ResponseEntity.status(200).body(new ApiResponse("Transfer successful"));
    }

    // Admin: Activate a bank account
    @PutMapping("/admin/activate/{accountId}")
    public ResponseEntity<?> activateAccount(@PathVariable Integer accountId) {
        accountService.activateAccount(accountId);
        return ResponseEntity.status(200).body(new ApiResponse("Account activated successfully"));
    }

    // Admin: Block a bank account
    @PutMapping("/admin/block/{accountId}")
    public ResponseEntity<?> blockAccount(@PathVariable Integer accountId) {
        accountService.blockAccount(accountId);
        return ResponseEntity.status(200).body(new ApiResponse("Account blocked successfully"));
    }
}