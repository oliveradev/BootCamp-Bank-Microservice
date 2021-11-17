package com.bootcamp.yankitransferservice.documents.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositDTO {
    private String customerIdentityNumber;
    private String accountNumber;
    private String typeAccount;
    private Double amount;
}
