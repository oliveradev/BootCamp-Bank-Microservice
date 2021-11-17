package com.bootcamp.creditcardservice.documents.entities;

import com.bootcamp.creditcardservice.documents.dto.CustomerDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Document(collection = "creditcard")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CreditCard {
    @Id
    private String id;

    @NotNull
    @Indexed(unique=true)
    private String pan;

    @NotNull
    private String cardType;

    @Field( name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOperation = LocalDateTime.now();

    @NotNull
    private String cardBrand;

    private double balanceAmount;

    @NotNull
    private double creditLimit;

    private double totalConsumption;

    @NotNull
    private String settlementDay;

    private boolean debtor;

    @NotNull
    private String chargeDay;

    @NotNull
    private CustomerDTO customer;

}
