package com.bootcamp.yankitransferservice.documents.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DebitCardDTO {
    private String pan;
    private String cvv;
    private CustomerCommand customer;
    private AccountCommand mainAccount;
}
