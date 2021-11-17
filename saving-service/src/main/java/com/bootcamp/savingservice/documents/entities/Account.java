package com.bootcamp.savingservice.documents.entities;

import com.bootcamp.savingservice.documents.dto.CustomerCommand;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "saving")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id
    private String id;
    /**
     * @param accountNumber, example "9999-4444-3333-1111"
     */

    private String accountNumber;

    private String typeOfAccount;

    private double amount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOperation = LocalDateTime.now();

    /**
     * Limits Movements
     */

    private int movementPerMonth;

    private int maxLimitMovementPerMonth;
    /**
     * Customer Information
     */
    private CustomerCommand customer;

    private String customerIdentityNumber;

}
