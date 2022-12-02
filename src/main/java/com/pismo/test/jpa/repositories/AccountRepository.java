package com.pismo.test.jpa.repositories;

import com.pismo.test.jpa.entities.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Accounts, Integer> {
    Optional<Accounts> findByDocumentNumber(String documentNumber);
}
