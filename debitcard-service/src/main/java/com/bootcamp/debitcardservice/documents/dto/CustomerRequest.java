package com.bootcamp.debitcardservice.documents.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private String name;
    private String customerIdentityNumber;
    private String customerIdentityType;
    private CustomerType customerType;
}
