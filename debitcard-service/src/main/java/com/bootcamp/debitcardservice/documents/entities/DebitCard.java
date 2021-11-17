package com.bootcamp.debitcardservice.documents.entities;

import com.bootcamp.debitcardservice.documents.dto.AccountCommand;
import com.bootcamp.debitcardservice.documents.dto.CustomerCommand;
import lombok.*;


import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DebitCard {
    private String id;
    private String pan;
    private String cardType;
    private String cardBrand;
    private String cvv;
    private String password;
    private List<AccountCommand> accounts;
    private CustomerCommand customer;
    private AccountCommand mainAccount;
}
