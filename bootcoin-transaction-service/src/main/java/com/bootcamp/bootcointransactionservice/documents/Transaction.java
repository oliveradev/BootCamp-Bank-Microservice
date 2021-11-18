package com.bootcamp.bootcointransactionservice.documents;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "transaction")
public class Transaction {
    @Id
    private String id;

    private String typeTransaction;

    private String phoneNumber;

    private Double amount;

    private Double exchange;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss ")
    private LocalDateTime createdAt = LocalDateTime.now();
}
