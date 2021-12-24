package com.rurocker.chainvalidation.model;

import lombok.*;

import java.math.BigDecimal;

@ToString
@EqualsAndHashCode
@Data
@NoArgsConstructor
public class BaseProduct {

    private String productCode;
    private String productName;
    private BigDecimal sumAssured;
}
