package com.bootcamp.bootcoinservice.documents.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class YankiCommand {
    private String ownerName;
    private String phoneNumber;
}
