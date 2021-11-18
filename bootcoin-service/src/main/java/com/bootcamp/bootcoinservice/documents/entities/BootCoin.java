package com.bootcamp.bootcoinservice.documents.entities;

import com.bootcamp.bootcoinservice.documents.dto.YankiCommand;
import com.bootcamp.bootcoinservice.documents.dto.YankiDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "bootcoin")
public class BootCoin {
    @Id
    private String id;

    private String name;

    private String customerIdentityType;

    private String customerIdentityNumber;

    private String phoneNumber;

    private String email;

    private YankiCommand yanki;

    private String typeOfAccount;

    private Double amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss ")
    private LocalDateTime createdAt = LocalDateTime.now();

}
