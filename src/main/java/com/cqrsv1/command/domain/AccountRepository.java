package com.cqrsv1.command.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface AccountRepository extends Repository<Account, AccountId> {
    void save(Account account);
    void delete(Account account);
    Optional<Account> findById(AccountId id);
}
