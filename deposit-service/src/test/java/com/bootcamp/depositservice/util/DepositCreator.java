package com.bootcamp.depositservice.util;

import com.bootcamp.depositservice.models.Deposit;

public class DepositCreator {
    public static Deposit createValidDeposit(){
        return Deposit.builder()
                .id("qw123pou64yrte")
                .amount(50.0)
                .costumerIdentityNumber("41526374")
                .typeAccount("SAVING_ACCOUNT")
                .accountNumber("1111-5555-7899-2131")
                .build();
    }

    public static Deposit createDepositToBeSaved(){
        return Deposit.builder()
                .id("qw123pou64yrte")
                .amount(50.0)
                .costumerIdentityNumber("41526374")
                .typeAccount("SAVING_ACCOUNT")
                .accountNumber("1111-5555-7899-2131")
                .build();
    }
}
