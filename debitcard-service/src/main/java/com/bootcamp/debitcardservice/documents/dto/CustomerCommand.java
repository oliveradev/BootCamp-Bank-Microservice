package com.bootcamp.debitcardservice.documents.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerCommand {
    private String name;
    private String code;
    private String customerIdentityNumber;
}
