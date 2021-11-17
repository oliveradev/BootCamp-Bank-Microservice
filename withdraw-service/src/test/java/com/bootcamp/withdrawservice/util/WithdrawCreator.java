package com.bootcamp.withdrawservice.util;

import com.bootcamp.withdrawservice.documents.entities.Withdraw;

import java.time.LocalDateTime;

public class WithdrawCreator {
    public static Withdraw createValidWithdraw(){
        return Withdraw.builder()
                .id("617ee981e22159302f88c899")
                .amount(100.0)
                .typeOfAccount("SAVING_ACCOUNT")
                .accountNumber("1111-2222-3333")
                .retireDate(LocalDateTime.now())
                .build();
    }

    public static Withdraw createWithdrawToBeSaved(){
        return Withdraw.builder()
                .amount(100.0)
                .typeOfAccount("SAVING_ACCOUNT")
                .accountNumber("1111-2222-3333")
                .retireDate(LocalDateTime.now())
                .build();
    }
}
