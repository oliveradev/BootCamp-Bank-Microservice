package com.bootcamp.creditservice.models.entities;

import com.bootcamp.creditservice.models.dto.CustomerDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Document(collection = "credit")
@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Builder
@NoArgsConstructor
public class Credit {
    @Id
    private String id;

    @NotNull
    private double capital;

    @Field( name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOperation = LocalDateTime.now();

    @Indexed(unique = true)
    private String contractNumber;

    @NotNull
    private String customerIdentityNumber;

    private double amountInitial;

    /**
     * @param amount -> capital + capital*interest
     */
    private double amount;

    private String chargeDay;

    private double commission;

    private double interestRate;

    private boolean debtor;

    @NotNull
    private CustomerDTO customer;
}
