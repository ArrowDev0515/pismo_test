package com.pismo.test.services;

import com.pismo.test.jpa.entities.Accounts;
import com.pismo.test.jpa.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Optional<Accounts> getAccount(Integer accountId) {
        return accountRepository.findById(accountId);
    }

    public Optional<Accounts> getAccount(String documentNumber) {
        return accountRepository.findByDocumentNumber(documentNumber);
    }

    public Accounts createAccount(String documentNumber) {
        return accountRepository.save(Accounts.builder().documentNumber(documentNumber).build());
    }
}
