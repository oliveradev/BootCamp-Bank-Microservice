package com.bootcamp.savingservice.util;

import com.bootcamp.savingservice.documents.dto.CustomerCommand;
import com.bootcamp.savingservice.documents.entities.Account;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AccountCreator {
    public static Account createValidAccount(){
        CustomerCommand c = new CustomerCommand("Luis Olivera Vasquez", "TP-01", "77380599");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Account.builder()
                .id("uiehfue8372")
                .accountNumber("1111-2222")
                .typeOfAccount("-")
                .amount(1000.0)
                .dateOperation(LocalDateTime.parse("2021-11-04 19:15:09", formatter))
                .movementPerMonth(5)
                .maxLimitMovementPerMonth(10)
                .customer(c)
                .customerIdentityNumber("77380599")
                .build();
    }

    public static Account createAccountToBeSaved(){
        CustomerCommand c = new CustomerCommand("Luis Olivera Vasquez", "TP-01", "77380599");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Account.builder()
                .accountNumber("1111-2222")
                .typeOfAccount("-")
                .amount(1000.0)
                .dateOperation(LocalDateTime.parse("2021-11-04 19:15:09", formatter))
                .movementPerMonth(5)
                .maxLimitMovementPerMonth(10)
                .customer(c)
                .customerIdentityNumber("77380599")
                .build();
    }
}
