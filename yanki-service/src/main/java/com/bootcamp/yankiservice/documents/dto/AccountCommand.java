package com.bootcamp.yankiservice.documents.dto;

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
