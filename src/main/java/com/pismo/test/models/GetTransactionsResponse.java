package com.pismo.test.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pismo.test.utils.DateTimeUtils;
import lombok.*;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
//@SqlResultSetMapping(name="transaction_response_dto",
//classes = @ConstructorResult(targetClass = GetTransactionsResponse.class,
//columns = {
//        @ColumnResult(name = "transactionId", type = Long.class),
//        @ColumnResult(name = "accountId", type = Integer.class),
//        @ColumnResult(name = "operationType", type = String.class),
//        @ColumnResult(name = "eventAt", type = Date.class),
//}))

@AllArgsConstructor
@NoArgsConstructor
public class GetTransactionsResponse {
    @JsonProperty(value = "transaction_id")
    Long transactionId;

    @JsonProperty(value = "account_id")
    Integer accountId;

    @JsonProperty(value = "operation_type")
    String operationType;

    BigDecimal amount;

    @JsonProperty(value = "event_date")
    String eventDate;

    public GetTransactionsResponse(Long transactionId, Integer accountId, String operationType, BigDecimal amount, Date eventAt) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.operationType = operationType;
        this.amount = amount;
        this.eventDate = DateTimeUtils.getStandardDateTime(eventAt);
    }
}
