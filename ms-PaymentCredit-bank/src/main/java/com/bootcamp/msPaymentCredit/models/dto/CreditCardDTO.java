package com.bootcamp.msPaymentCredit.models.dto;

import lombok.*;

/**
 * The type Credit card dto.
 */
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
