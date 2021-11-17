package com.bootcamp.creditcardservice.util;

import com.bootcamp.creditcardservice.documents.dto.CustomerDTO;
import com.bootcamp.creditcardservice.documents.entities.CreditCard;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreditCardCreator {

    public static CreditCard createValidCreditCard(){
        CustomerDTO c = new CustomerDTO("Luis Olivera Vasquez", "TP-01", "77380599");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return CreditCard.builder()
                .id("qsxwdc123678ewq")
                .pan("1111-2222-3333-5555")
                .cardType("PERSONAL")
                .dateOperation(LocalDateTime.parse("2021-11-04 19:15:09", formatter))
                .cardBrand("VISA")
                .balanceAmount(1000.0)
                .creditLimit(1000.0)
                .totalConsumption(500.0)
                .settlementDay("10")
                .debtor(false)
                .chargeDay("05")
                .customer(c)
                .build();
    }

    public static CreditCard createCreditCardToBeSaved(){
        CustomerDTO c = new CustomerDTO("Luis Olivera Vasquez", "TP-01", "77380599");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return CreditCard.builder()
                .id("qsxwdc123678ewq")
                .pan("1111-2222-3333-5555")
                .cardType("PERSONAL")
                .dateOperation(LocalDateTime.parse("2021-11-04 19:15:09", formatter))
                .cardBrand("VISA")
                .balanceAmount(1000.0)
                .creditLimit(1000.0)
                .totalConsumption(500.0)
                .settlementDay("10")
                .debtor(false)
                .chargeDay("05")
                .customer(c)
                .build();
    }
}
