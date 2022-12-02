package com.pismo.test.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountRequest {
    @JsonProperty(value = "document_number", required = true)
    String documentNumber;
}
