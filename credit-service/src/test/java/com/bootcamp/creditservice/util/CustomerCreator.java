package com.bootcamp.creditservice.util;

import com.bootcamp.creditservice.models.dto.Customer;
import com.bootcamp.creditservice.models.dto.CustomerType;

public class CustomerCreator {
    public static Customer createValidCostumer(){
        CustomerType ct = new CustomerType("TP-01", "Personal");

        return Customer.builder()
                .name("Luis Olivera Vasquez")
                .customerIdentityType("DNI")
                .customerIdentityNumber("77380599")
                .customerType(ct)
                .build();
    }
}
