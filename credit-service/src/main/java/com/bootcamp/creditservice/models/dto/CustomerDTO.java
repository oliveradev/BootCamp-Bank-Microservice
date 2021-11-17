package com.bootcamp.creditservice.models.dto;


import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CustomerDTO {
    private String name;
    private String code;
    private String customerIdentityNumber;
}
