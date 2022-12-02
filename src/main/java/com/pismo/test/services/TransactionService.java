package com.pismo.test.services;

import com.pismo.test.constants.OperationType;
import com.pismo.test.jpa.entities.Accounts;
import com.pismo.test.jpa.entities.OperationsType;
import com.pismo.test.jpa.entities.Transactions;
import com.pismo.test.jpa.repositories.TransactionRepository;
import com.pismo.test.models.CreateTransactionRequest;
import com.pismo.test.models.GetTransactionsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public Transactions createTransaction(CreateTransactionRequest request) {
        BigDecimal amount = request.getAmount();
        if (request.getOperationTypeId() != OperationType.CREDIT_VOUCHER)
            amount = amount.multiply(new BigDecimal(-1));

        return transactionRepository.save(Transactions.builder()
                .account(
                        Accounts.builder()
                                .accountId(request.getAccountId())
                                .build()
                )
                .operationsType(
                        OperationsType.builder()
                                .operationTypeId(request.getOperationTypeId())
                                .build()
                )
                .amount(amount)
//                .eventDate(new Date())
                .build()
        );
    }

    public List<Transactions> getTransactions(Integer accountId) {
        return transactionRepository.findAllByAccount_AccountId(accountId);
    }

    public List<GetTransactionsResponse> getTransactionsByQuery(Integer accountId) {
        return transactionRepository.find(accountId);
    }
}
