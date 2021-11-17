package com.bootcamp.transferservice.util;

import com.bootcamp.transferservice.documents.entities.Transfer;

public class TransferCreator {

    public static Transfer createValidTransfer(){
        return Transfer.builder()
                .id("617ee981e22159302f88c88w")
                .originAccount("617ee981e")
                .destinationAccount("617ef")
                .amount(200.0)
                .originTypeOfAccount("SAVING_ACCOUNT")
                .destinationTypeOfAccount("SAVING_ACCOUNT")
                .build();
    }

    public static Transfer createTransferToBeSaved(){
        return Transfer.builder()
                .id("617ee981e22159302f88c88w")
                .originAccount("617ee981e")
                .destinationAccount("617ef")
                .amount(200.0)
                .originTypeOfAccount("SAVING_ACCOUNT")
                .destinationTypeOfAccount("SAVING_ACCOUNT")
                .build();
    }
}
