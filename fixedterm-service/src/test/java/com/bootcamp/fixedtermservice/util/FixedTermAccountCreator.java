package com.bootcamp.fixedtermservice.util;

import com.bootcamp.fixedtermservice.models.dto.CustomerDTO;
import com.bootcamp.fixedtermservice.models.entities.FixedTermAccount;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FixedTermAccountCreator {

    public static FixedTermAccount createValidFixedTermAccount() {
        CustomerDTO c = new CustomerDTO("Luis Olivera Vasquez", "TP-01", "77380599");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return FixedTermAccount.builder()
                .id("q234ddfhr7583")
                .typeOfAccount("-")
                .customerIdentityNumber("71710023")
                .accountNumber("1234-1234-1235-3456")
                .amount(2000.0)
                .createDate(LocalDateTime.parse("2021-11-04 19:15:09", formatter))
                .customer(c)
                .maxLimitMovementPerMonth(5)
                .movementPerMonth(3)
                .build();
    }

    public static FixedTermAccount createFixedTermAccountToBeSaved(){
        CustomerDTO c = new CustomerDTO("Luis Olivera Vasquez", "TP-01", "77380599");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return FixedTermAccount.builder()
                .typeOfAccount("-")
                .customerIdentityNumber("71710023")
                .accountNumber("1234-1234-1235-3456")
                .amount(2000.0)
                .createDate(LocalDateTime.parse("2021-11-04 19:15:09", formatter))
                .customer(c)
                .maxLimitMovementPerMonth(5)
                .movementPerMonth(3)
                .build();
    }
}
