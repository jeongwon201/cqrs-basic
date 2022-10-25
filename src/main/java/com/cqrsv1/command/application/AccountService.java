package com.cqrsv1.command.application;

import com.cqrsv1.command.domain.Account;
import com.cqrsv1.command.domain.AccountId;
import com.cqrsv1.command.domain.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AccountService {

    private AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public void save(AccountRequest request) {
        Account account = new Account(new AccountId(request.getId()), request.getPassword());
        repository.save(account);
    }

    public void change(AccountRequest request) {
        Account account = repository.findById(new AccountId(request.getId()))
                .orElseThrow(() -> new NoSuchElementException("account not found."));
        account.change(request.getPassword());
        repository.save(account);
    }

    public void delete(AccountId id) {
        Account account = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("account not found."));
        repository.delete(account);
    }
}
