package com.pismo.test.jpa.repositories;

import com.pismo.test.jpa.entities.Transactions;
import com.pismo.test.models.GetTransactionsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {
    List<Transactions> findAllByAccount_AccountId(Integer accountId);

    @Query(value = " select new com.pismo.test.models.GetTransactionsResponse(t.transactionId, u.accountId, p.description, t.amount, t.eventDate) from Transactions t " +
            " left join Accounts u on t.account.accountId=u.accountId " +
            " left join OperationsType p on p.operationTypeId=t.operationsType.operationTypeId " +
            " where t.account.accountId=:accountId")
    List<GetTransactionsResponse> find(@Param("accountId") Integer accountId);
}
