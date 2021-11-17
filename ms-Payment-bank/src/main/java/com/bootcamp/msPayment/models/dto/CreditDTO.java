package com.bootcamp.msPayment.models.dto;

import lombok.*;

/**
 * The type Credit dto.
 */
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
