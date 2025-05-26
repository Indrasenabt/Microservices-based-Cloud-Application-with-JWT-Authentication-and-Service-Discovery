package com.microservice.accounts_service.repository;

import com.microservice.accounts_service.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}

