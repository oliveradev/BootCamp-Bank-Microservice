package com.bootcamp.depositservice.models.dto;


import lombok.*;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DebitAccountDTO {
    private String id;
    private double amount;
    private String customerIdentityNumber;
    private String typeAccount;
    private String accountNumber;
    private int maxLimitMovementPerMonth;
    private int movementPerMonth;
    private double commission;
}
