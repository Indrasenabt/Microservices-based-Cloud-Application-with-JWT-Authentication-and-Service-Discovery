package com.microservice.accounts_service.controller;

import com.microservice.accounts_service.model.Account;
import com.microservice.accounts_service.repository.AccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountRepository repo;

    public AccountController(AccountRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return repo.findAll();
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return repo.save(account);
    }
}

