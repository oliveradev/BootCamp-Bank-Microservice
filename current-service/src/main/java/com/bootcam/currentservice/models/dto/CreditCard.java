package com.bootcam.currentservice.models.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {

    private String pan;

    private CustomerDTO customer;

    private boolean debtor;
}
