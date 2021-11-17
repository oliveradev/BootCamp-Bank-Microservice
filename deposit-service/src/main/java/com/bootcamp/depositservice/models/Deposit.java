package com.bootcamp.depositservice.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "deposit-service")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Deposit {
    @Id
    private String id;
    /**
     * @param amount is info Deposit
     */
    private double amount;

    /**
     * @param costumerIdentityNumber is info Costumer
     */

    private String costumerIdentityNumber;
    /**
     * @param accountType is in
     *                    fo Account
     */
    private String typeAccount;
    /**
     * @param accountNumber is info Account
     */
    private String accountNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOperation = LocalDateTime.now();
}
