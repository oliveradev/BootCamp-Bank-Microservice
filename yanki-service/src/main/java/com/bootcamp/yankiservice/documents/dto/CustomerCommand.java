package com.bootcamp.yankiservice.documents.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CustomerCommand {
    private String name;
    private String code;
    private String customerIdentityNumber;
}

