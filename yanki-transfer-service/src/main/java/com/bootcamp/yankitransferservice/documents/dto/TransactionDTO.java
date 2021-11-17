package com.bootcamp.yankitransferservice.documents.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionDTO {
    private String originPhoneNumber;
    private String destinationPhoneNumber;
    private Double amountTransaction;
    private String typeTransaction;
    private String description;
}
