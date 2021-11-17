package com.bootcamp.debitcardoperations.documents.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditDTO {

    private String contractNumber;
    private double amount;
    private String customerIdentityNumber;
}
