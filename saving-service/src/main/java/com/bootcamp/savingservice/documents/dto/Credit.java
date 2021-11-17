package com.bootcamp.savingservice.documents.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Credit {

    private String contractNumber;

    private CustomerCommand customer;

    private boolean debtor;
}

