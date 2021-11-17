package com.bootcamp.fixedtermservice.models.entities;

import com.bootcamp.fixedtermservice.models.dto.CustomerDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


@Document(collection = "fixedTermAccound")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class FixedTermAccount {

    @Id
    private String id;

    private String typeOfAccount;


    private String customerIdentityNumber;

    private String accountNumber;

    private double amount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate = LocalDateTime.now();


    private CustomerDTO customer;

    private int maxLimitMovementPerMonth;

    private int movementPerMonth;
}
