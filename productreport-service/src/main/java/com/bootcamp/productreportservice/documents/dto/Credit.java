package com.bootcamp.productreportservice.documents.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Credit {
    private String typeOfAccount;

    private String contractNumber;

    private double amountInitial;

    private double amount;

    private boolean debtor;
}
