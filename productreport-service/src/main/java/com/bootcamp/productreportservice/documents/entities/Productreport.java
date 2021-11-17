package com.bootcamp.productreportservice.documents.entities;

import com.bootcamp.productreportservice.documents.dto.CustomerDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Productreport {
    private CustomerDTO customer;
    private List<Object> productos = new ArrayList<>();

}
