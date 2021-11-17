package com.bootcamp.productreportservice.documents.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Creditcard {
    private String typeOfAccount;

    private String pan;

    private double creditLimit;

    private double totalConsumption;

    private double balanceAmount;

    private boolean debtor;
}
