package com.bootcamp.creditservice.models.dto;

import lombok.*;

@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Customer {
    private String name;
    private String customerIdentityType;
    private String customerIdentityNumber;
    private CustomerType customerType;
}
