package com.pismo.test.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateTransactionRequest {

    @JsonProperty(value = "account_id", required = true)
    Integer accountId;

    @JsonProperty(value = "operation_type_id", required = true)
    Integer operationTypeId;

    @JsonProperty(required = true)
    BigDecimal amount;
}
