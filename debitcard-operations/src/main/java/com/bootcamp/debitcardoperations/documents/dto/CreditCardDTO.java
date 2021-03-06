package com.bootcamp.debitcardoperations.documents.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCardDTO {
    private String id;
    private String pan;
    private double balanceAmount;
    private double totalConsumption;
    private String customerIdentityNumber;
}
