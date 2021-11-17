package com.bootcamp.transactionservice.util;

import com.bootcamp.transactionservice.models.Transaction;

import java.time.LocalDateTime;

public class TransactionCreator {
    public static Transaction createValidTransaction(){

        return Transaction.builder()
                .id("6181e4a21c171614ae5e864e")
                .typeTransaction("COMMISSION")
                .identityNumber("3333-2222-1111-5555")
                .typeAccount("SAVING_ACCOUNT")
                .customerIdentityNumber("77380599")
                .transactionAmount(0)
                .dateOperation(LocalDateTime.now())
                .build();
    }

    public static Transaction createTransactionToBeSaved(){

        return Transaction.builder()
                .typeTransaction("COMMISSION")
                .identityNumber("3333-2222-1111-5555")
                .typeAccount("SAVING_ACCOUNT")
                .customerIdentityNumber("77380599")
                .transactionAmount(0)
                .dateOperation(LocalDateTime.now())
                .build();
    }
}
