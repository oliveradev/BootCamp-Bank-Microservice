package com.bootcamp.creditservice.util;

import com.bootcamp.creditservice.models.dto.CustomerDTO;
import com.bootcamp.creditservice.models.entities.Credit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreditCreator {
    public static Credit createValidCredit(){
        CustomerDTO c = new CustomerDTO("Luis Olivera Vasquez", "TP-01", "77380599");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Credit.builder()
                .id("6184778d76d308377e482a85")
                .capital(500.0)
                .dateOperation(LocalDateTime.parse("2021-11-04 19:15:09", formatter))
                .contractNumber("5555-4444-3333")
                .customerIdentityNumber("77380599")
                .amountInitial(757.0)
                .amount(575.0)
                .interestRate(0.15)
                .customer(c)
                .build();
    }

    public static Credit createCreditToBeSaved(){
        CustomerDTO c = new CustomerDTO("Luis Olivera Vasquez", "TP-01", "77380599");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Credit.builder()
                .capital(500.0)
                .dateOperation(LocalDateTime.parse("2021-11-14 19:15:09", formatter))
                .contractNumber("5555-4444-3333")
                .customerIdentityNumber("77380599")
                .amountInitial(757.0)
                .amount(575.0)
                .interestRate(0.15)
                .customer(c)
                .build();
    }
}
