package com.bootcamp.debitcardoperations.documents.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Document(collection = "debitpayment")
@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Builder
public class DebitPayment {
    @Id
    private String id;

    @NotNull
    @NotBlank
    private String originAccount;

    @NotNull
    @NotBlank
    private String originTypeOfAccount;

    private double amount;

    private String destinationCredit;

    @NotNull
    @NotBlank
    private String destinationTypeOfCredit;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOperation = LocalDateTime.now();
}
