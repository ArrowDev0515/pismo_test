package com.pismo.test.jpa.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="operations_type")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationsType {

    @Id
    Integer operationTypeId;

    String description;
}
