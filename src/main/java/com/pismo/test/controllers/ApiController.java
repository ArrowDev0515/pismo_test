package com.pismo.test.controllers;

import com.pismo.test.jpa.entities.Accounts;
import com.pismo.test.jpa.entities.Transactions;
import com.pismo.test.models.CreateAccountRequest;
import com.pismo.test.models.CreateTransactionRequest;
import com.pismo.test.models.GetTransactionsResponse;
import com.pismo.test.services.AccountService;
import com.pismo.test.services.TransactionService;
import com.pismo.test.utils.DateTimeUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(value = "*")
@RestController
@Transactional
@RequestMapping(value = "/")
@Log4j2
public class ApiController {

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    @PostMapping(value = "accounts")
    public ResponseEntity<Integer> createAccount(@RequestBody CreateAccountRequest request) {
        Optional<Accounts> account = accountService.getAccount(request.getDocumentNumber());
        if (account.isPresent())
            return ResponseEntity.internalServerError().build();

        return ResponseEntity.ok(accountService.createAccount(request.getDocumentNumber()).getAccountId());
    }

    @GetMapping(value = "account/{accountId}")
    @ResponseBody
    public Accounts getAccount(@PathVariable Integer accountId) {
        Optional<Accounts> account = accountService.getAccount(accountId);

        log.info(transactionService.getCurrentBalance(accountId));

        return account.orElse(new Accounts());
    }

    @PostMapping(value = "transactions")
    public ResponseEntity<Long> createTransaction(@RequestBody CreateTransactionRequest request) {
        return ResponseEntity.ok(transactionService.createTransaction(request).getTransactionId());
    }

    @GetMapping(value = "transactions/{accountId}")
    @ResponseBody
    public List<GetTransactionsResponse> getTransactions(@PathVariable Integer accountId) {
//        return transactionService.getTransactionsByQuery(accountId);

        List<Transactions> transactions = transactionService.getTransactions(accountId);
        return transactions.stream().map(item -> GetTransactionsResponse.builder()
                .transactionId(item.getTransactionId())
                .accountId(item.getAccount().getAccountId())
                .operationType(item.getOperationsType().getDescription())
                .amount(item.getAmount())
                .eventDate(DateTimeUtils.getStandardDateTime(item.getEventDate()))
                .build()
        ).collect(Collectors.toList());
    }
}
