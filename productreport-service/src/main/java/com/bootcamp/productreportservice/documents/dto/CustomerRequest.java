package com.bootcamp.productreportservice.documents.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private String name;
    private String customerIdentityType;
    private String customerIdentityNumber;
    private CustomerType customerType;
}
