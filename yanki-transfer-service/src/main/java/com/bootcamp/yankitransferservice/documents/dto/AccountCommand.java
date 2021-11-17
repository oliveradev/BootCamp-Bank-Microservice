package com.bootcamp.yankitransferservice.documents.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountCommand {
    private String accountNumber;
    private String typeOfAccount;
}
