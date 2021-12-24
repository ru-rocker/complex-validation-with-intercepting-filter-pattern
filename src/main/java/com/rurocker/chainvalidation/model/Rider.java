package com.rurocker.chainvalidation.model;


import lombok.*;

import java.math.BigDecimal;

@ToString
@EqualsAndHashCode
@Data
@NoArgsConstructor
public class Rider {

    private String riderCode;
    private String riderName;
    private BigDecimal sumAssured;

}
