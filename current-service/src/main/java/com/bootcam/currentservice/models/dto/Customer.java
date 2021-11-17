package com.bootcam.currentservice.models.dto;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private String name;
    private String customerIdentityType;
    private String customerIdentityNumber;
    private CustomerType customerType;
}

