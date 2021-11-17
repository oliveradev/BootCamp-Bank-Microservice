package com.bootcamp.withdrawservice.documents.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DebitAccountDTO {
    private String id;
    private double amount;
    private String customerIdentityNumber;
    private String typeOfAccount;
    private String accountNumber;
    private int maxLimitMovementPerMonth;
    private int movementPerMonth;
    private double commission;
}
