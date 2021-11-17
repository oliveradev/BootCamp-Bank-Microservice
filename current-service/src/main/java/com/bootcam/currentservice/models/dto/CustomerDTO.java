package com.bootcam.currentservice.models.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private String name;
    private String code;
    private String customerIdentityNumber;
}

