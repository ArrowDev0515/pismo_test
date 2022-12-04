package com.pismo.test.controllers;

import com.pismo.test.jpa.entities.Accounts;
import com.pismo.test.models.CreateAccountRequest;
import com.pismo.test.services.AccountService;
import com.pismo.test.services.TransactionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@CrossOrigin(value = "*")
@RestController
@Transactional
@RequestMapping(value = "/accounts")
@Log4j2
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    @PostMapping(value = "")
    public ResponseEntity<Integer> createAccount(@RequestBody CreateAccountRequest request) {
        Optional<Accounts> account = accountService.getAccount(request.getDocumentNumber());
        if (account.isPresent())
            return ResponseEntity.internalServerError().build();

        return ResponseEntity.ok(accountService.createAccount(request.getDocumentNumber()).getAccountId());
    }

    @GetMapping(value = "/{accountId}")
    @ResponseBody
    public Accounts getAccount(@PathVariable Integer accountId) {
        Optional<Accounts> account = accountService.getAccount(accountId);

        log.info(transactionService.getCurrentBalance(accountId));

        return account.orElse(new Accounts());
    }
}
