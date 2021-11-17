package com.bootcamp.yankiservice.documents;

import com.bootcamp.yankiservice.documents.dto.DebitCardDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "account")
public class YankiAccount {
    @Id
    private String id;

    private Double amount;

    private String ownerName;

    private String ownerIdentityNumber;

    private String ownerIdentityType;

    private String phoneNumber;

    private String imei;

    private String email;

    private DebitCardDTO debitCard;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate = LocalDateTime.now();

}
