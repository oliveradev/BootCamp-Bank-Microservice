package com.bootcamp.productreportservice.documents.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SavingAccount {

    private String accountNumber;
    private String typeOfAccount;
    private Double amount;
    private int maxLimitMovementPerMonth;
    private int MovementPerMonth;

}
