package com.cqrsv1.query.domain;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface AccountDataDao extends Repository<AccountData, String> {
    Optional<AccountData> findById(String id);
    List<AccountData> findAll();
}
